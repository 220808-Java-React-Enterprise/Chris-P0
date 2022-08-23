package com.revature.buyNlarge.ui;
import com.revature.buyNlarge.models.User;
import com.revature.buyNlarge.services.UserService;

import java.util.List;

public class UsersMenu implements Menu {
    private final UIState uiState;
    public UsersMenu(UIState uiState){
        this.uiState = uiState;
    }

    @Override
    public void display() {
        List<User> users = UserService.getAllUsers();
        if(users.size() == 0){
            System.out.println("No users found. Exiting...");
        }
        loop: while (users.size() > 0) {
            System.out.println("\nUsers:\n");
            for(int i = 0; i < users.size(); i++){
                System.out.print("[" + (i + 1) + "] ");
                System.out.println(users.get(i).getUsername());
            }
            selectloop: while(true) {
                String userInput = Menu.prompt("\nSelect a User to view: ");
                if(userInput.equals("x")){
                    break loop;
                }
                int userInt = Integer.parseInt(userInput) - 1;
                if ((userInt < users.size()) && (userInt >= 0)) {
                    uiState.pushNavigator(this);
                    uiState.pushNavigator(new UserDetailsMenu(uiState, users.get(userInt)));
                    break loop;
                }
            }
        }
    }
}
