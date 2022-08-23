package com.revature.buyNlarge.ui;
import com.revature.buyNlarge.models.Ship;
import com.revature.buyNlarge.models.User;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Stack;

public class UIState {
    private User user;
    private Stack<Menu> navigator;
    private ArrayList<Ship> cart;

    public UIState(){
        this.user = null;
        this.navigator = new Stack<Menu>();
        this.cart = new ArrayList<Ship>();
    }
    public UIState(User user, Stack<Menu> navigator, ArrayList<Ship> cart) {
        this.user = user;
        this.navigator = navigator;
        this.cart = cart;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Menu popNavigator() {
        return navigator.pop();
    }

    public void pushNavigator(Menu menu) {
        navigator.push(menu);
    }

    public boolean navigatorIsEmpty(){
        return navigator.isEmpty();
    }

    public void addToCart(Ship ship){
        if(!cart.contains(ship)) {
            cart.add(ship);
        }
    }

    public void emptyCart(){ this.cart = new ArrayList<Ship>(); }

    public ArrayList<Ship> getCart() {
        return cart;
    }
    public BigDecimal getCartTotal(){
        BigDecimal sum = new BigDecimal("0");
        for(Ship ship : cart){
            sum = sum.add(ship.getTotalPrice());
        }
        return sum;
    }

    public void removeFromCart(Ship ship) {
        cart.remove(ship);
    }

    public boolean cartIsEmpty() {
        return cart.size() == 0;
    }
}
