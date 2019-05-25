package me.yuri.oosedea.rest;

import me.yuri.oosedea.datasource.mo.Playlist;
import me.yuri.oosedea.rest.dto.implementation.PlaylistRequest;
import me.yuri.oosedea.rest.dto.implementation.PlaylistResponse;
import me.yuri.oosedea.service.PlaylistService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/playlists")
public class PlaylistController extends Responses {

    @Inject
    PlaylistService service;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response requestAllPlaylists(@QueryParam("token") String token) {
        System.out.println("Entered playlistcontroller");
        List<Playlist> playlists = service.getAllPlaylists(token);
        PlaylistResponse response = new PlaylistResponse(playlists);
        return respondOk(response);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addNewPlaylist(@QueryParam("token") String token, PlaylistRequest request) {
        System.out.println("Entered addnewplaylist");
        System.out.println("Name: " + request.getName());
        List<Playlist> playlists = service.addNewPlaylist(token, request.getName());
        PlaylistResponse response = new PlaylistResponse(playlists);
        return respondCreated(response);
    }

    @PUT
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response renamePlaylist(@QueryParam("token") String token, PlaylistRequest request) {
        System.out.println("Entered addnewplaylist");
        System.out.println("Name: " + request.getName());
        List<Playlist> playlists = service.renamePlaylist(request.getId(), token, request.getName());
        PlaylistResponse response = new PlaylistResponse(playlists);
        return respondCreated(response);
    }

    @DELETE
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deletePlaylist(@QueryParam("token") String token, @PathParam("id") int id) {
        System.out.println("Deleting this playlist");
        System.out.println("id: " + id);
        List<Playlist> playlists = service.deletePlaylist(id, token);
        PlaylistResponse response = new PlaylistResponse(playlists);
        return respondOk(response);
    }
}
