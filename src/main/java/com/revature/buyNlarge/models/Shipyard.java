package com.revature.buyNlarge.models;

public class Shipyard{
    private String id;
    private String name;
    private String description;
    private String address;

    public Shipyard(String id, String name, String description, String address) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.address = address;
    }

    public String getAddress(){
        return address;
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

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Shipyard " + id + '\n' +
                name + '\n' +
                description + '\n' +
                "Galactic Address: " + address + '\n';
    }
}
