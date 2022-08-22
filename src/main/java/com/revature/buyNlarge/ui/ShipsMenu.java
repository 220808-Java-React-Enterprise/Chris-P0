package com.revature.buyNlarge.ui;
import com.revature.buyNlarge.models.Ship;
import java.util.Arrays;
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
        if(ships.size() == 0){
            System.out.println("No ships for sale. Returning to Main Menu...");
        }
        loop: while (ships.size() > 0) {
            System.out.println("\nAvailable Ships:\n");
            for(int i = 0; i < ships.size(); i++){
                System.out.print("[" + (i + 1) + "] ");
                System.out.println(ships.get(i));
            }
            selectloop: while(true) {
                String userInput = Menu.prompt("\nSelect a Ship to add to cart: ");
                if(userInput.equals("x")){
                    break loop;
                }
                int userInt = Integer.parseInt(userInput) - 1;
                if ((userInt < ships.size()) && (userInt >= 0)) {
                    uiState.addToCart(ships.get(userInt));
                    break;
                }
            }
            keepshoppingloop: while(true){
                switch(Menu.prompt("\nKeep shopping? (y/n): ",
                        Arrays.asList("y", "n", "x"))){
                    case "y":
                        break keepshoppingloop;
                    case "n":
                        uiState.pushNavigator(new CartMenu(uiState));
                        break loop;
                    case "x":
                        break loop;
                }
            }
        }
    }
}
