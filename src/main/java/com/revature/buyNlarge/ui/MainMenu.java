package com.revature.buyNlarge.ui;

import com.revature.buyNlarge.models.Ship;
import com.revature.buyNlarge.services.ShipService;
import com.revature.buyNlarge.services.ShipyardService;
import com.revature.buyNlarge.utils.ShipFactory;

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
            switch(!uiState.getUser().isAdmin() ? Menu.prompt("\nMain Menu\n[1] Ships\n[2] Shipyards\n[3] Users\n[4] My Information\n[5] Settings\n[x] Exit\n\nChoose an option: ",
                    Arrays.asList("1", "2", "3", "4", "5", "x")) : Menu.prompt("\nMain Menu\n[1] Ships\n[2] Shipyards\n[3] Users\n[4] My Information\n[5] Settings\n[6] Admin Options\n[x] Exit\n\nChoose an option: ",
                    Arrays.asList("1", "2", "3", "4", "5", "6", "x"))){
                case "1": //Ships
                    uiState.pushNavigator(this);
                    uiState.pushNavigator(new ShipsMenu(uiState, ShipService.getAllAvailableShips()));
                    break loop;
                case "2": //Shipyards
                    uiState.pushNavigator(this);
                    uiState.pushNavigator(new ShipyardsMenu(uiState));
                    break loop;
                case "3": //Users
                    break;
                case "4": //My Information
                    uiState.pushNavigator(this);
                    uiState.pushNavigator(new OrderHistoryMenu(uiState, uiState.getUser()));
                    break loop;
                case "5": //Settings
                    break;
                case "6":
                    //TODO Remove this
                    //System.out.println(UUID.randomUUID().toString());
                    for(int i = 0; i < 10; i++){
                        ShipFactory.createRandomShip();
                    }
                    break;
                case "x": //Exit
                    System.out.println("Exiting program. Goodbye.");
                    break loop;
            }
        }
    }
}
