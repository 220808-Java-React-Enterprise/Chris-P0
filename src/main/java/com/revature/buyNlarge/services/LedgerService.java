package com.revature.buyNlarge.services;
import com.revature.buyNlarge.daos.LedgerDAO;
import com.revature.buyNlarge.models.Ledger;
import java.util.ArrayList;

public class LedgerService {
    private static final LedgerDAO ledgerDAO = new LedgerDAO();

    public static void registerLedger(Ledger shipyard){
        ledgerDAO.save(shipyard);
    }

    public static Ledger getLedgerByID(String id) {
        return ledgerDAO.getByKey(id);
    }

    public static boolean isLedgerRegistered(String id){
        return ledgerDAO.getByKey(id) != null;
    }

    public static ArrayList<Ledger> getLedgersByUsername(String username) {
        return ledgerDAO.getLedgersByUsername(username);
    }
}
