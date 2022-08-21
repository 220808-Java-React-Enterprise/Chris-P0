package com.revature.buyNlarge.models;
import java.math.BigDecimal;

public class ComponentType {
    protected String id;
    protected String name;
    protected String description;
    protected ComponentClass componentClass;
    protected int size;
    protected BigDecimal basePrice;

    protected ComponentType(String id, String name, String description, ComponentClass componentClass, int size, BigDecimal basePrice) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.componentClass = componentClass;
        this.size = size;
        this.basePrice = basePrice;
    }
}
