package com.example.javaeefinalpoject.dao;

import com.example.javaeefinalpoject.entity.User;
import com.example.javaeefinalpoject.util.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDao {

    private static final UserDao INSTANCE = new UserDao();
    private static final String FIND_ALL = """
                SELECT id,
                    full_name,
                    email,
                    password,
                    birth_date,
                    role
                FROM users
            """;
    private static final String FIND_BY_ID = FIND_ALL + " WHERE id = ?";
    private static final String FIND_BY_EMAIL = FIND_ALL + " WHERE email = ?";
    private static final String SAVE_SQL = """
                INSERT INTO users (full_name, email, password, birth_date, role)
                VALUES (?, ?, ?, ?, ?);
            """;

    public List<User> findAll() {
        try(var connection = ConnectionManager.open();
            var preparedStatement = connection.prepareStatement(FIND_ALL)) {
            ResultSet resultSet = preparedStatement.executeQuery();

            List<User> users = new ArrayList<>();
            while (resultSet.next()) {
                users.add(buildUser(resultSet));
            }
            return users;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public User findById(Long id) {
        try(var connection = ConnectionManager.open();
            var preparedStatement = connection.prepareStatement(FIND_BY_ID)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            
            User user = null;
            if (resultSet.next()) {
                user = buildUser(resultSet);
            }
            return user;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public User findByEmail(String email) {
        try(var connection = ConnectionManager.open();
            var preparedStatement = connection.prepareStatement(FIND_BY_EMAIL)) {
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();

            User user = null;
            if (resultSet.next()) {
                user = buildUser(resultSet);
            }
            return user;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean save(User user) {
        try(var connection = ConnectionManager.open();
            var preparedStatement = connection.prepareStatement(SAVE_SQL)) {
            preparedStatement.setString(1, user.getFullName());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setString(3, user.getPassword());
            preparedStatement.setString(4, user.getBirthDate());
            preparedStatement.setInt(5, user.getRole());

            int result = preparedStatement.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private User buildUser(ResultSet resultSet) throws SQLException {
        return new User(
                resultSet.getLong("id"),
                resultSet.getString("full_name"),
                resultSet.getString("email"),
                resultSet.getString("password"),
                resultSet.getString("birth_date"),
                resultSet.getInt("role")
        );
    }


    private UserDao() {
    }

    public static UserDao getInstance() {
        return INSTANCE;
    }
}
