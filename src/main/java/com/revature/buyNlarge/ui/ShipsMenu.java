package com.revature.buyNlarge.ui;
import com.revature.buyNlarge.models.Ship;
import java.util.List;

public class ShipsMenu implements Menu {
    private final UIState uiState;
    private final List<Ship> ships;
    public ShipsMenu(UIState uiState, List<Ship> ships){
        this.uiState = uiState;
        this.ships = ships;
    }

    @Override
    public void display() {
        while (true) {
            if(ships.size() == 0){
                System.out.println("No ships for sale. Returning to Main Menu...");
                break;
            }
            System.out.println("\nAvailable Ships:\n");
            for(int i = 0; i < ships.size(); i++){
                System.out.print("[" + (i + 1) + "] ");
                System.out.println(ships.get(i));
            }
            String userInput = Menu.prompt("Select a Ship to add to cart or 'x' to finish: ");
            if(userInput.equals("x")){
                break;
            }
            try {
                int userInt = Integer.parseInt(userInput) - 1;
                if ((userInt < ships.size()) && (userInt >= 0)) {
                    uiState.addToCart(ships.get(userInt));
                    ships.remove(ships.get(userInt));
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input.");
            }
        }
        if(!uiState.cartIsEmpty()){
            uiState.pushNavigator(new CartMenu(uiState));
        }
    }
}
