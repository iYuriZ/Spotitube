package me.yuri.oosedea.rest.dto.implementation;

import me.yuri.oosedea.rest.dto.DTO;

public class LoginResponse implements DTO {
    private String user;
    private String token;

    public LoginResponse(String firstName, String lastName, String token) {
        user = firstName + " " + lastName;
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setUser(String user) { this.user = user; }
}
