package com.revature.buyNlarge.models;

public class Component {
    protected ComponentType type;
    protected Condition condition;

    public Component(ComponentType type, Condition condition) {
        this.type = type;
        this.condition = condition;
    }

    public ComponentType getType() {
        return type;
    }

    public Condition getCondition() {
        return condition;
    }
}
