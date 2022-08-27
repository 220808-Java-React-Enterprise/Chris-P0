package com.revature.buyNlarge.utils;

import com.revature.buyNlarge.models.*;
import com.revature.buyNlarge.services.ComponentTypeService;
import com.revature.buyNlarge.services.ShipClassService;
import com.revature.buyNlarge.services.ShipService;
import com.revature.buyNlarge.services.ShipyardService;

import java.math.BigDecimal;
import java.util.*;

public class ShipFactory {
    private static final List<Shipyard> shipyards = ShipyardService.getAllShipyards();
    private static final List<ShipClass> shipClasses = ShipClassService.getAllShipClasses();
    public static void createRandomShip(){
        Random r = new Random();
        ArrayList<Component> components = new ArrayList<>();
        List<ComponentType> componentTypes = ComponentTypeService.getAllComponentTypes();
        for(int i = 0; i < r.nextInt(5); i++){
            components.add(new Component(componentTypes.get(r.nextInt(componentTypes.size())), Arrays.asList(Condition.values()).get(r.nextInt(Condition.COUNT.ordinal() - 1) + 1)));
        }
        ShipService.registerShip(
                new Ship(
                        UUID.randomUUID().toString(),
                        "BNL Mundanity " + r.nextInt(1000),
                        "Buy-N-Large Ship.",
                        shipyards.get(r.nextInt(shipyards.size())),
                        BigDecimal.valueOf((Math.random() * 1000000000) + 1),
                        Arrays.asList(Condition.values()).get(r.nextInt(Condition.COUNT.ordinal() - 1) + 1),
                        shipClasses.get(r.nextInt(shipClasses.size())),
                        null,
                        components
                )
        );
    }
}
