package com.example.javaeefinalpoject.dao;

import com.example.javaeefinalpoject.entity.Comment;
import com.example.javaeefinalpoject.util.ConnectionManager;
import com.example.javaeefinalpoject.util.Converter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CommentDao {

    private static final CommentDao INSTANCE = new CommentDao();
    private static final String FIND_ALL = """
                SELECT c.id,
                    c.comment,
                    c.post_date,
                    c.user_id,
                    c.news_id
                FROM comment c 
            """;
    private static final String FIND_BY_NEWS_ID = FIND_ALL + """
                INNER JOIN users u on c.user_id = u.id
                WHERE c.news_id = ?
                ORDER BY c.post_date DESC
            """;
    private static final String SAVE_SQL = """
                INSERT INTO comment (comment, post_date, user_id, news_id) 
                VALUES (?, ?, ?, ?);
            """;
    private static final String DELETE_SQL = """
                DELETE FROM comment
                WHERE id = ?
            """;
    private final Converter converter = new Converter();
    private final UserDao userDao = UserDao.getInstance();
    private final NewsDao newsDao = NewsDao.getInstance();

    public List<Comment> findAllByNewsId(Integer id) {
        try (var connection = ConnectionManager.open();
             var preparedStatement = connection.prepareStatement(FIND_BY_NEWS_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            List<Comment> comments = new ArrayList<>();
            while (resultSet.next()) {
                comments.add(buildComment(resultSet));
            }
            return comments;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean save(Comment comment) {
        try (var connection = ConnectionManager.open();
             var preparedStatement = connection.prepareStatement(SAVE_SQL)) {
            preparedStatement.setString(1, comment.getComment());
            preparedStatement.setTimestamp(2, converter.convertToDatabaseColumn(comment.getPostDate()));
            preparedStatement.setLong(3, comment.getUser().getId());
            preparedStatement.setInt(4, comment.getNews().getId());

            int executeUpdate = preparedStatement.executeUpdate();
            return executeUpdate > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean delete(Integer id) {
        try (var connection = ConnectionManager.open();
             var preparedStatement = connection.prepareStatement(DELETE_SQL)) {
            preparedStatement.setInt(1, id);

            int executeUpdate = preparedStatement.executeUpdate();
            return executeUpdate > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private Comment buildComment(ResultSet resultSet) throws SQLException {
        return new Comment(
                resultSet.getInt("id"),
                resultSet.getString("comment"),
                converter.convertToEntityAttribute(resultSet.getTimestamp("post_date")),
                userDao.findById(resultSet.getLong("user_id")),
                newsDao.findById(resultSet.getInt("news_id"))
        );
    }


    private CommentDao() {
    }

    public static CommentDao getInstance() {
        return INSTANCE;
    }
}
