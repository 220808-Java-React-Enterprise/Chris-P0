package com.revature.buyNlarge.ui;

import com.revature.buyNlarge.models.Ship;
import com.revature.buyNlarge.models.Shipyard;
import com.revature.buyNlarge.services.ShipService;
import com.revature.buyNlarge.services.ShipyardService;

import java.util.Arrays;
import java.util.List;

public class ShipyardsMenu implements Menu {
    private final UIState uiState;
    public ShipyardsMenu(UIState uiState){
        this.uiState = uiState;
    }

    @Override
    public void display() {
        List<Shipyard> shipyards = ShipyardService.getAllShipyards();
        if(shipyards.size() == 0){
            System.out.println("No ships for sale. Returning to Main Menu...");
            uiState.pushNavigator(new MainMenu(uiState));
        }
        loop: while (shipyards.size() > 0) {
            System.out.println("\nShipyards:\n");
            for(int i = 0; i < shipyards.size(); i++){
                System.out.print("[" + (i + 1) + "] ");
                System.out.println(shipyards.get(i).getName());
            }
            selectloop: while(true) {
                String userInput = Menu.prompt("\nSelect a Shipyard to view: ");
                if(userInput.equals("x")){
                    break loop;
                }
                int userInt = Integer.parseInt(userInput) - 1;
                if ((userInt < shipyards.size()) && (userInt >= 0)) {
                    System.out.println(shipyards.get(userInt));
                    uiState.pushNavigator(this);
                    uiState.pushNavigator(new ShipsMenu(uiState, ShipService.getAllAvailableShipsByShipyardID(shipyards.get(userInt).getID())));
                    break loop;
                }
            }
        }
    }
}
