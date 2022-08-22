package com.revature.buyNlarge.ui;

public class CheckoutMenu implements Menu {
    private final UIState uiState;
    public CheckoutMenu(UIState uiState) {
        this.uiState = uiState;
    }

    @Override
    public void display() {
        System.out.println(uiState.getCart());
    }
}
