package me.yuri.oosedea.service;

import me.yuri.oosedea.datasource.dao.UserDAO;
import me.yuri.oosedea.datasource.mo.User;
<<<<<<< HEAD
import me.yuri.oosedea.exceptions.UnauthorizedUserException;
=======
import me.yuri.oosedea.rest.dto.LoginResponse;
>>>>>>> 40ea2af9775ef4629d7ac057ff86d0e86f850376

import javax.inject.Inject;

public class LoginService {

    public LoginService() {}

    @Inject
    private UserDAO userDAO;

    public User authenticate(String username, String password) throws UnauthorizedUserException {
        User user = userDAO.findByUserName(username);
        if(user == null)
            throw new UnauthorizedUserException();

        boolean passMatch = PasswordHashService.checkPassword(password, storedUser.getPassword());

        if (!passMatch)
            throw new UnauthorizedUserException();

        storedUser.issueToken();
        userDAO.updateToken(storedUser);

        return storedUser;
    }
}
