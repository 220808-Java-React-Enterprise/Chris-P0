package com.revature.buyNlarge.ui;

public class MainMenu implements Menu {
    private final UIState uiState;

    public MainMenu(UIState uiState){
        this.uiState = uiState;
    }
    @Override
    public void display(){
        System.out.println("Main Menu");
        System.out.println(uiState.getUser());
    }
}
