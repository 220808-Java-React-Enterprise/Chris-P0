package com.revature.buyNlarge.ui;
import com.revature.buyNlarge.models.*;
import com.revature.buyNlarge.services.*;
import com.revature.buyNlarge.utils.ShipFactory;
import java.math.BigDecimal;
import java.util.*;

public class AdminMenu implements Menu {
    private final UIState uiState;
    public AdminMenu(UIState uiState) {
        this.uiState = uiState;
    }

    @Override
    public void display() {
        loop: while (true) {
            switch (Menu.prompt("\nAdmin Menu\n[1] Add Ship\n[2] Edit Ship\n[3] Add Shipyard\n[4] Edit Shipyard\n[5] Add Ship Class\n[6] Edit Ship Class\n[7] Add Component Type\n[8] Edit Component Type\n[9] Add Component\n[10] Edit Component\n[x] Exit\n\nChoose an option: ",
                    Arrays.asList("1", "2", "3", "4", "5", "f", "u", "x"))) {
                case "1": // Add Ship
                    addShip();
                    break;
                case "2": // Edit Ship
                    loop2: while(true) {
                        List<Ship> ships = ShipService.getAllShips();
                        for(int i = 0; i < ships.size(); i++){
                            System.out.print("[" + (i + 1) + "] ");
                            System.out.println(ships.get(i));
                        }
                        String userInput = Menu.prompt("\nSelect a ship to edit: ");
                        if(userInput.equals("x")){
                            break loop2;
                        }
                        try {
                            int userInt = Integer.parseInt(userInput) - 1;
                            if ((userInt < ships.size()) && (userInt >= 0)) {
                                Ship ship = editShip(ships.get(userInt));
                                if(ship != null) ShipService.updateShip(ship);
                            }
                        }catch(NumberFormatException e){
                            System.out.println("Invalid input.");
                        }
                    }
                    break;
                case "3": // Add Shipyard
                    addShipyard();
                    break;
                case "4": // Edit Shipyard
                    loop4: while(true) {
                        List<Shipyard> shipyards = ShipyardService.getAllShipyards();
                        for(int i = 0; i < shipyards.size(); i++){
                            System.out.print("[" + (i + 1) + "] ");
                            System.out.println(shipyards.get(i).getName());
                        }
                        String userInput = Menu.prompt("\nSelect a shipyard to edit: ");
                        if(userInput.equals("x")){
                            break loop4;
                        }
                        try {
                            int userInt = Integer.parseInt(userInput) - 1;
                            if ((userInt < shipyards.size()) && (userInt >= 0)) {
                                Shipyard shipyard = editShipyard(shipyards.get(userInt));
                                if(shipyard != null) ShipyardService.updateShipyard(shipyard);
                            }
                        }catch(NumberFormatException e){
                            System.out.println("Invalid input.");
                        }
                    }
                    break;
                case "5": // Add Ship Class
                    //TODO
                    break;
                case "6": // Edit Ship Class
                    //TODO
                    break;
                case "7": // Add Component Type
                    //TODO
                    break;
                case "8": // Edit Component Type
                    //TODO
                    break;
                case "9": // Add Component
                    //TODO
                    break;
                case "10": // Edit Component
                    //TODO
                    break;
                case "x":
                    break loop;
                case "f":
                    for(int i = 0; i < 10; i++){
                        ShipFactory.createRandomShip();
                    }
                    break;
                case "u":
                    System.out.println(UUID.randomUUID().toString());
                    break;
            }
        }
    }

    private void addShip(){
        String name = Menu.prompt("Enter a name for the ship: ", false);
        if(name.equals("x")) return;
        String description = Menu.prompt("Enter a description for the ship: ", false);
        if(description.equals("x")) return;
        Shipyard shipyard = getShipyard();
        if(shipyard == null) return;
        BigDecimal basePrice = getPrice();
        if(basePrice == null) return;
        Condition condition = getCondition("ship");
        if(condition == null) return;
        ShipClass shipClass = getShipClass();
        if(shipClass == null) return;
        List<Component> components = getComponents();
        if(components == null) return;
        Ship ship = new Ship(UUID.randomUUID().toString(), name, description, shipyard, basePrice, condition, shipClass, null ,components);
        loop: while(true) {
            switch (Menu.prompt("\n" + ship + "\nIs this correct? (y/n): ", Arrays.asList("y", "n", "x"))) {
                case "y":
                    ShipService.registerShip(ship);
                    break loop;
                case "n":
                    ship = editShip(ship);
                    break;
                case "x":
                    return;
            }
        }
    }

    private void addShipyard(){
        //TODO parse theta and pi
        String name = Menu.prompt("Enter a name for the shipyard: ", false);
        if(name.equals("x")) return;
        String description = Menu.prompt("Enter a description for the shipyard: ", false);
        if(description.equals("x")) return;
        String address = Menu.prompt("Enter an address for the shipyard: ", false);
        if(address.equals("x")) return;
        Shipyard shipyard = new Shipyard(UUID.randomUUID().toString(), name, description, address);
        loop: while(true) {
            switch (Menu.prompt("\n" + shipyard + "\nIs this correct? (y/n): ", Arrays.asList("y", "n", "x"))) {
                case "y":
                    ShipyardService.registerShipyard(shipyard);
                    break loop;
                case "n":
                    shipyard = editShipyard(shipyard);
                    break;
                case "x":
                    return;
            }
        }
    }

