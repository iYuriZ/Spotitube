package me.yuri.oosedea.rest;

import me.yuri.oosedea.rest.dto.PlaylistRequest;
import me.yuri.oosedea.rest.dto.PlaylistResponse;
import me.yuri.oosedea.service.PlaylistService;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/playlists")
public class PlaylistController {

    @Inject
    PlaylistService service;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response requestAllPlaylists(PlaylistRequest request) {
        PlaylistResponse response = service.getPlaylists(request.getToken());
        return Response.ok(response).build();
    }

}
