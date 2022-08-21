package com.revature.buyNlarge.models;

public abstract class ShipClass {
    protected String id;
    protected String name;
    protected String description;
    protected int engineMaxSize;
    protected int engineMinSize;
    protected int smallHardPoints;
    protected int mediumHardPoints;
    protected int largeHardPoints;
    protected int smallAuxPoints;
    protected int mediumAuxPoints;
    protected int largeAuxPoints;
    protected int cabins;
    protected int bays;

    protected ShipClass(String id, String name, String description, int engineMaxSize, int engineMinSize, int smallHardPoints, int mediumHardPoints, int largeHardPoints, int smallAuxPoints, int mediumAuxPoints, int largeAuxPoints, int cabins, int bays) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.engineMaxSize = engineMaxSize;
        this.engineMinSize = engineMinSize;
        this.smallHardPoints = smallHardPoints;
        this.mediumHardPoints = mediumHardPoints;
        this.largeHardPoints = largeHardPoints;
        this.smallAuxPoints = smallAuxPoints;
        this.mediumAuxPoints = mediumAuxPoints;
        this.largeAuxPoints = largeAuxPoints;
        this.cabins = cabins;
        this.bays = bays;
    }
}
