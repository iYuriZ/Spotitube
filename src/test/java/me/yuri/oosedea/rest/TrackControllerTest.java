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
import org.springframework.http.ResponseEntity;

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
        TracksListResponse response = new TracksListResponse(new ArrayList<>());

        Mockito.when(trackService.getAllTracksNotInPlaylist(1, token)).thenReturn(new ArrayList<>());

        ResponseEntity expected = ResponseEntity.status(200).body(response);
        ResponseEntity actual = trackController.getAllTracksNotInPlaylist(1, token);

        List<Track> expectedList = new ArrayList<>();
        List<Track> actualList = response.getTracks();

        Assert.assertEquals(expectedList, actualList);
        Assert.assertEquals(expected.getStatusCode(), actual.getStatusCode());
    }
}
