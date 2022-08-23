package com.revature.buyNlarge.ui;
import java.util.Arrays;

public class AdminMenu implements Menu {
    private final UIState uiState;
    public AdminMenu(UIState uiState) {
        this.uiState = uiState;
    }

    @Override
    public void display() {
        loop: while (true) {
            switch (Menu.prompt("\nAdmin Menu\n[1] Add/Edit Ship\n[2] Add/Edit Shipyard\n[3] Add/Edit Ship Class\n[4] Add/Edit Component Type\n[5] Add/Edit Component\n[x] Exit\n\nChoose an option: ",
                    Arrays.asList("1", "2", "3", "4", "5", "x"))) {
                case "1": // Add/Edit Ship
                    addEditShip();
                    break;
                case "2": // Add/Edit Shipyard
                    //TODO
                    break;
                case "3": // Add/Edit Ship Class
                    //TODO
                    break;
                case "4": // Add/Edit Component Type
                    //TODO
                    break;
                case "5": // Add/Edit Component
                    //TODO
                    break;
                case "x":
                    break loop;
            }
        }
    }

    private void addEditShip(){

    }
}
