package com.revature.buyNlarge.services;

import com.revature.buyNlarge.daos.ShipDAO;
import com.revature.buyNlarge.models.Ledger;
import com.revature.buyNlarge.models.Ship;

import java.util.ArrayList;
import java.util.List;

public class ShipService {
    private static final ShipDAO shipDAO = new ShipDAO();

    public static void registerShip(Ship ship){
        shipDAO.save(ship);
    }

    public static Ship getShipByID(String id) {
        return shipDAO.getByKey(id);
    }

    public static boolean isShipRegistered(String id){
        return shipDAO.getByKey(id) != null;
    }

    public static List<Ship> getAllShips(){ return shipDAO.getAll(); }
    public static List<Ship> getAllAvailableShips(){
        return shipDAO.getAllAvailable();
    }

    public static void assignShipsToLedger(ArrayList<Ship> ships, Ledger ledger) {
        shipDAO.assignShipsToLedger(ships, ledger);
    }

    public static List<Ship> getAllAvailableShipsByShipyardID(String id) {
        return shipDAO.getAllAvailableShipsByShipyardID(id);
    }
}
