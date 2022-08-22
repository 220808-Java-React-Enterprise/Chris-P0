package com.revature.buyNlarge.services;
import com.revature.buyNlarge.daos.ShipClassDAO;
import com.revature.buyNlarge.daos.ShipyardDAO;
import com.revature.buyNlarge.models.ShipClass;
import com.revature.buyNlarge.models.Shipyard;

import java.util.List;

public class ShipClassService {
    private static final ShipClassDAO shipClassDAO = new ShipClassDAO();

    public static void registerShipClass(ShipClass shipClass){
        shipClassDAO.save(shipClass);
    }

    public static ShipClass getShipClassByID(String id) {
        return shipClassDAO.getByKey(id);
    }

    public static boolean isShipClassRegistered(String id){
        return shipClassDAO.getByKey(id) != null;
    }

    public static List<ShipClass> getAllShipClasses() {
        return shipClassDAO.getAll();
    }
}
