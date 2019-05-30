package me.yuri.oosedea.service;

import me.yuri.oosedea.datasource.dao.TrackDAO;
import me.yuri.oosedea.exceptions.UnauthorizedUserException;
import me.yuri.oosedea.modelobjects.Track;
import me.yuri.oosedea.service.implementation.LoginServiceImpl;
import me.yuri.oosedea.service.implementation.TrackServiceImpl;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.doNothing;

@RunWith(MockitoJUnitRunner.class)
public class TrackServiceTest {

    @InjectMocks
    private TrackServiceImpl trackService;

    @Mock
    private TrackDAO trackDAO;

    @Mock
    private LoginServiceImpl loginService;

    @Test
    public void testGetAllTracksInPlaylistReturnsEmptyList() throws UnauthorizedUserException {
        List<Track> expected = new ArrayList<>();

        Mockito.when(trackDAO.getAllTracksInPlaylist(1)).thenReturn(new ArrayList<>());
        Mockito.when(loginService.authorizeByToken("0891-bva2-he7d")).thenReturn(true);

        List<Track> actual = trackService.getAllTracksInPlaylist(1,"0891-bva2-he7d", false);

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testGetAllTracksNotInPlaylistReturnsEmptyList() throws UnauthorizedUserException {
        List<Track> expected = new ArrayList<>();

        Mockito.when(trackDAO.getAllTracksNotInPlaylist(1)).thenReturn(new ArrayList<>());
        Mockito.when(loginService.authorizeByToken("0891-bva2-he7d")).thenReturn(true);

        List<Track> actual = trackService.getAllTracksNotInPlaylist(1,"0891-bva2-he7d");

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testAddTrackToPlaylistReturnsEmptyList() throws UnauthorizedUserException {
        List<Track> expected = new ArrayList<>();

        Mockito.when(trackDAO.getAllTracksInPlaylist(1)).thenReturn(new ArrayList<>());
        Mockito.when(loginService.authorizeByToken("0891-bva2-he7d")).thenReturn(true);
        doNothing().when(trackDAO).addTrackToPlaylist(1, 1);
        doNothing().when(trackDAO).updateTrackAvailability(1, true);

        List<Track> actual = trackService.addTrackToPlaylist(1, 1, true, "0891-bva2-he7d");

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testRemoveTrackFromPlaylistReturnsEmptyList() throws UnauthorizedUserException {
        List<Track> expected = new ArrayList<>();

        Mockito.when(trackDAO.getAllTracksInPlaylist(1)).thenReturn(new ArrayList<>());
        Mockito.when(loginService.authorizeByToken("0891-bva2-he7d")).thenReturn(true);
        doNothing().when(trackDAO).removeTrackFromPlaylist(1, 1);

        List<Track> actual = trackService.removeTrackFromPlaylist(1, 1, "0891-bva2-he7d");

        Assert.assertEquals(expected, actual);
    }
}
