package me.yuri.oosedea.datasource.dao;

import me.yuri.oosedea.datasource.DAOSetup;
import me.yuri.oosedea.modelobjects.User;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class UserDAO extends DAOSetup {

    public User findUserByUsername(String userName) {
        try {
            prepareStmt("SELECT u.user, u.password, u.token, u.firstname, u.lastname FROM user u WHERE u.user = ?");
            stmt.setString(1, userName);
            return getUser();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public User findUserByToken(String token) {
        try {
            prepareStmt("SELECT user, password, token, firstname, lastname FROM user WHERE token = ?");
            stmt.setString(1, token);
            return getUser();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private User getUser() throws SQLException {
        ResultSet results = getResultSet();
        User user = null;

        if (results.next()) {
            user = mapResultSetToUser(results);
        }
        closeConnection();

        return user;
    }

    private User mapResultSetToUser(ResultSet results) throws SQLException {
        User user = new User();
        user.setUser(results.getString("user"));
        user.setPassword(results.getString("password"));
        user.setToken(results.getString("token"));
        user.setFirstName(results.getString("firstname"));
        user.setLastName(results.getString("lastname"));
        return user;
    }
}
