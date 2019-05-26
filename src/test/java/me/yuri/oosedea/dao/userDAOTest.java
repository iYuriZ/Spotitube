package me.yuri.oosedea.dao;

import me.yuri.oosedea.datasource.dao.UserDAO;
import me.yuri.oosedea.modelobjects.User;
import me.yuri.oosedea.service.DatabaseConnection;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@RunWith(MockitoJUnitRunner.class)
public class userDAOTest {

    @Mock
    private DatabaseConnection db;

    @InjectMocks
    private UserDAO userDAO;

    @Before
    public void setup() throws SQLException, IOException {
        Connection connection = TestMockDatabaseConnection.getConnection();

        Mockito.when(db.getDBConnection()).thenReturn(connection);
    }

    @Test
    public void testFindByUsernameOnCorrectUser() {
        User actual = userDAO.findUserByUsername("dennis");
        User expected = new User();
        expected.setUser("dennis");
        expected.setPassword("dennis");
        expected.setToken("0891-bva2-he7d");
        expected.setFirstName("Dennis");
        expected.setLastName("Breuker");

        System.out.println("Actual user: " + actual.getUser());
        System.out.println("Actual password: " + actual.getPassword());
        System.out.println("Actual token: " + actual.getToken());
        System.out.println("Actual firstname: " + actual.getFirstName());
        System.out.println("Actual lastname: " + actual.getLastName());
        System.out.println("Expected user: " + expected.getUser());
        System.out.println("Expected password: " + expected.getPassword());
        System.out.println("Expected token: " + expected.getToken());
        System.out.println("Expected firstname: " + expected.getFirstName());
        System.out.println("Expected lastname: " + expected.getLastName());

        System.out.println(expected.getUser() + " " + actual.getUser());
        System.out.println(expected.getPassword() + " " + actual.getPassword());
        System.out.println(expected.getToken() + " " + actual.getToken());
        System.out.println(expected.getFirstName() + " " + actual.getFirstName());
        System.out.println(expected.getLastName() + " " + actual.getLastName());

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testFindByUsernameOnNoUserFound() {
        User actual = userDAO.findUserByUsername("qweqfwefweweggwe");
        Assert.assertNull(actual);
    }

    @Test
    public void testFindByTokenOnCorrectUser() {
        User actual = userDAO.findUserByToken("0891-bva2-he7d");
        User expected = new User();
        expected.setUser("dennis");
        expected.setPassword("dennis");
        expected.setToken("0891-bva2-he7d");
        expected.setFirstName("Dennis");
        expected.setLastName("Breuker");

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testFindByTokenOnNoUserFound() {
        User actual = userDAO.findUserByToken("0000-bva2-he7d");
        Assert.assertNull(actual);
    }
}
