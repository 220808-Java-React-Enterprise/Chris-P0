package com.revature.buyNlarge.ui;

import com.revature.buyNlarge.models.Ledger;
import com.revature.buyNlarge.models.Ship;
import com.revature.buyNlarge.services.LedgerService;

public class OrderHistoryMenu implements Menu {
    private final UIState uiState;
    public OrderHistoryMenu(UIState uiState) {
        this.uiState = uiState;
    }

    @Override
    public void display() {
        for(Ledger ledger : LedgerService.getLedgerByUsername(uiState.getUser().getUsername())){
            StringBuilder sb = new StringBuilder();
            sb.append(ledger.getID()).append('\n')
                    .append("On ").append(ledger.getDate()).append(" ").append(ledger.getUser().getUsername()).append(" bought:").append('\n');
            for(Ship ship : ledger.getLedgerItems()){
                sb.append('\t').append(ship).append('\n');
            }
            sb.append("for ").append(ledger.getTotalPrice());
            System.out.println(sb);
        }
    }
}
