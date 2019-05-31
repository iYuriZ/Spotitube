package me.yuri.oosedea.service.implementation;

import me.yuri.oosedea.datasource.dao.UserDAO;
import me.yuri.oosedea.modelobjects.User;
import me.yuri.oosedea.exceptions.UnauthorizedUserException;
import me.yuri.oosedea.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {

    public LoginServiceImpl() {}

    @Autowired
    private UserDAO userDAO;

    @Override
    public User authenticate(String username, String password) throws UnauthorizedUserException {

        User user = userDAO.findUserByUsername(username);

        if(user == null)
            throw new UnauthorizedUserException();

        if (!user.getPassword().equals(password))
            throw new UnauthorizedUserException();

        return user;
    }

    @Override
    public boolean authorizeByToken(String token) throws UnauthorizedUserException {
        User user = userDAO.findUserByToken(token);
        if (user == null) {
            throw new UnauthorizedUserException();
        }
        return true;
    }
}
