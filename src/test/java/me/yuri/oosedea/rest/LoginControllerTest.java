package me.yuri.oosedea.rest;

import me.yuri.oosedea.exceptions.UnauthorizedUserException;
import me.yuri.oosedea.modelobjects.User;
import me.yuri.oosedea.rest.dto.implementation.LoginRequest;
import me.yuri.oosedea.rest.dto.implementation.LoginResponse;
import me.yuri.oosedea.service.LoginService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import javax.ws.rs.core.Response;

@RunWith(MockitoJUnitRunner.class)
public class LoginControllerTest {

    @Mock
    private LoginService loginService;

    @InjectMocks
    private LoginController loginController;

    private LoginRequest loginRequest;

    @Before
    public void setup() {
        loginRequest = new LoginRequest();
    }

    @Test
    public void testLoginWithCorrectUser() throws UnauthorizedUserException {
        loginRequest.setUser("dennis");
        loginRequest.setPassword("dennis");

        LoginResponse loginResponse = new LoginResponse("Dennis", "Breuker", "0891-bva2-he7d");
        User user = new User();

        user.setUser("dennis");
        user.setPassword("dennis");
        user.setToken("0891-bva2-he7d");
        user.setFirstName("Dennis");
        user.setLastName("Breuker");

        Mockito.when(loginService.authenticate("dennis", "dennis")).thenReturn(user);

        Response actual = loginController.login(loginRequest);
        Response expected = Response.ok(loginResponse).build();

        String expectedUser = "Dennis Breuker";
        String actualUser = loginResponse.getUser();

        String expectedToken = "0891-bva2-he7d";
        String actualToken = loginResponse.getToken();

        Assert.assertEquals(expectedUser, actualUser);
        Assert.assertEquals(expectedToken, actualToken);
        Assert.assertEquals(expected.getStatus(), actual.getStatus());
    }

    @Test
    public void testLoginWithWrongUserThrowsException() throws UnauthorizedUserException {
        loginRequest.setUser("user");
        loginRequest.setPassword("password");

        Mockito.when(loginService.authenticate("user", "password")).thenThrow(UnauthorizedUserException.class);

        Response actual = loginController.login(loginRequest);
        Assert.assertEquals(401, actual.getStatus());
    }
}
