package com.revature.buyNlarge.models;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public class Ledger {
    private String id;
    private User user;
    private LocalDate date;
    private BigDecimal totalPrice;
    private ArrayList<Ship> ledgerItems;

    public Ledger(String id, User user, LocalDate date, BigDecimal totalPrice, ArrayList<Ship> ledgerItems) {
        this.id = id;
        this.user = user;
        this.date = date;
        this.totalPrice = totalPrice;
        this.ledgerItems = ledgerItems;
    }

    public String getID() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public LocalDate getDate() {
        return date;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public ArrayList<Ship> getLedgerItems() {
        return ledgerItems;
    }
}
