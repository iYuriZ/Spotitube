package me.yuri.oosedea.rest;

import me.yuri.oosedea.exceptions.UnauthorizedUserException;
import me.yuri.oosedea.rest.dto.DTO;

import javax.ws.rs.core.Response;

abstract class Responses {

    Response respondOk(DTO response) {
        return Response.ok(response).build();
    }

    Response respondCreated(DTO response) {
        return Response.status(201).entity(response).build();
    }

    Response respondUnauthorized(UnauthorizedUserException e) {
        return Response.status(401).entity(e).build();
    }

}