    private Shipyard getShipyard() {
        loop: while(true) {
            List<Shipyard> shipyards = ShipyardService.getAllShipyards();
            for(int i = 0; i < shipyards.size(); i++){
                System.out.print("\t[" + (i + 1) + "] ");
                System.out.println(shipyards.get(i).getName());
            }
            selectloop: while(true) {
                String userInput = Menu.prompt("Select shipyard that the ship is located at: ");
                if(userInput.equals("x")){
                    return null;
                }
                try {
                    int userInt = Integer.parseInt(userInput) - 1;
                    if ((userInt < shipyards.size()) && (userInt >= 0)) {
                        return shipyards.get(userInt);
                    }
                }catch(NumberFormatException e){
                    System.out.println("Invalid input.");
                }
            }
        }
    }

    private BigDecimal getPrice(){
        while(true) {
            BigDecimal userInput;
            Scanner scanner = new Scanner(System.in);
            loop:
            while(true){
                System.out.print("Enter the base price for the ship: ");
                userInput = scanner.nextBigDecimal();
                if(userInput != null) break;
            }
            return userInput;
        }
    }

    private Condition getCondition(String thing){
        loop: while(true) {
            List<Condition> conditions = Arrays.asList(Condition.values());
            for(int i = 1; i < conditions.size() - 1; i++){
                System.out.print("\t[" + (i) + "] ");
                System.out.println(conditions.get(i));
            }
            selectloop: while(true) {
                String userInput = Menu.prompt("Select the condition of the " + thing + ": ");
                if(userInput.equals("x")){
                    return null;
                }
                try {
                    int userInt = Integer.parseInt(userInput);
                    if ((userInt < conditions.size() - 1) && (userInt >= 1)) {
                        return conditions.get(userInt);
                    }
                }catch(NumberFormatException e){
                    System.out.println("Invalid input.");
                }
            }
        }
    }

    private ShipClass getShipClass(){
        loop: while(true) {
            List<ShipClass> shipClasses = ShipClassService.getAllShipClasses();
            for(int i = 0; i < shipClasses.size(); i++){
                System.out.print("\t[" + (i + 1) + "] ");
                System.out.println(shipClasses.get(i).getName());
            }
            selectloop: while(true) {
                String userInput = Menu.prompt("Select the class of the ship: ");
                if(userInput.equals("x")){
                    return null;
                }
                try {
                    int userInt = Integer.parseInt(userInput) - 1;
                    if ((userInt < shipClasses.size()) && (userInt >= 0)) {
                        return shipClasses.get(userInt);
                    }
                }catch(NumberFormatException e){
                    System.out.println("Invalid input.");
                }
            }
        }
    }

    private List<Component> getComponents(){
        List<Component> results = new ArrayList<>();
        loop: while(true) {
            List<ComponentType> componentTypes = ComponentTypeService.getAllComponentTypes();
            for (int i = 0; i < componentTypes.size(); i++) {
                System.out.print("\t[" + (i + 1) + "] ");
                System.out.println(componentTypes.get(i).getName());
            }
            String userInput = Menu.prompt("Select a component to add to the ship or 'x' to finish: ");
            if (userInput.equals("x")) {
                break;
            }
            try {
                int userInt = Integer.parseInt(userInput) - 1;
                if ((userInt < componentTypes.size()) && (userInt >= 0)) {
                    Condition condition = getCondition("component");
                    if (condition != null) results.add(new Component(componentTypes.get(userInt), condition));
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input.");
            }
        }
        return results;
    }

    private Ship editShip(Ship ship){
        loop: while(true) {
            switch (Menu.prompt( "[1] Name\n[2] Description\n[3] Shipyard\n[4] Base Price\n[5] Condition\n[6] Ship Class\n[7] Components\nSelect a attribute to edit or 'x' to finish: ",
                    Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8", "x"))) {
                case "1": //Name
                    String name = Menu.prompt("Enter a name for the ship: ", false);
                    if(!name.equals("x")) ship.setName(name);
                    break;
                case "2": //Description
                    String description = Menu.prompt("Enter a description for the ship: ", false);
                    if(!description.equals("x")) ship.setDescription(description);
                    break;
                case "3": //Shipyard
                    Shipyard shipyard = getShipyard();
                    if(shipyard != null) ship.setShipyard(shipyard);
                    break;
                case "4": //Base Price
                    BigDecimal basePrice = getPrice();
                    if(basePrice != null) ship.setBasePrice(basePrice);
                    break;
                case "5": //Condition
                    Condition condition = getCondition("ship");
                    if(condition != null) ship.setCondition(condition);
                    break;
                case "6": //Ship Class
                    ShipClass shipClass = getShipClass();
                    if(shipClass != null) ship.setShipClass(shipClass);
                    break;
                case "7": //Component
                    List<Component> components = getComponents();
                    if(components != null) ship.setComponents(components);
                    break;
                case "x":
                    break loop;
            }
        }
        return ship;
    }

    private Shipyard editShipyard(Shipyard shipyard){
        loop: while(true) {
            switch (Menu.prompt( "[1] Name\n[2] Description\n[3] Address\nSelect a attribute to edit or 'x' to finish: ",
                    Arrays.asList("1", "2", "3", "x"))) {
                case "1": //Name
                    String name = Menu.prompt("Enter a name for the shipyard: ", false);
                    if(!name.equals("x")) shipyard.setName(name);
                    break;
                case "2": //Description
                    String description = Menu.prompt("Enter a description for the shipyard: ", false);
                    if(!description.equals("x")) shipyard.setDescription(description);
                    break;
                case "3": //Shipyard
                    String address = Menu.prompt("Enter a address for the shipyard: ", false);
                    if(!address.equals("x")) shipyard.setAddress(address);
                    break;
                case "x":
                    break loop;
            }
        }
        return shipyard;
    }
}
