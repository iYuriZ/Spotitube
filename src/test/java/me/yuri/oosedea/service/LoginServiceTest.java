package me.yuri.oosedea.service;

import me.yuri.oosedea.datasource.dao.UserDAO;
import me.yuri.oosedea.exceptions.UnauthorizedUserException;
import me.yuri.oosedea.modelobjects.User;
import me.yuri.oosedea.service.implementation.LoginServiceImpl;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class LoginServiceTest {

    @InjectMocks
    private LoginServiceImpl loginService;

    @Mock
    private UserDAO userDAO;

    @Test
    public void testAuthenticateIsAuthorized() throws UnauthorizedUserException {
        User expected = new User();

        expected.setUser("dennis");
        expected.setPassword("dennis");
        expected.setToken("0891-bva2-he7d");
        expected.setFirstName("Dennis");
        expected.setLastName("Breuker");

        Mockito.when(userDAO.findUserByUsername("dennis")).thenReturn(expected);

        User actual = loginService.authenticate("dennis", "dennis");

        Assert.assertEquals(expected, actual);
    }

    @Test(expected = UnauthorizedUserException.class)
    public void testAuthenticateThrowsExceptionOnWrongPassword() throws UnauthorizedUserException {
        User user = new User();

        user.setUser("dennis");
        user.setPassword("dennis");
        user.setToken("0891-bva2-he7d");
        user.setFirstName("Dennis");
        user.setLastName("Breuker");

        Mockito.when(userDAO.findUserByUsername("dennis")).thenReturn(user);
        User actual = loginService.authenticate("dennis", "ppp");
    }

    @Test(expected = UnauthorizedUserException.class)
    public void testAuthenticateThrowsExceptionOnNull() throws UnauthorizedUserException {
        Mockito.when(userDAO.findUserByUsername("dennis")).thenReturn(null);
        User actual = loginService.authenticate("dennis", "ppp");
    }

    @Test
    public void testAuthorizeByTokenIsAuthorized() throws UnauthorizedUserException {
        User user = new User();

        user.setUser("dennis");
        user.setPassword("dennis");
        user.setToken("0891-bva2-he7d");
        user.setFirstName("Dennis");
        user.setLastName("Breuker");

        Mockito.when(userDAO.findUserByToken("0891-bva2-he7d")).thenReturn(user);

        boolean actual = loginService.authorizeByToken("0891-bva2-he7d");
        Assert.assertTrue(actual);
    }

    @Test(expected = UnauthorizedUserException.class)
    public void testAuthorizeByTokenThrowsException() throws UnauthorizedUserException {
        Mockito.when(userDAO.findUserByToken("0000-0000-0000")).thenReturn(null);
        boolean actual = loginService.authorizeByToken("0000-0000-0000");
    }
}
