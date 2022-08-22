package com.revature.buyNlarge.models;

public class ShipClass {
    private String id;
    private String name;
    private String description;
    private int engineMaxSize;
    private int engineMinSize;
    private int smallHardPoints;
    private int mediumHardPoints;
    private int largeHardPoints;
    private int smallAuxPoints;
    private int mediumAuxPoints;
    private int largeAuxPoints;
    private int cabins;
    private int bays;

    public ShipClass(String id, String name, String description, int engineMaxSize, int engineMinSize, int smallHardPoints, int mediumHardPoints, int largeHardPoints, int smallAuxPoints, int mediumAuxPoints, int largeAuxPoints, int cabins, int bays) {
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

    public String getID() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getEngineMaxSize() {
        return engineMaxSize;
    }

    public int getEngineMinSize() {
        return engineMinSize;
    }

    public int getSmallHardPoints() {
        return smallHardPoints;
    }

    public int getMediumHardPoints() {
        return mediumHardPoints;
    }

    public int getLargeHardPoints() {
        return largeHardPoints;
    }

    public int getSmallAuxPoints() {
        return smallAuxPoints;
    }

    public int getMediumAuxPoints() {
        return mediumAuxPoints;
    }

    public int getLargeAuxPoints() {
        return largeAuxPoints;
    }

    public int getCabins() {
        return cabins;
    }

    public int getBays() {
        return bays;
    }
}
