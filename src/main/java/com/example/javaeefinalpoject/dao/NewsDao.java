package com.example.javaeefinalpoject.dao;

import com.example.javaeefinalpoject.entity.News;
import com.example.javaeefinalpoject.util.ConnectionManager;
import com.example.javaeefinalpoject.util.Converter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class NewsDao {

    private static final NewsDao INSTANCE = new NewsDao();
    private final Converter converter = new Converter();
    private final UserDao userDao = UserDao.getInstance();
    private static final String FIND_ALL = """
                SELECT n.id,
                    n.title,
                    n.content,
                    n.post_date,
                    n.user_id
                FROM news n 
                INNER JOIN users u ON u.id = n.user_id
                ORDER BY n.post_date DESC 
            """;

    private static final String SEARCH = """
                SELECT n.id,
                    n.title,
                    n.content,
                    n.post_date,
                    n.user_id
                FROM news n
                INNER JOIN users u ON u.id = n.user_id
                WHERE LOWER(n.title) LIKE LOWER(?)
                ORDER BY n.post_date DESC
            """;

    private static final String FIND_BY_ID = """
                SELECT id,
                    title,
                    content,
                    post_date,
                    user_id
               
                FROM news 
                WHERE id = ?
                """;
    private static final String SAVE_SQL = """
                INSERT INTO news (title, content, post_date, user_id) 
                VALUES (?, ? ,? ,?);
            """;
    private static final String UPDATE_SQL = """
                UPDATE news
                SET title = ?,
                    content = ?
                WHERE id = ?
            """;
    private static final String DELETE_SQL = """
                DELETE FROM news
                WHERE id = ?
            """;

    public List<News> findAll() {
        try (var connection = ConnectionManager.open();
             var preparedStatement = connection.prepareStatement(FIND_ALL)) {
            ResultSet resultSet = preparedStatement.executeQuery();

            List<News> list = new ArrayList<>();
            while (resultSet.next()) {
                list.add(buildNews(resultSet));
            }
            return list;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<News> search(String key) {
        try (var connection = ConnectionManager.open();
             var preparedStatement = connection.prepareStatement(SEARCH)) {
            String uniKey = "%" + key + "%";
            preparedStatement.setString(1, uniKey);
            ResultSet resultSet = preparedStatement.executeQuery();

            List<News> list = new ArrayList<>();
            while (resultSet.next()) {
                list.add(buildNews(resultSet));
            }
            return list;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public News findById(Integer id) {
        try (var connection = ConnectionManager.open();
             var preparedStatement = connection.prepareStatement(FIND_BY_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            News news = null;
            if (resultSet.next()) {
                news = buildNews(resultSet);
            }
            return news;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean save(News news) {
        try (var connection = ConnectionManager.open();
             var preparedStatement = connection.prepareStatement(SAVE_SQL)) {
            preparedStatement.setString(1, news.getTitle());
            preparedStatement.setString(2, news.getContent());
            preparedStatement.setTimestamp(3, converter.convertToDatabaseColumn(news.getPostDate()));
            preparedStatement.setLong(4, news.getUser().getId());

            int executedUpdate = preparedStatement.executeUpdate();
            return executedUpdate > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean update(News news) {
        try (var connection = ConnectionManager.open();
             var preparedStatement = connection.prepareStatement(UPDATE_SQL)) {
            preparedStatement.setString(1, news.getTitle());
            preparedStatement.setString(2, news.getContent());
            preparedStatement.setInt(3, news.getId());

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


    private News buildNews(ResultSet resultSet) throws SQLException {
        return new News(
                resultSet.getInt("id"),
                resultSet.getString("title"),
                resultSet.getString("content"),
                converter.convertToEntityAttribute(resultSet.getTimestamp("post_date")),
                userDao.findById(resultSet.getLong("user_id"))
        );
    }

    private NewsDao() {
    }

    public static NewsDao getInstance() {
        return INSTANCE;
    }
}
