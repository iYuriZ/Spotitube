package me.yuri.oosedea.rest;

import me.yuri.oosedea.datasource.mo.Track;
import me.yuri.oosedea.rest.dto.implementation.TrackRequest;
import me.yuri.oosedea.rest.dto.implementation.TracksListResponse;
import me.yuri.oosedea.service.TracksService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/playlists/{id}/")
public class TracksInPlaylistController extends Responses {

    @Inject
    private TracksService tracksService;

    @GET
    @Path("tracks")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getAllTracksInPlaylist(@QueryParam("token") String token, @PathParam("id") int playlistId) {
        List<Track> tracks = tracksService.getAllTracksInPlaylist(playlistId);
        TracksListResponse response = new TracksListResponse(tracks);
        return respondOk(response);
    }

    @POST
    @Path("tracks")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addTrackToPlaylist(@QueryParam("token") String token, @PathParam("id") int playlistId, TrackRequest request) {
        List<Track> tracks = tracksService.addTrackToPlaylist(playlistId, request.getId());
        TracksListResponse response = new TracksListResponse(tracks);
        return respondOk(response);
    }

    @DELETE
    @Path("tracks/{trackId}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response removeTrackFromPlaylist(@QueryParam("token") String token, @PathParam("id") int playlistId, @PathParam("trackId") int trackId) {
        List<Track> tracks = tracksService.removeTrackFromPlaylist(playlistId, trackId);
        TracksListResponse response = new TracksListResponse(tracks);
        return respondOk(response);
    }
}
