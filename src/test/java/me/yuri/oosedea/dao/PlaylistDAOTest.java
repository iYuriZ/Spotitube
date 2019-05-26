package me.yuri.oosedea.dao;

import me.yuri.oosedea.datasource.dao.PlaylistDAO;
import me.yuri.oosedea.datasource.dao.UserDAO;
import me.yuri.oosedea.service.DatabaseConnection;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@RunWith(MockitoJUnitRunner.class)
public class PlaylistDAOTest {

    @Mock
    private DatabaseConnection db;

    @InjectMocks
    private PlaylistDAO playlistDAO;

    @Before
    public void setup() throws SQLException, IOException {
        Connection connection = TestMockDatabaseConnection.getConnection();

        Mockito.when(db.getDBConnection()).thenReturn(connection);
    }


}
