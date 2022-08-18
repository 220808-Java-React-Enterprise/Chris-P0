package com.revature.buyNlarge.ui;
import com.revature.buyNlarge.models.User;
import com.revature.buyNlarge.services.UserService;
import com.revature.buyNlarge.utils.custom_exceptions.InvalidUserException;
import java.util.Scanner;

public class LoginMenu implements Menu {
    private final UIState uiState;
    public LoginMenu(UIState uiState) {
        this.uiState = uiState;
    }

    @Override
    public void display(){
        String userInput = "";
        Scanner scanner = new Scanner(System.in);
        mainloop: while(true){
            System.out.print("\nLogin Menu\n[1] Login\n[2] Sign up\n[x] Exit\n\nChoose an option: ");
            userInput = scanner.nextLine();
            userInput = userInput.toLowerCase();
            switch(userInput){
                case "1":
                    if(login()){
                        break mainloop;
                    }else{
                        System.out.println("Login failed.\nReturning to login menu...\n");
                        break;
                    }
                case "2":
                    if(signUp()){
                        break mainloop;
                    }else{
                        System.out.println("Sign up failed.\nReturning to login menu...\n");
                        break;
                    }
                case "x":
                    break mainloop;
                default:
                    System.out.print("\nIncorrect Input. Try again.\n");
            }
        }
    }

    private boolean signUp() {
        String username;
        String password;
        String userInput = "";
        Scanner scanner = new Scanner(System.in);
        while (true) {
            usernameloop:
            while (true) {
                System.out.println("Enter a username: ");
                userInput = scanner.nextLine();
                if (userInput.toLowerCase().equals("x")) {
                    return false;
                }
                try {
                    UserService.validateUsername(userInput);
                    UserService.checkAvailableUsername(userInput);
                    username = userInput;
                    break usernameloop;
                } catch (InvalidUserException e) {
                    System.out.println(e.getMessage());
                }
            }
            passwordloop:
            while (true) {
                System.out.println("Enter a password: ");
                userInput = scanner.nextLine();
                if (userInput.toLowerCase().equals("x")) {
                    return false;
                }
                try {
                    UserService.validatePassword(userInput);
                    password = userInput;
                    break passwordloop;
                } catch (InvalidUserException e) {
                    System.out.println(e.getMessage());
                }
            }
            confirmloop:
            while (true) {
                System.out.print("\nUsername: " + username + "\nPassword: " + password + "\nAre these correct? (y/n): ");
                userInput = scanner.nextLine();
                userInput = userInput.toLowerCase();
                switch (userInput) {
                    case "y":
                    case "yes":
                        System.out.println("Creating new user...");
                        User user = new User(username, password, false);
                        UserService.resisterUser(user);
                        uiState.setUser(user);
                        System.out.println("User created. Logging in...");
                        return true;
                    case "n":
                    case "no":
                        break confirmloop;
                    case "x":
                        return false;
                }
            }
        }
    }

    private boolean login(){
        String username;
        String password;
        String userInput = "";
        Scanner scanner = new Scanner(System.in);
        while (true) {
            usernameloop:
            while (true) {
                System.out.println("Enter a username: ");
                userInput = scanner.nextLine();
                if (userInput.toLowerCase().equals("x")) {
                    return false;
                }
                try {
                    UserService.validateUsername(userInput);
                    username = userInput;
                    break usernameloop;
                } catch (InvalidUserException e) {
                    System.out.println(e.getMessage());
                }
            }
            passwordloop:
            while (true) {
                System.out.println("Enter a password: ");
                userInput = scanner.nextLine();
                if (userInput.toLowerCase().equals("x")) {
                    return false;
                }
                try {
                    UserService.validatePassword(userInput);
                    password = userInput;
                    break passwordloop;
                } catch (InvalidUserException e) {
                    System.out.println(e.getMessage());
                }
            }
            try {
                User user = UserService.validateLogin(username, password);
                uiState.setUser(user);
                return true;
            } catch (InvalidUserException e){
                System.out.println(e.getMessage());
            }
        }
    }
}
