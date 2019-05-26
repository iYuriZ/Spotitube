package me.yuri.oosedea.rest;

import me.yuri.oosedea.rest.dto.implementation.LoginRequest;
import me.yuri.oosedea.service.implementation.LoginServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class LoginControllerTest {

    @Mock
    private LoginServiceImpl loginService;

    @InjectMocks
    private LoginController loginController;
    private LoginRequest loginRequest;

    @Before
    public void setup() {
        loginRequest = new LoginRequest();
    }

    @Test
    public void testLoginReturns200() {

    }

}
