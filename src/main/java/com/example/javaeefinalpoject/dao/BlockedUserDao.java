package com.example.javaeefinalpoject.dao;

import com.example.javaeefinalpoject.entity.BlockedUser;
import com.example.javaeefinalpoject.entity.User;
import com.example.javaeefinalpoject.util.ConnectionManager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BlockedUserDao {

    private static final BlockedUserDao INSTANCE = new BlockedUserDao();
    private static final String FIND_ALL = """
                SELECT id,
                    reason,
                    user_id
                FROM blocked_user
            """;

    private static final String FIND_BY_ID = FIND_ALL + " WHERE id = ?";
    private static final String FIND_BY_USER_ID = FIND_ALL + " WHERE user_id = ?";
    private static final String SAVE_SQL = """
                INSERT INTO blocked_user (reason, user_id)
                VALUES (?, ?);
            """;
    private static final String DELETE_SQL = """
                DELETE FROM blocked_user
                WHERE id = ?
            """;
    private final UserDao userDao = UserDao.getInstance();

    public List<BlockedUser> findAll() {
        try(var connection = ConnectionManager.open();
            var preparedStatement = connection.prepareStatement(FIND_ALL)) {
            ResultSet resultSet = preparedStatement.executeQuery();

            List<BlockedUser> blockedUsers = new ArrayList<>();
            while (resultSet.next()) {
                blockedUsers.add(buildBlockedUser(resultSet));
            }
            return blockedUsers;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public BlockedUser findByUserId(Long id) {
        try(var connection = ConnectionManager.open();
            var preparedStatement = connection.prepareStatement(FIND_BY_USER_ID)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            BlockedUser blockedUser = null;
            while (resultSet.next()) {
                blockedUser = buildBlockedUser(resultSet);
            }
            return blockedUser;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public BlockedUser findById(Long id) {
        try(var connection = ConnectionManager.open();
            var preparedStatement = connection.prepareStatement(FIND_BY_ID)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            BlockedUser blockedUser = null;
            while (resultSet.next()) {
                blockedUser = buildBlockedUser(resultSet);
            }
            return blockedUser;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public boolean save(BlockedUser blockedUser) {
        try(var connection = ConnectionManager.open();
            var preparedStatement = connection.prepareStatement(SAVE_SQL)) {
            preparedStatement.setString(1, blockedUser.getReason());
            preparedStatement.setLong(2, blockedUser.getUser().getId());

            int result = preparedStatement.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean delete(Long id) {
        try(var connection = ConnectionManager.open();
            var preparedStatement = connection.prepareStatement(DELETE_SQL)) {
            preparedStatement.setLong(1, id);

            int executeUpdate = preparedStatement.executeUpdate();
            return executeUpdate > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private BlockedUser buildBlockedUser(ResultSet resultSet) throws SQLException {
        return new BlockedUser(
                resultSet.getLong("id"),
                resultSet.getString("reason"),
                userDao.findById(resultSet.getLong("user_id"))
        );
    }


    private BlockedUserDao() {
    }

    public static BlockedUserDao getInstance() {
        return INSTANCE;
    }
}
