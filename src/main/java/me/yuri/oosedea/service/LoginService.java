package me.yuri.oosedea.service;

import me.yuri.oosedea.datasource.dao.UserDAO;
import me.yuri.oosedea.datasource.mo.User;
import me.yuri.oosedea.exceptions.UnauthorizedUserException;


import javax.inject.Inject;

public class LoginService {

    public LoginService() {}

    @Inject
    private UserDAO userDAO;

    public User authenticate(String username, String password) throws UnauthorizedUserException {

        User user = userDAO.findUserByUsername(username);

        if(user == null)
            throw new UnauthorizedUserException();

        if (!user.getPassword().equals(password))
            throw new UnauthorizedUserException();

        return user;
    }
}
