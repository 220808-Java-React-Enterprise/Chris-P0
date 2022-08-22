package com.revature.buyNlarge.services;
import com.revature.buyNlarge.daos.ComponentDAO;
import com.revature.buyNlarge.models.Component;

public class ComponentService {
    private static final ComponentDAO componentDAO = new ComponentDAO();

    public static void registerComponent(Component component){
        componentDAO.save(component);
    }
    public static void registerComponent(Component component, String shipID){
        componentDAO.save(component, shipID);
    }

    public static Component getComponentByID(String id) {
        return componentDAO.getByKey(id);
    }

    public static boolean isComponentRegistered(String id){
        return componentDAO.getByKey(id) != null;
    }
}
