package com.revature.buyNlarge.services;
import com.revature.buyNlarge.daos.UserDAO;
import com.revature.buyNlarge.models.User;
import com.revature.buyNlarge.utils.custom_exceptions.InvalidUserException;

public class UserService {
    private static final UserDAO userDAO = new UserDAO();
    public static void validateUsername(String username) throws InvalidUserException {
        if(!username.matches("^[A-Za-z\\d]{5,15}|[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}$")){
            throw new InvalidUserException("Username must start with a letter and consist of between 5 and 15 alphanumeric characters or be a valid email address.");
        }
    }

    public static void validatePassword(String password) throws InvalidUserException {
        if(!password.matches("^[A-Za-z\\d@$!%*?&]{5,30}$")){
            throw new InvalidUserException("Password must be between 5 and 30 alphanumeric or special characters.");
        }
    }

    public static void resisterUser(User user){
        userDAO.save(user);
    }

    public static User getUserByUsername(String username) {
        return userDAO.getByKey(username);
    }
}
