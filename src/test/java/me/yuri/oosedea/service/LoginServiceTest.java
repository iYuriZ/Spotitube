package me.yuri.oosedea.service;

import me.yuri.oosedea.exceptions.UnauthorizedUserException;
import me.yuri.oosedea.modelobjects.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class LoginServiceTest {

    @Mock
    private LoginService loginService;

    @Test
    public void testAuthenticateIsAuthorized() throws UnauthorizedUserException {
        User actual = loginService.authenticate("dennis", "dennis");

        User expected = new User();
        expected.setUser("dennis");
        expected.setPassword("dennis");
        expected.setToken("0891-bva2-he7d");
        expected.setFirstName("Dennis");
        expected.setLastName("Breuker");

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testAuthorizeByTokenIsAuthorized() throws UnauthorizedUserException {
        boolean actual = loginService.authorizeByToken("0891-bva2-he7d");
        Assert.assertTrue(actual);
    }
}
