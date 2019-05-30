package me.yuri.oosedea.datasource;

import javax.inject.Inject;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class DAOSetup {

    @Inject
    protected DatabaseConnection db;
    protected Connection connection;

    protected PreparedStatement stmt;

    protected void prepareStmt(String s) throws SQLException, IOException {
        connection = db.getDBConnection();
        stmt = connection.prepareStatement(sgit);
    }

    protected ResultSet getResultSet() throws SQLException {
        return stmt.executeQuery();
    }

    protected void closeConnection() throws SQLException {
        stmt.close();
        connection.close();
    }
}
