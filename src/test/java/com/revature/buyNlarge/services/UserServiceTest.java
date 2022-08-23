package com.revature.buyNlarge.services;
import com.revature.buyNlarge.daos.UserDAO;
import com.revature.buyNlarge.models.User;
import com.revature.buyNlarge.utils.custom_exceptions.InvalidUserException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import static org.mockito.Mockito.*;

public class UserServiceTest {
    private UserService sut;
    private final UserDAO mockUserDao = mock(UserDAO.class);

    @Before
    public void setup() {
        sut = new UserService(mockUserDao);
    }

    @Test
    public void test_isValidUsername_givenCorrectUsername() {
        String validUsername = "christhewizard";
        Assert.assertTrue(sut.validateUsername(validUsername));
    }

    @Test(expected = InvalidUserException.class)
    public void test_isNotValidUsername_givenInCorrectUsername() {
        String invalidUsername = "k";
        sut.validateUsername(invalidUsername);
    }

    @Test(expected = InvalidUserException.class)
    public void test_isNotValidUsername_givenEmptyUsername() {
        String invalidUsername = "";
        sut.validateUsername(invalidUsername);
    }

    @Test(expected = InvalidUserException.class)
    public void test_isNotValidUsername_givenInvallidEmail() {
        String invalidUsername = "+@gmail.com";
        sut.validateUsername(invalidUsername);
    }

    @Test(expected = InvalidUserException.class)
    public void test_isNotValidUsername_startingWithUnderscore() {
        String invalidUsername = "_bduong0929";
        sut.validateUsername(invalidUsername);
    }

    @Test
    public void test_login_validLoginGivenCorrectCredentials() {
        UserService spiedSut = Mockito.spy(sut);
        String validUsername = "christhewizard";
        String validPassword = "fnord";
        when(mockUserDao.getUserByUsernameAndPassword(validUsername, validPassword)).thenReturn(new User(validUsername, validPassword, false));
        User user = spiedSut.validateLogin(validUsername, validPassword);
        Assert.assertNotNull(user);
        verify(mockUserDao, times(1)).getUserByUsernameAndPassword(validUsername, validPassword);
    }

    @Test(expected = InvalidUserException.class)
    public void test_login_invalidLoginGivenIncorrectCredentials() {
        UserService spiedSut = Mockito.spy(sut);
        String invalidUsername = "invalid";
        String invalidPassword = "invalid";
        when(mockUserDao.getUserByUsernameAndPassword(invalidUsername, invalidPassword)).thenReturn(null);
        sut.validateLogin(invalidUsername, invalidPassword);
    }
}