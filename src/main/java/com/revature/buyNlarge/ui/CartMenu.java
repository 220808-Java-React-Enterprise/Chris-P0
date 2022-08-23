package com.revature.buyNlarge.ui;
import com.revature.buyNlarge.models.Ledger;
import com.revature.buyNlarge.models.Ship;
import com.revature.buyNlarge.services.LedgerService;
import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.UUID;

public class CartMenu implements Menu {
    private final UIState uiState;
    public CartMenu(UIState uiState) {
        this.uiState = uiState;
    }

    @Override
    public void display() {
        loop: while (true) {
            System.out.println("\nCart:");
            if(uiState.cartIsEmpty()){
                System.out.println("No ships in cart.");
            }
            for (Ship ship : uiState.getCart()) {
                System.out.print(ship);
            }
            System.out.println("Total: " + NumberFormat.getCurrencyInstance().format(uiState.getCartTotal()));
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

    private boolean checkout(){
        for(Ship ship : uiState.getCart()){
            System.out.print(ship);
        }
        System.out.println("Total: " + NumberFormat.getCurrencyInstance().format(uiState.getCartTotal()));
        loop: while(true){
            switch(Menu.prompt("\nConfirm Purchase? (y/n): ",
                    Arrays.asList("y", "n", "x"))){
                case "y":
                    LedgerService.registerLedger(new Ledger(UUID.randomUUID().toString(), uiState.getUser(), LocalDateTime.now(), uiState.getCartTotal(), uiState.getCart()));
                    uiState.emptyCart();
                    System.out.println("Your account has been billed " + NumberFormat.getCurrencyInstance().format(uiState.getCartTotal()) + ". Purchase successful. Returning to Main Menu...");
                    return true;
                case "n":
                    return false;
                case "x":
                    return false;
            }
        }
    }
    private boolean removeItem(){
        while(true){
            if(uiState.cartIsEmpty()){
                System.out.println("Cart Emptied. Returning to Main Menu...");
                return true;
            }
            System.out.println("\nShips in cart:\n");
            for(int i = 0; i < uiState.getCart().size(); i++){
                System.out.print("[" + (i + 1) + "] ");
                System.out.print(uiState.getCart().get(i));
            }
        System.out.println("Total: " + NumberFormat.getCurrencyInstance().format(uiState.getCartTotal()));
            String userInput = Menu.prompt("\nSelect a Ship to remove from cart or 'x' to finish: ");
            if(userInput.equals("x")){
                return false;
            }
            try {
                int userInt = Integer.parseInt(userInput) - 1;
                if ((userInt < uiState.getCart().size()) && (userInt >= 0)) {
                    System.out.println("Ship " + uiState.getCart().get(userInt).getID() + " has been removed from cart.");
                    uiState.removeFromCart(uiState.getCart().get(userInt));
                }
            }catch (NumberFormatException e){
                System.out.println("Invalid input.");
            }
        }
    }
    private boolean emptyCart(){
        uiState.emptyCart();
        System.out.println("Cart Emptied. Returning to Main Menu...");
        return true;
    }
}
