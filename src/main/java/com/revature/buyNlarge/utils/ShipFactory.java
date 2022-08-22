package com.revature.buyNlarge.utils;

import com.revature.buyNlarge.models.*;
import com.revature.buyNlarge.services.ShipClassService;
import com.revature.buyNlarge.services.ShipService;
import com.revature.buyNlarge.services.ShipyardService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class ShipFactory {
    private static final List<Shipyard> shipyards = ShipyardService.getAllShipyards();
    private static final List<ShipClass> shipClasses = ShipClassService.getAllShipClasses();

    public static void createRandomShip(){
        ShipService.registerShip(new Ship(UUID.randomUUID().toString(), "USS Mundanity" + (Math.random() * 1000),
                "Random Ship Generated by the Test Factory.", shipyards.get((int) (Math.random() * shipyards.size())),
                new BigDecimal(Math.random() * 1000000), Arrays.asList(Condition.values()).get((int) (Math.random() * Condition.COUNT.ordinal() + 1)),
                shipClasses.get((int) (Math.random() * shipClasses.size())), null, new ArrayList<Component>()));
    }
}
