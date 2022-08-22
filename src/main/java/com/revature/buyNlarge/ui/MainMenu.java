package com.revature.buyNlarge.ui;

import com.revature.buyNlarge.models.Ship;
import com.revature.buyNlarge.services.ShipService;

import java.util.Arrays;
import java.util.UUID;

public class MainMenu implements Menu {
    private final UIState uiState;

    public MainMenu(UIState uiState){
        this.uiState = uiState;
    }
    @Override
    public void display(){
        loop: while(true){
            switch(Menu.prompt("\nMain Menu\n[1] Ships\n[2] Shipyards\n[3] Users\n[4] My Information\n[5] Settings\n[x] Exit\n\nChoose an option: ",
                    Arrays.asList("1", "2", "3", "4", "5", "x"))){
                case "1": //Ships
                    //if(login()) break loop;
                    //TODO: Remove this
                    //System.out.println(UUID.randomUUID().toString());
                    for(Ship ship : ShipService.getAllShips()){
                        System.out.println(ship);
                    }
                    break;
                case "2": //Shipyards
                    //if(signUp()) break loop;
                    break;
                case "3": //Users
                    break;
                case "4": //My Information
                    break;
                case "5": //Settings
                    break;
                case "x": //Exit
                    System.out.println("Exiting program. Goodbye.");
                    break loop;
            }
        }
    }
}
