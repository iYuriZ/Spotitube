package me.yuri.oosedea.rest;

import me.yuri.oosedea.modelobjects.User;
import me.yuri.oosedea.exceptions.UnauthorizedUserException;
import me.yuri.oosedea.rest.dto.implementation.LoginRequest;
import me.yuri.oosedea.rest.dto.implementation.LoginResponse;
import me.yuri.oosedea.service.LoginService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("login")
@Controller
public class LoginController extends Responses {

    @Autowired
    private LoginService loginService;

    @PostMapping("")
    public ResponseEntity login(LoginRequest loginRequest) {
        try {
            User user = loginService.authenticate(loginRequest.getUser(), loginRequest.getPassword());
            LoginResponse response = new LoginResponse(user.getFirstName(), user.getLastName(), user.getToken());
            return respondOk(response);

        } catch (UnauthorizedUserException e) {
            return respondUnauthorized(e);
        }
    }
}
