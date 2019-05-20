package me.yuri.oosedea.service;

import me.yuri.oosedea.datasource.dao.UserDAO;
import me.yuri.oosedea.datasource.mo.User;

import javax.inject.Inject;

public class LoginService {

    public LoginService() {}

    @Inject
    private UserDAO userdao;

    public boolean authorized(String user, String password) {

        return false;
    }

}
