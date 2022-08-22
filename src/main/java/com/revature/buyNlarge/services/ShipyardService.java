package com.revature.buyNlarge.services;
import com.revature.buyNlarge.daos.ShipyardDAO;
import com.revature.buyNlarge.models.Shipyard;

public class ShipyardService {
    private static final ShipyardDAO shipyardDAO = new ShipyardDAO();

    public static void registerShipyard(Shipyard shipyard){
        shipyardDAO.save(shipyard);
    }

    public static Shipyard getShipyardByID(String id) {
        return shipyardDAO.getByKey(id);
    }

    public static boolean isShipyardRegistered(String id){
        return shipyardDAO.getByKey(id) != null;
    }
}
