package com.revature.buyNlarge.ui;
import com.revature.buyNlarge.models.User;
import java.util.Stack;

public class UIState {
    private User user;
    private Stack<Menu> navigator;

    public UIState(){
        this.user = null;
        this.navigator = new Stack<Menu>();
    }
    public UIState(User user, Stack<Menu> navigator) {
        this.user = user;
        this.navigator = navigator;
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
}
