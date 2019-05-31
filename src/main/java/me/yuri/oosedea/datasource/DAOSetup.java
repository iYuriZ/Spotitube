package me.yuri.oosedea.datasource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public abstract class DAOSetup {

    @Autowired
    protected DatabaseConnection db;

    protected Connection connection;

    protected PreparedStatement stmt;

    protected void prepareStmt(String s) throws SQLException, IOException {
        connection = db.getDBConnection();
        stmt = connection.prepareStatement(s);
    }

    protected ResultSet getResultSet() throws SQLException {
        return stmt.executeQuery();
    }

    protected void closeConnection() throws SQLException {
        stmt.close();
        connection.close();
    }
}
