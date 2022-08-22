package com.revature.buyNlarge.models;
import java.math.BigDecimal;
import java.util.List;

public class Ship {
    private String id;
    private String name;
    private String description;
    private Shipyard shipyard;
    private BigDecimal basePrice;
    private Condition condition;
    private ShipClass shipClass;
    private List<Component> components;

    public Ship(String id, String name, String description, Shipyard shipyard, BigDecimal basePrice, Condition condition, ShipClass shipClass, List<Component> components) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.shipyard = shipyard;
        this.basePrice = basePrice;
        this.condition = condition;
        this.shipClass = shipClass;
        this.components = components;
    }

    public String getID() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Shipyard getShipyard() {
        return shipyard;
    }

    public BigDecimal getBasePrice() {
        return basePrice;
    }

    public Condition getCondition() {
        return condition;
    }

    public ShipClass getShipClass() {
        return shipClass;
    }

    public List<Component> getComponents() {
        return components;
    }

    @Override
    public String toString() {
        return "Ship{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", shipyard=" + shipyard +
                ", basePrice=" + basePrice +
                ", condition=" + condition +
                ", shipClass=" + shipClass +
                ", components=" + components +
                '}';
    }
}
