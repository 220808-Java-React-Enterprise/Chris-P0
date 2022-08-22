package com.revature.buyNlarge.models;
import java.math.BigDecimal;

public class ComponentType {
    private String id;
    private String name;
    private String description;
    private ComponentClass componentClass;
    private int size;
    private BigDecimal basePrice;

    public ComponentType(String id, String name, String description, ComponentClass componentClass, int size, BigDecimal basePrice) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.componentClass = componentClass;
        this.size = size;
        this.basePrice = basePrice;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public ComponentClass getComponentClass() {
        return componentClass;
    }

    public int getSize() {
        return size;
    }

    public BigDecimal getBasePrice() {
        return basePrice;
    }
}
