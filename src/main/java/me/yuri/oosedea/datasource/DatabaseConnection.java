package me.yuri.oosedea.datasource;

import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import static java.sql.DriverManager.getConnection;

@Repository
public class DatabaseConnection {

    public DatabaseConnection() {}

    public Connection getDBConnection() throws IOException {
        Properties connection = getProperties();

        String driver = connection.getProperty("jdbc.driverClassName");
        String url = connection.getProperty("jdbc.url");
        String user = connection.getProperty("jdbc.username");
        String password = connection.getProperty("jdbc.password");

        try {
            Class.forName(driver);
            return getConnection(url, user, password);
        } catch (SQLException e) {
            System.err.println("Connection to database failed: " + e);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Properties getProperties() throws IOException {
        Properties connection = new Properties();
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream input = classLoader.getResourceAsStream("db.properties");
        connection.load(input);
        return connection;
    }
}
