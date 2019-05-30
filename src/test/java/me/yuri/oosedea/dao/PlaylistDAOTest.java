package me.yuri.oosedea.dao;

import me.yuri.oosedea.datasource.dao.PlaylistDAO;
import me.yuri.oosedea.modelobjects.Playlist;
import me.yuri.oosedea.service.DatabaseConnection;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class PlaylistDAOTest {

    @Mock
    private DatabaseConnection db;

    @InjectMocks
    private PlaylistDAO playlistDAO;

    private String token;

    @Before
    public void setup() throws SQLException, IOException {
        Connection connection = TestMockDatabaseConnection.getConnection();

        Mockito.when(db.getDBConnection()).thenReturn(connection);

        token = "0892-bva2-he7d";
    }

    @Test
    public void testFindAllPlaylistsReturnsList() {
        List<Playlist> actual = playlistDAO.findAllPlaylists(token);

        List<Playlist> expected = new ArrayList<>();

        Playlist playlist = new Playlist();
        playlist.setId(6);
        playlist.setName("Music");
        playlist.setOwner(true);
        playlist.setLength(420);
        playlist.setTracks(new int[] {6});

        expected.add(playlist);

        Assert.assertEquals(expected.get(0).getId(), actual.get(5).getId());
        Assert.assertEquals(expected.get(0).getName(), actual.get(5).getName());
        Assert.assertEquals(expected.get(0).isOwner(), actual.get(5).isOwner());
        Assert.assertArrayEquals(expected.get(0).getTracks(), actual.get(5).getTracks());
    }
}
