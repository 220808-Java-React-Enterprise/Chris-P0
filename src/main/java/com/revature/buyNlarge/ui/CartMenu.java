package com.revature.buyNlarge.ui;
import com.revature.buyNlarge.models.Ledger;
import com.revature.buyNlarge.models.Ship;
import com.revature.buyNlarge.services.LedgerService;
import com.revature.buyNlarge.services.ShipService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class CartMenu implements Menu {
    private final UIState uiState;
    public CartMenu(UIState uiState) {
        this.uiState = uiState;
    }

    @Override
    public void display() {
        List<Ship> ships = uiState.getCart();
        loop: while (true) {
            System.out.println();
            for (int i = 0; i < ships.size(); i++) {
                System.out.print("[" + (i + 1) + "] ");
                System.out.print(ships.get(i));
            }
            optionsloop: while (true) {
                switch (Menu.prompt("\nCart Menu\n[1] Checkout\n[2] Remove Items\n[3] Empty Cart\n[x] Exit\n\nChoose an option: ",
                        Arrays.asList("1", "2", "3", "x"))) {
                    case "1":
                        if(checkout()) break loop;
                        break;
                    case "2":
                        if(removeItem()) break loop;
                        break;
                    case "3":
                        if(emptyCart()) break loop;
                        break;
                    case "x":
                        break loop;
                }
            }
        }
    }

    private boolean checkout(){
        for(Ship ship : uiState.getCart()){
            System.out.println(ship);
        }
        loop: while(true){
            switch(Menu.prompt("\nConfirm Purchase? (y/n): ",
                    Arrays.asList("y", "n", "x"))){
                case "y":
                    BigDecimal sum = new BigDecimal("0");
                    for(Ship ship : uiState.getCart()){
                        sum = sum.add(ship.getTotalPrice());
                    }
                    LedgerService.registerLedger(new Ledger(UUID.randomUUID().toString(), uiState.getUser(), LocalDate.now(), sum, uiState.getCart()));
                    uiState.emptyCart();
                    System.out.println("Your account has been billed. Purchase successful. Returning to Main Menu...");
                    return true;
                case "n":
                    return false;
                case "x":
                    return false;
            }
        }
    }
    private boolean removeItem(){
        return false; //TODO finish
        /**
         selectloop:
         while (true) {
         String userInput = Menu.prompt("\nSelect a Ship to add to cart: ");
         if (userInput.equals("x")) {
         break loop;
         }
         int userInt = Integer.parseInt(userInput) - 1;
         if ((userInt < ships.size()) && (userInt >= 0)) {
         uiState.addToCart(ships.get(userInt));
         break;
         }
         }
         */
    }
    private boolean emptyCart(){
         uiState.emptyCart();
        System.out.println("Cart Emptied.");
        return true;
    }
}
