package com.revature.buyNlarge.ui;

import com.revature.buyNlarge.models.*;
import com.revature.buyNlarge.services.LedgerService;

import java.text.NumberFormat;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class ShipyardDetailsMenu implements Menu {
    private final UIState uiState;
    public final Shipyard shipyard;
    public ShipyardDetailsMenu(UIState uiState, Shipyard shipyard) {
        this.uiState = uiState;
        this.shipyard = shipyard;
    }

    @Override
    public void display() {
        System.out.println('\n' + shipyard.toString());
        List<Ledger> ledgers = LedgerService.getLedgersByShipyardID(shipyard.getID());
        if(ledgers.size() == 0){
            System.out.println("No order history for this user available.\n\n");
        }
        switch (Menu.prompt("\n[1] Date Ascending\n[2] Date Descending\n[3] Price Ascending\n[4] Price Descending\n[x] Exit\nSelect an sorting method for order history: ",
                Arrays.asList("1", "2", "3", "4", "x"))) {
            case "1":
                ledgers.sort(Comparator.comparing(Ledger::getDate));
                break;
            case "2":
                ledgers.sort(Comparator.comparing(Ledger::getDate).reversed());
                break;
            case "3":
                ledgers.sort(Comparator.comparing(Ledger::getTotalPrice));
                break;
            case "4":
                ledgers.sort(Comparator.comparing(Ledger::getTotalPrice).reversed());
                break;
            case "x":
                return;
        }
        for (Ledger ledger : ledgers) {
            StringBuilder sb = new StringBuilder();
            sb.append("Ledger ").append(ledger.getID()).append('\n')
                    .append('\t').append(ledger.getDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))).append(" ").append(ledger.getUser().getUsername()).append('\n');
            for (Ship ship : ledger.getLedgerItems()) {
                sb.append("\t\t■ Ship ").append(ship.getID()).append(":\n")
                        .append("\t\t\t").append(ship.getName()).append('\n')
                        .append("\t\t\t").append(ship.getDescription()).append('\n')
                        .append("\t\t\tLocated at ").append(ship.getShipyard().getName()).append('\n')
                        .append("\t\t\tPrice: ").append(NumberFormat.getCurrencyInstance().format(ship.getTotalPrice())).append('\n')
                        .append("\t\t\t").append(ship.getCondition().toString()).append("-quality ").append(ship.getShipClass().getName()).append("-class\n");
                for(Component component : ship.getComponents()){
                    sb.append("\t\t\t\t<> ").append(component.getCondition()).append("-quality ").append(component.getType().getName()).append('\n');
                }
            }
            sb.append("\tTotal: ").append(NumberFormat.getCurrencyInstance().format(ledger.getTotalPrice())).append('\n');
            System.out.println(sb);
        }
        System.out.print("Press enter to return.");
        new Scanner(System.in).nextLine();
    }
}
