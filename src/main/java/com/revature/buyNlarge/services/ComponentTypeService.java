package com.revature.buyNlarge.services;
import com.revature.buyNlarge.daos.ComponentTypeDAO;
import com.revature.buyNlarge.models.ComponentType;

import java.util.List;

public class ComponentTypeService {
    private static final ComponentTypeDAO componentTypeDAO = new ComponentTypeDAO();

    public static void registerComponentType(ComponentType componentType){
        componentTypeDAO.save(componentType);
    }

    public static ComponentType getComponentTypeByID(String id) {
        return componentTypeDAO.getByKey(id);
    }

    public static boolean isComponentRegistered(String id){
        return componentTypeDAO.getByKey(id) != null;
    }

    public static List<ComponentType> getAllComponentTypes() {
        return componentTypeDAO.getAll();
    }
}
