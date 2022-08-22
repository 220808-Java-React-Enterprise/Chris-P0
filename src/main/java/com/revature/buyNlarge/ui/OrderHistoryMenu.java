package com.revature.buyNlarge.ui;

import com.revature.buyNlarge.models.Ledger;
import com.revature.buyNlarge.models.Ship;
import com.revature.buyNlarge.models.User;
import com.revature.buyNlarge.services.LedgerService;

import java.text.NumberFormat;
import java.util.List;
import java.util.Scanner;

public class OrderHistoryMenu implements Menu {
    private final UIState uiState;
    public final User user;
    public OrderHistoryMenu(UIState uiState, User user) {
        this.uiState = uiState;
        this.user = user;
    }

    @Override
    public void display() {
        for (Ledger ledger : LedgerService.getLedgersByUsername(user.getUsername())) {
            StringBuilder sb = new StringBuilder();
            sb.append("Ledger ").append(ledger.getID()).append('\n')
                    .append('\t').append(ledger.getDate()).append(" ").append(ledger.getUser().getUsername()).append('\n');
            for (Ship ship : ledger.getLedgerItems()) {
                sb.append("\t\t■ Ship ").append(ship.getID()).append(":\n")
                        .append("\t\t\t").append(ship.getName()).append('\n')
                        .append("\t\t\t").append(ship.getDescription()).append('\n')
                        .append("\t\t\tLocated at ").append(ship.getShipyard().getName()).append('\n')
                        .append("\t\t\tPrice: ").append(NumberFormat.getCurrencyInstance().format(ship.getTotalPrice())).append('\n')
                        .append("\t\t\t").append(ship.getCondition().toString()).append("-quality ").append(ship.getShipClass().getName()).append("-class\n");
            }
            sb.append("\tTotal: ").append(NumberFormat.getCurrencyInstance().format(ledger.getTotalPrice())).append('\n');
            System.out.println(sb);
        }
        System.out.print("Press enter to return.");
        new Scanner(System.in).nextLine();
    }
}
