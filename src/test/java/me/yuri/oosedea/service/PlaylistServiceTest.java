package me.yuri.oosedea.service;

import me.yuri.oosedea.datasource.dao.PlaylistDAO;
import me.yuri.oosedea.exceptions.UnauthorizedUserException;
import me.yuri.oosedea.modelobjects.Playlist;
import me.yuri.oosedea.service.implementation.LoginServiceImpl;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.doNothing;

@RunWith(MockitoJUnitRunner.class)
public class PlaylistServiceTest {

    @InjectMocks
    private PlaylistService playlistService;

    @Mock
    private PlaylistDAO playlistDAO;

    @Mock
    LoginServiceImpl loginService;

    @Test
    public void testGetAllPlaylistsReturnsEmptyList() throws UnauthorizedUserException {
        List<Playlist> expected = new ArrayList<>();

        Mockito.when(playlistDAO.findAllPlaylists("0891-bva2-he7d")).thenReturn(new ArrayList<>());
        Mockito.when(loginService.authorizeByToken("0891-bva2-he7d")).thenReturn(true);
        List<Playlist> actual = playlistService.getAllPlaylists("0891-bva2-he7d", false);

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testAddNewPlaylistReturnsEmptyList() throws UnauthorizedUserException {
        List<Playlist> expected = new ArrayList<>();

        doNothing().when(playlistDAO).addNewPlaylist("0891-bva2-he7d", "Rock");
        Mockito.when(playlistDAO.findAllPlaylists("0891-bva2-he7d")).thenReturn(new ArrayList<>());

        List<Playlist> actual = playlistService.addNewPlaylist("0891-bva2-he7d", "Rock");

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testRenamePlaylistReturnsEmptyList() throws UnauthorizedUserException {
        List<Playlist> expected = new ArrayList<>();

        doNothing().when(playlistDAO).updatePlaylist(1, "0891-bva2-he7d", "Rock");
        Mockito.when(playlistDAO.findAllPlaylists("0891-bva2-he7d")).thenReturn(new ArrayList<>());

        List<Playlist> actual = playlistService.renamePlaylist(1, "0891-bva2-he7d", "Rock");

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testDeletePlaylistReturnsEmptyList() throws UnauthorizedUserException {
        List<Playlist> expected = new ArrayList<>();

        doNothing().when(playlistDAO).deletePlaylist(1, "0891-bva2-he7d");
        Mockito.when(playlistDAO.findAllPlaylists("0891-bva2-he7d")).thenReturn(new ArrayList<>());

        List<Playlist> actual = playlistService.deletePlaylist(1, "0891-bva2-he7d");

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testGetTotalLength() {
        Playlist playlist1 = new Playlist();
        Playlist playlist2 = new Playlist();

        playlist1.setLength(100);
        playlist2.setLength(155);

        List<Playlist> playlists = new ArrayList<>();
        playlists.add(playlist1);
        playlists.add(playlist2);

        int expected = 255;
        int actual = playlistService.getTotalLength(playlists);

        Assert.assertEquals(expected, actual);
    }
}
