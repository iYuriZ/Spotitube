package me.yuri.oosedea.rest;

import me.yuri.oosedea.exceptions.UnauthorizedUserException;
import me.yuri.oosedea.modelobjects.Playlist;
import me.yuri.oosedea.modelobjects.Track;
import me.yuri.oosedea.rest.dto.implementation.PlaylistRequest;
import me.yuri.oosedea.rest.dto.implementation.PlaylistResponse;
import me.yuri.oosedea.rest.dto.implementation.TrackRequest;
import me.yuri.oosedea.rest.dto.implementation.TracksListResponse;
import me.yuri.oosedea.service.PlaylistService;
import me.yuri.oosedea.service.TrackService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class PlaylistControllerTest {

    @Mock
    private PlaylistService playlistService;

    @Mock
    private TrackService trackService;

    @InjectMocks
    private PlaylistController playlistController;

    private PlaylistRequest playlistRequest;
    private TrackRequest trackRequest;
    private Playlist playlist;
    private String token;

    @Before
    public void setup() {
        playlistRequest = new PlaylistRequest();
        trackRequest = new TrackRequest();
        playlist = new Playlist();
        token = "0891-bva2-he7d";
    }

    @Test
    public void testRequestAllPlaylistsReturnsOk() throws UnauthorizedUserException {
        PlaylistResponse response = new PlaylistResponse(new ArrayList<>());

        Mockito.when(playlistService.getAllPlaylists(token, false)).thenReturn(new ArrayList<>());

        Response actual = playlistController.requestAllPlaylists(token);

        Assert.assertEquals(Response.ok(response).build().getStatus(), actual.getStatus());
    }

    @Test
    public void testAddNewPlaylistReturnsCreated() throws UnauthorizedUserException {
        playlistRequest.setId(1);
        playlistRequest.setName("Rock");
        playlistRequest.setOwner(true);
        playlistRequest.setTracks(new int[] {1, 2});

        playlist.setId(1);
        playlist.setName("Rock");
        playlist.setOwner(true);
        playlist.setTracks(new int[] {1, 2});

        List<Playlist> playlists = new ArrayList<>();
        playlists.add(playlist);

        PlaylistResponse response = new PlaylistResponse(playlists);

        Mockito.when(playlistService.addNewPlaylist(token, "Rock")).thenReturn(playlists);

        Response actual = playlistController.addNewPlaylist(token, playlistRequest);

        Assert.assertEquals(Response.status(201).entity(response).build().getStatus(), actual.getStatus());
    }

    @Test
    public void testRenamingNewPlaylistReturnsCreated() throws UnauthorizedUserException {
        playlistRequest.setId(1);
        playlistRequest.setName("Rock");
        playlistRequest.setOwner(true);
        playlistRequest.setTracks(new int[] {1, 2});

        Playlist playlist = new Playlist();
        playlist.setId(1);
        playlist.setName("Rock");
        playlist.setOwner(true);
        playlist.setTracks(new int[] {1, 2});

        List<Playlist> playlists = new ArrayList<>();
        playlists.add(playlist);

        PlaylistResponse response = new PlaylistResponse(playlists);

        Mockito.when(playlistService.renamePlaylist(1, token, "Rock")).thenReturn(playlists);

        Response actual = playlistController.renamePlaylist(token, playlistRequest);

        Assert.assertEquals(Response.status(201).entity(response).build().getStatus(), actual.getStatus());
    }

    @Test
    public void testDeletePlaylistReturnsOk() throws UnauthorizedUserException {
        List<Playlist> playlists = new ArrayList<>();
        PlaylistResponse response = new PlaylistResponse(new ArrayList<>());

        Mockito.when(playlistService.deletePlaylist(1, token)).thenReturn(playlists);

        Response actual = playlistController.deletePlaylist(token, 1);

        Assert.assertEquals(Response.ok(response).build().getStatus(), actual.getStatus());
    }

    @Test
    public void testGetAllTracksInPlaylistReturnsOk() throws UnauthorizedUserException {
        List<Track> tracks = new ArrayList<>();
        TracksListResponse response = new TracksListResponse(new ArrayList<>());

        Mockito.when(trackService.getAllTracksInPlaylist(1, token, false)).thenReturn(tracks);

        Response actual = playlistController.getAllTracksInPlaylist(token, 1);

        Assert.assertEquals(Response.ok(response).build().getStatus(), actual.getStatus());
    }

    @Test
    public void testAddTrackToPlaylistReturnsCreated() throws UnauthorizedUserException {
        trackRequest.setId(1);
        trackRequest.setTitle("Transmission");
        trackRequest.setPerformer("The Prototypes");
        trackRequest.setDuration(205);
        trackRequest.setAlbum("Prototypes 2016");
        trackRequest.setPublicationDate(null);
        trackRequest.setOfflineAvailable(true);
        trackRequest.setDescription("...");

        List<Track> tracks = new ArrayList<Track>();
        Track track = new Track();
        track.setId(1);
        track.setTitle("Transmission");
        track.setPerformer("The Prototypes");
        track.setDuration(205);
        track.setAlbum("Prototypes 2016");
        track.setPublicationDate(null);
        track.setOfflineAvailable(true);
        track.setDescription("...");

        tracks.add(track);

        TracksListResponse response = new TracksListResponse(tracks);

        Mockito.when(trackService.addTrackToPlaylist(1, 1, true, token)).thenReturn(tracks);

        Response actual = playlistController.addTrackToPlaylist(token, 1, trackRequest);

        Assert.assertEquals(Response.status(201).entity(response).build().getStatus(), actual.getStatus());
    }

    @Test
    public void testDeleteTrackFromPlaylistReturnsOk() throws UnauthorizedUserException {
        List<Track> tracks = new ArrayList<Track>();
        TracksListResponse response = new TracksListResponse(new ArrayList<Track>());

        Mockito.when(trackService.removeTrackFromPlaylist(1, 1, token)).thenReturn(tracks);

        Response actual = playlistController.removeTrackFromPlaylist(token, 1, 1);

        Assert.assertEquals(Response.ok(response).build().getStatus(), actual.getStatus());
    }
}
