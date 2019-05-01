package me.yuri.oosedea.rest.dto;

public class LoginResponse {
    private String user;
    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setUser(String user) { this.user = user; }
}
