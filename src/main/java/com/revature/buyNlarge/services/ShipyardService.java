package com.revature.buyNlarge.services;
import com.revature.buyNlarge.daos.ShipyardDAO;
import com.revature.buyNlarge.models.Shipyard;
import java.util.List;

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

    public static List<Shipyard> getAllShipyards(){
        return shipyardDAO.getAll();
    }

    public static void updateShipyard(Shipyard shipyard) {
        shipyardDAO.update(shipyard);
    }
}
