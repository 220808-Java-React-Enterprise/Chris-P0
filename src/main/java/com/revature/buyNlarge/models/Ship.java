package com.revature.buyNlarge.models;
import java.math.BigDecimal;
import java.util.List;

public class Ship {
    private String id;
    private String name;
    private String description;
    private Location location;
    private BigDecimal basePrice;
    private Condition condition;
    private ShipClass shipClass;
    private List<Component> components;
}
