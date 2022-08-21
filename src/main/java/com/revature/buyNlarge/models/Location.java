package com.revature.buyNlarge.models;

public abstract class Location {
    protected String id;
    protected String name;
    protected String description;
    protected String address;

    abstract String getAddress();
}
