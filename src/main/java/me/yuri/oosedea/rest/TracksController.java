package me.yuri.oosedea.rest;

import me.yuri.oosedea.datasource.mo.Track;
import me.yuri.oosedea.rest.dto.implementation.TracksListResponse;
import me.yuri.oosedea.service.TracksService;

import javax.inject.Inject;
import javax.print.attribute.standard.Media;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/tracks")
public class TracksController extends Responses {

    @Inject
    TracksService tracksService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getAllTracksNotInPlaylist(@QueryParam("forPlaylist") int playlistId, @QueryParam("token") String token) {
        List<Track> tracks = tracksService.getAllTracksNotInPlaylist(playlistId);
        TracksListResponse response = new TracksListResponse(tracks);
        return respondOk(response);
    }
}
