package com.revature.buyNlarge.models;
import java.math.BigDecimal;
import java.text.NumberFormat;
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
        BigDecimal result = basePrice.multiply(BigDecimal.valueOf((double)condition.ordinal() / (double)(Condition.COUNT.ordinal() - 1)));
        for(Component component : components){
            result = result.add(component.getType().getBasePrice().multiply(BigDecimal.valueOf((double)component.getCondition().ordinal() / (double)(Condition.COUNT.ordinal() - 1))));
        }
        return result;
    }

    public void setID(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setShipyard(Shipyard shipyard) {
        this.shipyard = shipyard;
    }

    public void setBasePrice(BigDecimal basePrice) {
        this.basePrice = basePrice;
    }

    public void setCondition(Condition condition) {
        this.condition = condition;
    }

    public void setShipClass(ShipClass shipClass) {
        this.shipClass = shipClass;
    }

    public void setLedgerID(String ledgerID) {
        this.ledgerID = ledgerID;
    }

    public void setComponents(List<Component> components) {
        this.components = components;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(name).append('\n')
                .append("\t").append(description).append('\n')
                .append("\tLocated at ").append(shipyard.getName()).append('\n')
                .append("\tPrice: ").append(NumberFormat.getCurrencyInstance().format(getTotalPrice())).append('\n')
                .append("\t").append(condition.toString()).append("-quality ").append(shipClass.getName()).append("-class\n");
        for(Component component : components){
            sb.append("\t\t<> ").append(component.getCondition()).append("-quality ").append(component.getType().getName()).append('\n');
        }
        return sb.toString();
    }


}
