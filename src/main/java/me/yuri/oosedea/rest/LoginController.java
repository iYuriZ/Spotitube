package me.yuri.oosedea.rest;

import me.yuri.oosedea.datasource.mo.User;
import me.yuri.oosedea.exceptions.UnauthorizedUserException;
import me.yuri.oosedea.rest.dto.implementation.LoginRequest;
import me.yuri.oosedea.rest.dto.implementation.LoginResponse;
import me.yuri.oosedea.service.LoginService;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/login")
public class LoginController extends Responses {

    @Inject
    LoginService loginService;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response login(LoginRequest loginRequest) {
        try {
            User user = loginService.authenticate(loginRequest.getUser(), loginRequest.getPassword());
            LoginResponse response = new LoginResponse(user.getFirstName(), user.getLastName(), user.getToken());
            return respondOk(response);

        } catch (UnauthorizedUserException e) {
            return respondUnauthorized(e);
        }
    }
}
