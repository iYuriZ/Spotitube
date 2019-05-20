package me.yuri.oosedea.service;

import me.yuri.oosedea.datasource.dao.UserDAO;
import me.yuri.oosedea.datasource.mo.User;
import me.yuri.oosedea.rest.dto.LoginResponse;

import javax.inject.Inject;

public class LoginService {

    public LoginService() {}

    @Inject
    private UserDAO userdao;

    public boolean authorized(String userName, String password) {
        return userdao.findUserByUsername(userName).getPassword().equals(password);
    }

    public LoginResponse getUser(String userName) {
        LoginResponse response = new LoginResponse();
        User user = userdao.findUserByUsername(userName);

        response.setToken(user.getToken());
        response.setUser(user.getFirstName() + " " + user.getLastName());

        return response;
    }

}
