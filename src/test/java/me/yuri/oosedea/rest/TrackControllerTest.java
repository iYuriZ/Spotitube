package me.yuri.oosedea.rest;

import me.yuri.oosedea.exceptions.UnauthorizedUserException;
import me.yuri.oosedea.modelobjects.Track;
import me.yuri.oosedea.rest.dto.implementation.TracksListResponse;
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
public class TrackControllerTest {

    @Mock
    private TrackService trackService;

    @InjectMocks
    private TrackController trackController;

    private String token;

    @Before
    public void setup() {
        token = "0891-bva2-he7d";
    }

    @Test
    public void testGetAllTracksNotInPlaylistReturnsOk() throws UnauthorizedUserException {
        List<Track> tracks = new ArrayList<Track>();
        TracksListResponse response = new TracksListResponse(new ArrayList<Track>());

        Mockito.when(trackService.getAllTracksNotInPlaylist(1, token)).thenReturn(tracks);

        Response actual = trackController.getAllTracksNotInPlaylist(1, token);

        Assert.assertEquals(Response.ok(response).build().getStatus(), actual.getStatus());
    }
}
