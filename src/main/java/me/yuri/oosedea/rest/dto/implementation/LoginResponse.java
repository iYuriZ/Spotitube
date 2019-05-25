package me.yuri.oosedea.rest.dto.implementation;

import me.yuri.oosedea.rest.dto.DTO;

public class LoginResponse implements DTO {

    private String token;
    private String user;

    public LoginResponse(String firstName, String lastName, String token) {
        user = firstName + " " + lastName;
        this.token = token;
    }

    public String getToken() { return token; }
    public String getUser() { return user; }
}
