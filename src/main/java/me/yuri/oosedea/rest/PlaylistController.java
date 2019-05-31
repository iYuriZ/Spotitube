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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/playlists")
public class PlaylistController extends Responses {

    @Autowired
    private PlaylistService playlistService;

    @Autowired
    private TrackService trackService;

    @GetMapping("")
    public ResponseEntity requestAllPlaylists(@RequestParam("token") String token)
            throws UnauthorizedUserException {
        List<Playlist> playlists = playlistService.getAllPlaylists(token, false);
        int length = playlistService.getTotalLength(playlists);
        PlaylistResponse response = new PlaylistResponse(playlists, length);
        return respondOk(response);
    }

    @PostMapping("")
    public ResponseEntity addNewPlaylist(@RequestParam("token") String token, PlaylistRequest request)
            throws UnauthorizedUserException {
        List<Playlist> playlists = playlistService.addNewPlaylist(token, request.getName());

        int length = playlistService.getTotalLength(playlists);
        PlaylistResponse response = new PlaylistResponse(playlists, length);
        return respondCreated(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity renamePlaylist(@RequestParam("token") String token, PlaylistRequest request)
            throws UnauthorizedUserException {
        List<Playlist> playlists = playlistService.renamePlaylist(request.getId(), token, request.getName());

        int length = playlistService.getTotalLength(playlists);
        PlaylistResponse response = new PlaylistResponse(playlists, length);
        return respondCreated(response);
    }

    @DeleteMapping("{id}")
    public ResponseEntity deletePlaylist(@RequestParam("token") String token, @PathVariable("id") int id)
            throws UnauthorizedUserException {
        List<Playlist> playlists = playlistService.deletePlaylist(id, token);
        int length = playlistService.getTotalLength(playlists);
        PlaylistResponse response = new PlaylistResponse(playlists, length);
        return respondOk(response);
    }

    @GetMapping("/{id}/tracks")
    public ResponseEntity getAllTracksInPlaylist(@RequestParam("token") String token, @PathVariable("id") int playlistId)
            throws UnauthorizedUserException {
        List<Track> tracks = trackService.getAllTracksInPlaylist(playlistId, token, false);
        TracksListResponse response = new TracksListResponse(tracks);
        return respondOk(response);
    }

    @PostMapping("{id}/tracks")
    public ResponseEntity addTrackToPlaylist(@RequestParam("token") String token,
                                             @PathVariable("id") int playlistId,
                                             @RequestBody TrackRequest request) throws UnauthorizedUserException {
        List<Track> tracks = trackService.addTrackToPlaylist(playlistId, request.getId(), request.isOfflineAvailable(), token);
        TracksListResponse response = new TracksListResponse(tracks);
        return respondCreated(response);
    }

    @DeleteMapping("/{id}/tracks/{trackId}")
    public ResponseEntity removeTrackFromPlaylist(@RequestParam("token") String token,
                                                  @PathVariable("id") int playlistId,
                                                  @PathVariable("trackId") int trackId) throws UnauthorizedUserException {
        List<Track> tracks = trackService.removeTrackFromPlaylist(playlistId, trackId, token);
        TracksListResponse response = new TracksListResponse(tracks);
        return respondOk(response);
    }
}
