package me.yuri.oosedea.dao;

import me.yuri.oosedea.datasource.dao.TrackDAO;
import me.yuri.oosedea.modelobjects.Track;
import me.yuri.oosedea.datasource.DatabaseConnection;
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
public class TrackDAOTest {

    @Mock
    private DatabaseConnection db;

    @InjectMocks
    private TrackDAO trackDAO;

    @Before
    public void setup() throws SQLException, IOException {
        Connection connection = TestMockDatabaseConnection.getConnection();

        Mockito.when(db.getDBConnection()).thenReturn(connection);
    }

    @Test
    public void testFindAllTracksInPlaylistReturnsList() {
        List<Track> actual = trackDAO.getAllTracksInPlaylist(6);

        List<Track> expected = new ArrayList<>();
        Track track = new Track();
        track.setId(6);
        track.setTitle("Through the fire and the flames");
        track.setPerformer("DragonForce");
        track.setDuration(420);
        track.setAlbum("DragonForce album");
        track.setPlaycount(0);
        track.setPublicationDate(null);
        track.setDescription(null);
        track.setOfflineAvailable(false);

        expected.add(track);

        Assert.assertEquals(expected.get(0), actual.get(0));
    }

    @Test
    public void testFindAllTracksInPlaylistReturnsEmptyList() {
        List<Track> actual = trackDAO.getAllTracksInPlaylist(1000);
        Assert.assertEquals(0, actual.size());
    }

    @Test
    public void testFindAllTracksNotInPlaylistReturnsList() {
        List<Track> actual = trackDAO.getAllTracksNotInPlaylist(6);

        List<Track> expected = new ArrayList<>();
        Track track = new Track();
        track.setId(1);
        track.setTitle("Ocean and a rock");
        track.setPerformer("Lisa Hannigan");
        track.setDuration(337);
        track.setAlbum("Sea sew");
        track.setPlaycount(0);
        track.setPublicationDate(null);
        track.setDescription(null);
        track.setOfflineAvailable(true);

        expected.add(track);

        Assert.assertEquals(expected.get(0), actual.get(0));
    }

    @Test
    public void testFindAllTracksNotInPlaylistReturnsEmptyList() {
        List<Track> actual = trackDAO.getAllTracksInPlaylist(1000);
        Assert.assertEquals(0, actual.size());
    }
}
