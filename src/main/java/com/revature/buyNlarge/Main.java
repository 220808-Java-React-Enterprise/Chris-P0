package com.revature.buyNlarge;
import com.revature.buyNlarge.ui.*;

public class Main {
    public static void main(String[] args) {
        UIState uiState = new UIState();

        uiState.pushNavigator(new LoginMenu(uiState));

        while(!uiState.navigatorIsEmpty()){
            uiState.popNavigator().display();
        }


    }
}
