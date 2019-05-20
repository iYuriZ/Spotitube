package me.yuri.oosedea.rest;

import me.yuri.oosedea.rest.dto.LoginRequest;
import me.yuri.oosedea.rest.dto.LoginResponse;
import me.yuri.oosedea.service.LoginService;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/login")
public class LoginController {

    @Inject
    LoginService loginService;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response login(LoginRequest loginRequest) {
        LoginResponse loginResponse = new LoginResponse();
        if (loginService.authorized(loginRequest.getUser(), loginRequest.getPassword())) {
            loginResponse.setToken();
            loginResponse.setUser(loginRequest.getUser());
            return Response.ok(loginResponse).build();
        } else {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
    }
}
