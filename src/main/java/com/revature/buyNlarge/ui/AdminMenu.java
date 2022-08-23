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
                    Arrays.asList("1", "2", "3", "4", "5", "f", "x"))) {
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
                        selectloop: while(true) {
                            String userInput = Menu.prompt("\nSelect a ship to edit: ");
                            if(userInput.equals("x")){
                                break loop2;
                            }
                            try {
                                int userInt = Integer.parseInt(userInput) - 1;
                                if ((userInt < ships.size()) && (userInt >= 0)) {
                                    Ship ship = editShip(ships.get(userInt));
                                    if(ship != null) ShipService.registerShip(ship);
                                }
                            }catch(NumberFormatException e){
                                System.out.println("Invalid input.");
                            }
                        }
                    }
                    break;
                case "3": // Add Shipyard
                    //TODO
                    break;
                case "4": // Edit Shipyard
                    //TODO
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
            }
        }
    }

    private void addShip(){
        String id = getID();
        if(id == null) return;
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
        String ledgerID = null;
        List<Component> components = getComponents();
        if(components == null) return;
        Ship ship = new Ship(id, name, description, shipyard, basePrice, condition, shipClass, ledgerID,components);
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

    private String getID() {
        String userInput = Menu.prompt("Enter an id for the ship or nothing to automatically generate an id: ");
        if (userInput.equals("x")) {
            return null;
        } else if (userInput.equals("")) {
            return UUID.randomUUID().toString();
        } else {
            return userInput;
        }
    }

    private Shipyard getShipyard() {
        loop: while(true) {
            List<Shipyard> shipyards = ShipyardService.getAllShipyards();
            for(int i = 0; i < shipyards.size(); i++){
                System.out.print("[" + (i + 1) + "] ");
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
                System.out.print("[" + (i) + "] ");
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
                System.out.print("[" + (i + 1) + "] ");
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
                System.out.print("[" + (i + 1) + "] ");
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
        //TODO
        return null;
    }
}
