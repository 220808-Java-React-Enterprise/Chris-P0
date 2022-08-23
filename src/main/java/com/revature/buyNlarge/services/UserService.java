package com.revature.buyNlarge.services;
import com.revature.buyNlarge.daos.UserDAO;
import com.revature.buyNlarge.models.User;
import com.revature.buyNlarge.utils.custom_exceptions.InvalidUserException;

import java.util.List;

public class UserService {
    private static UserDAO userDAO = new UserDAO();

    UserService(UserDAO userDAO){
        this.userDAO = userDAO;
    }
    public static boolean validateUsername(String username) throws InvalidUserException {
        if(!username.matches("^([A-Za-z\\d]{3,15}|[A-Za-z0-9][A-Za-z0-9!#$%&'*+\\-/=?^_`{}|]{0,63}@[A-Za-z0-9.-]{1,253}.[A-Za-z]{2,24})$")){
            throw new InvalidUserException("Username must start with a letter and consist of between 3 and 15 alphanumeric characters or be a valid email address.");
        }
        return true;
    }

    public static boolean validatePassword(String password) throws InvalidUserException {
        if(!password.matches("^[A-Za-z\\d@$!%*?&]{5,30}$")){
            throw new InvalidUserException("Password must be between 5 and 30 alphanumeric or special characters.");
        }
        return true;
    }

    public static void resisterUser(User user){
        userDAO.save(user);
    }

    public static User getUserByUsername(String username) {
        return userDAO.getByKey(username);
    }

    public static void checkAvailableUsername(String username) throws InvalidUserException {
        if (userDAO.getByKey(username) != null){
            throw new InvalidUserException("Username is already taken, please choose another.");
        }
    }

    public static User validateLogin(String username, String password) {
        User user = userDAO.getUserByUsernameAndPassword(username, password);
        if(user == null){
            //TODO tell why unsuccessful.
            throw new InvalidUserException("Login unsuccessful. Please check username and password.");
        }
        return user;
    }

    public static List<User> getAllUsers() {
        return userDAO.getAll();
    }
}
