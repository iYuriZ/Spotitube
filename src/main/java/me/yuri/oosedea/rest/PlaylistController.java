package me.yuri.oosedea.rest;

import me.yuri.oosedea.datasource.mo.Playlist;
import me.yuri.oosedea.datasource.mo.Track;
import me.yuri.oosedea.rest.dto.implementation.PlaylistRequest;
import me.yuri.oosedea.rest.dto.implementation.PlaylistResponse;
import me.yuri.oosedea.rest.dto.implementation.TrackResponse;
import me.yuri.oosedea.rest.dto.implementation.TracksListResponse;
import me.yuri.oosedea.service.PlaylistService;
import me.yuri.oosedea.service.TracksService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/playlists/")
public class PlaylistController extends Responses {

    @Inject
    private PlaylistService playlistService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response requestAllPlaylists(@QueryParam("token") String token) {
        List<Playlist> playlists = playlistService.getAllPlaylists(token);
        PlaylistResponse response = new PlaylistResponse(playlists);
        return respondOk(response);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addNewPlaylist(@QueryParam("token") String token, PlaylistRequest request) {
        List<Playlist> playlists = playlistService.addNewPlaylist(token, request.getName());
        PlaylistResponse response = new PlaylistResponse(playlists);
        return respondCreated(response);
    }

    @PUT
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response renamePlaylist(@QueryParam("token") String token, PlaylistRequest request) {
        List<Playlist> playlists = playlistService.renamePlaylist(request.getId(), token, request.getName());
        PlaylistResponse response = new PlaylistResponse(playlists);
        return respondCreated(response);
    }

    @DELETE
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deletePlaylist(@QueryParam("token") String token, @PathParam("id") int id) {
        List<Playlist> playlists = playlistService.deletePlaylist(id, token);
        PlaylistResponse response = new PlaylistResponse(playlists);
        return respondOk(response);
    }
}
