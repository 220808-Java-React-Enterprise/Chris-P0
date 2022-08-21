package com.revature.buyNlarge.ui;
import com.revature.buyNlarge.models.User;
import com.revature.buyNlarge.services.UserService;
import com.revature.buyNlarge.utils.custom_exceptions.InvalidUserException;
import java.util.Arrays;

public class LoginMenu implements Menu {
    private final UIState uiState;
    public LoginMenu(UIState uiState) {
        this.uiState = uiState;
    }

    @Override
    public void display(){
        loop: while(true){
            switch(Menu.prompt("\nLogin Menu\n[1] Login\n[2] Sign up\n[x] Exit\n\nChoose an option: ",
                    Arrays.asList("1", "2", "x"))){
                case "1": //Login
                    if(login()) break loop;
                    break;
                case "2": //Sign up
                    if(signUp()) break loop;
                    break;
                case "x": //Exit
                    System.out.println("Exiting program. Goodbye.");
                    break loop;
            }
        }
    }

    private boolean login(){
        String username;
        String password;
        while(true){
            while(true) {
                username = Menu.prompt("Enter a username: ");
                try {
                    UserService.validateUsername(username);
                    break;
                } catch (InvalidUserException e) {
                    System.out.println(e.getMessage());
                }
            }
            while (true) {
                password = Menu.prompt("Enter a password: ");
                try {
                    UserService.validatePassword(password);
                    break;
                } catch (InvalidUserException e) {
                    System.out.println(e.getMessage());
                }
            }
            try {
                User user = UserService.validateLogin(username, password);
                System.out.println("Logging in...");
                uiState.setUser(user);
                uiState.pushNavigator(new MainMenu(uiState));
                return true;
            } catch (InvalidUserException e){
                System.out.println(e.getMessage());
            }
        }
    }
    private boolean signUp() {
        String username;
        String password;
        while(true){
            while(true) {
                username = Menu.prompt("Enter a username: ");
                try {
                    UserService.validateUsername(username);
                    UserService.checkAvailableUsername(username);
                    break;
                } catch (InvalidUserException e) {
                    System.out.println(e.getMessage());
                }
            }
            while (true) {
                password = Menu.prompt("Enter a password: ");
                try {
                    UserService.validatePassword(password);
                    break;
                } catch (InvalidUserException e) {
                    System.out.println(e.getMessage());
                }
            }
            confirmloop: while (true) {
                switch(Menu.prompt("\nUsername: " + username + "\nPassword: " + password + "\nAre these correct? (y/n): ",
                        Arrays.asList("y", "n", "x"))){
                    case "y":
                        System.out.println("Creating new user...");
                        User user = new User(username, password, false);
                        UserService.resisterUser(user);
                        uiState.setUser(user);
                        uiState.pushNavigator(new MainMenu(uiState));
                        System.out.println("User created. Logging in...");
                        return true;
                    case "n":
                        break confirmloop;
                    case "x":
                        System.out.println("Login failed.\nReturning to login menu...\n");
                        return false;
                }
            }
        }
    }
}
