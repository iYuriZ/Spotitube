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

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/playlists")
public class PlaylistController extends Responses {

    @Inject
    private PlaylistService playlistService;

    @Inject
    private TrackService trackService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response requestAllPlaylists(@QueryParam("token") String token) throws UnauthorizedUserException {
        List<Playlist> playlists = playlistService.getAllPlaylists(token, false);
        PlaylistResponse response = new PlaylistResponse(playlists);
        return respondOk(response);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addNewPlaylist(@QueryParam("token") String token, PlaylistRequest request) throws UnauthorizedUserException {
        List<Playlist> playlists = playlistService.addNewPlaylist(token, request.getName());
        PlaylistResponse response = new PlaylistResponse(playlists);
        return respondCreated(response);
    }

    @PUT
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response renamePlaylist(@QueryParam("token") String token, PlaylistRequest request) throws UnauthorizedUserException {
        List<Playlist> playlists = playlistService.renamePlaylist(request.getId(), token, request.getName());
        PlaylistResponse response = new PlaylistResponse(playlists);
        return respondCreated(response);
    }

    @DELETE
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deletePlaylist(@QueryParam("token") String token, @PathParam("id") int id) throws UnauthorizedUserException {
        List<Playlist> playlists = playlistService.deletePlaylist(id, token);
        PlaylistResponse response = new PlaylistResponse(playlists);
        return respondOk(response);
    }

    @GET
    @Path("{id}/tracks")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getAllTracksInPlaylist(@QueryParam("token") String token, @PathParam("id") int playlistId) throws UnauthorizedUserException {
        List<Track> tracks = trackService.getAllTracksInPlaylist(playlistId, token, false);
        TracksListResponse response = new TracksListResponse(tracks);
        return respondOk(response);
    }

    @POST
    @Path("{id}/tracks")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addTrackToPlaylist(@QueryParam("token") String token, @PathParam("id") int playlistId, TrackRequest request) throws UnauthorizedUserException {
        List<Track> tracks = trackService.addTrackToPlaylist(playlistId, request.getId(), request.isOfflineAvailable(), token);
        TracksListResponse response = new TracksListResponse(tracks);
        return respondOk(response);
    }

    @DELETE
    @Path("{id}/tracks/{trackId}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response removeTrackFromPlaylist(@QueryParam("token") String token, @PathParam("id") int playlistId, @PathParam("trackId") int trackId) throws UnauthorizedUserException {
        List<Track> tracks = trackService.removeTrackFromPlaylist(playlistId, trackId, token);
        TracksListResponse response = new TracksListResponse(tracks);
        return respondOk(response);
    }
}
