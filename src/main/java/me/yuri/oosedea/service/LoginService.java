package me.yuri.oosedea.service;

import me.yuri.oosedea.modelobjects.User;
import me.yuri.oosedea.exceptions.UnauthorizedUserException;

public interface LoginService {

    User authenticate(String username, String password) throws UnauthorizedUserException;

    boolean authorizeByToken(String token) throws UnauthorizedUserException;

}
