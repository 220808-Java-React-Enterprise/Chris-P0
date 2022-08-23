package com.revature.buyNlarge.services;
import com.revature.buyNlarge.daos.UserDAO;
import com.revature.buyNlarge.models.User;
import com.revature.buyNlarge.utils.custom_exceptions.InvalidUserException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Arrays;

import static org.mockito.Mockito.*;

public class UserServiceTest {
    private UserService sut;
    private final UserDAO mockUserDao = mock(UserDAO.class);

    @Before
    public void setup() {
        sut = new UserService(mockUserDao);
    }

    @Test
    public void test_validateUsername_givenCorrectUsername() {
        String validUsername = "christhewizard";
        Assert.assertTrue(sut.validateUsername(validUsername));
    }

    @Test(expected = InvalidUserException.class)
    public void test_validateUsername_givenTooShortUsername() {
        String invalidUsername = "k";
        sut.validateUsername(invalidUsername);
    }

    @Test(expected = InvalidUserException.class)
    public void test_validateUsername_givenTooLongUsername() {
        String invalidUsername = "ChrisTheAlmightyCoderExtraordinar";
        sut.validateUsername(invalidUsername);
    }

    @Test(expected = InvalidUserException.class)
    public void test_validateUsername_givenEmptyUsername() {
        String invalidUsername = "";
        sut.validateUsername(invalidUsername);
    }

    @Test(expected = java.lang.NullPointerException.class)
    public void test_validateUsername_givenNull() {
        String validUsername = null;
        Assert.assertTrue(sut.validateUsername(validUsername));
    }

    @Test(expected = InvalidUserException.class)
    public void test_validateUsername_givenInvalidEmail() {
        String invalidUsername = "a@gmail.c";
        sut.validateUsername(invalidUsername);
    }

    @Test(expected = InvalidUserException.class)
    public void test_validateUsername_givenTooLongEmail() {
        String invalidUsername = "DaenerysStormbornOfHouseTargaryenTheFirstOfHerNameQueenOfTheAndalsandTheFirstMenProtectorOfTheSevenKingdomsTheMotherOfDragonsTheKhaleesiOfTheGreatGrassSeaTheUnburntTheBreakerOfChains@gmail.com";
        sut.validateUsername(invalidUsername);
    }

    @Test(expected = InvalidUserException.class)
    public void test_validateUsername_startingWithUnderscore() {
        String invalidUsername = "_bduong0929";
        sut.validateUsername(invalidUsername);
    }

    @Test
    public void test_validatePassword_givenCorrectPassword() {
        String validPassword = "password";
        Assert.assertTrue(sut.validatePassword(validPassword));
    }

    @Test(expected = InvalidUserException.class)
    public void test_validatePassword_givenTooShortPassword() {
        String validPassword = "pw";
        Assert.assertTrue(sut.validatePassword(validPassword));
    }

    @Test(expected = InvalidUserException.class)
    public void test_validatePassword_givenTooLongPassword() {
        String validPassword = "CorrectHorseBatteryStapleCorrectHorseBatteryStapleCorrectHorseBatteryStapleCorrectHorseBatteryStaple";
        Assert.assertTrue(sut.validatePassword(validPassword));
    }

    @Test(expected = InvalidUserException.class)
    public void test_validatePassword_givenPasswordWithWrongCharacters() {
        String validPassword = "password~";
        Assert.assertTrue(sut.validatePassword(validPassword));
    }

    @Test(expected = java.lang.NullPointerException.class)
    public void test_validatePassword_givenNull() {
        String validPassword = null;
        Assert.assertTrue(sut.validatePassword(validPassword));
    }

    @Test(expected = InvalidUserException.class)
    public void test_validatePassword_givenEmptyString() {
        String validPassword = "";
        Assert.assertTrue(sut.validatePassword(validPassword));
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

    @Test
    public void test_checkAvailableUsername_givenAvailableUsername(){
        UserService spiedSut = Mockito.spy(sut);
        when(mockUserDao.getByKey("newUsername")).thenReturn(null);
        sut.checkAvailableUsername("newUsername");
    }

    @Test(expected = InvalidUserException.class)
    public void test_checkUnavailableUsername_givenAvailableUsername(){
        UserService spiedSut = Mockito.spy(sut);
        when(mockUserDao.getByKey("newUsername")).thenReturn(new User("newUsername", "password", false));
        sut.checkAvailableUsername("newUsername");
    }

    @Test
    public void test_getAllUsers(){
        UserService spiedSut = Mockito.spy(sut);
        when(mockUserDao.getAll()).thenReturn(Arrays.asList(new User("newUsername", "password", false), new User("tester", "password", false)));
        assert(sut.getAllUsers().size() == 2);
    }
}