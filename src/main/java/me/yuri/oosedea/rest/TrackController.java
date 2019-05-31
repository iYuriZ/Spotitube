package me.yuri.oosedea.rest;

import me.yuri.oosedea.exceptions.UnauthorizedUserException;
import me.yuri.oosedea.modelobjects.Track;
import me.yuri.oosedea.rest.dto.implementation.TracksListResponse;
import me.yuri.oosedea.service.TrackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RequestMapping("/tracks")
@Controller
public class TrackController extends Responses {

    @Autowired
    TrackService trackService;

    @GetMapping("")
    public ResponseEntity getAllTracksNotInPlaylist(@RequestParam("forPlaylist") int playlistId, @RequestParam("token") String token)
            throws UnauthorizedUserException {
        List<Track> tracks = trackService.getAllTracksNotInPlaylist(playlistId, token);
        TracksListResponse response = new TracksListResponse(tracks);
        return respondOk(response);
    }
}
