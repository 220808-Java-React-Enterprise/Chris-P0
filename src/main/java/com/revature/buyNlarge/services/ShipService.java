package com.revature.buyNlarge.services;

import com.revature.buyNlarge.daos.ShipDAO;
import com.revature.buyNlarge.models.Ship;

import java.util.List;

public class ShipService {
    private static final ShipDAO shipDAO = new ShipDAO();

    public static void registerShipyard(Ship ship){
        shipDAO.save(ship);
    }

    public static Ship getShipyardByID(String id) {
        return shipDAO.getByKey(id);
    }

    public static boolean isShipyardRegistered(String id){
        return shipDAO.getByKey(id) != null;
    }

    public static List<Ship> getAllShips(){ return shipDAO.getAll(); }
}
