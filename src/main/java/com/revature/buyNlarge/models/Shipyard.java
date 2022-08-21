package com.revature.buyNlarge.models;

public class Shipyard extends Location{
    public Shipyard(String id, String name, String description, String address) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.address = address;
    }

    @Override
    public String getAddress(){
        return address;
    }
}
