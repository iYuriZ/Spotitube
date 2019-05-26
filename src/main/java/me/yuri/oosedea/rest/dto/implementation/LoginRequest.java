package me.yuri.oosedea.rest.dto.implementation;

import me.yuri.oosedea.rest.dto.DTO;

public class LoginRequest implements DTO {

    private String user;
    private String password;

    public LoginRequest () {}

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getUser() {
        return user;
    }
    public void setUser(String user) {
        this.user = user;
    }

}
