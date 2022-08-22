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
    private String ledgerID;
    private List<Component> components;

    public Ship(String id, String name, String description, Shipyard shipyard, BigDecimal basePrice, Condition condition, ShipClass shipClass, String ledgerID, List<Component> components) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.shipyard = shipyard;
        this.basePrice = basePrice;
        this.condition = condition;
        this.shipClass = shipClass;
        this.ledgerID = ledgerID;
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

    public String getLedgerID() {
        return ledgerID;
    }
    public List<Component> getComponents() {
        return components;
    }
    public BigDecimal getTotalPrice() {
        BigDecimal result = new BigDecimal(String.valueOf(basePrice));
        result = result.multiply(BigDecimal.valueOf((condition.ordinal() + 1)/ (Condition.COUNT.ordinal() + 1)));
        for(Component component : components){
            result = result.add(component.getType().getBasePrice().multiply(BigDecimal.valueOf((component.getCondition().ordinal() + 1)/ (Condition.COUNT.ordinal() + 1))));
        }
        return result;
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Ship ").append(id).append(":\n")
                .append("\t").append(name).append('\n')
                .append("\t").append(description).append('\n')
                .append("\tLocated at ").append(shipyard.getName()).append('\n')
                .append("\tPrice: ").append(getTotalPrice()).append(" Credits").append('\n')
                .append("\t").append(condition.toString()).append(' ').append(shipClass.getName()).append("-class\n");
        for(Component component : components){
            sb.append("\t\t-").append(component.getCondition()).append(' ').append(component.getType().getName()).append('\n');
        }
        return sb.toString();
    }


}
