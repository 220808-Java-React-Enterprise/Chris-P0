package com.revature.buyNlarge.daos;

import com.revature.buyNlarge.models.*;
import com.revature.buyNlarge.services.*;
import com.revature.buyNlarge.utils.custom_exceptions.InvalidSQLException;
import com.revature.buyNlarge.utils.database.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class LedgerDAO implements DAO<Ledger> {

    @Override
    public void save(Ledger ledger) {
        try (Connection connection = ConnectionFactory.getInstance().getConnection()) {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO ledgers (id, username, date, \"totalPrice\") VALUES (?, ?, ?, ?)");
            ps.setString(1, ledger.getID());
            ps.setString(2, ledger.getUser().getUsername());
            ps.setObject(3, ledger.getDate());
            ps.setBigDecimal(4, ledger.getTotalPrice());
            ps.executeUpdate();
            ShipService.assignShipsToLedger(ledger.getLedgerItems(), ledger);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new InvalidSQLException("An error occurred when tyring to save to the database.");
        }
    }

    @Override
    public void update(Ledger obj) {}

    @Override
    public void delete(String id) {}

    @Override
    public Ledger getByKey(String key) {
        try (Connection connection = ConnectionFactory.getInstance().getConnection()) {
            ArrayList<Ship> ledgerItems = new ArrayList<Ship>();
            PreparedStatement ps = connection.prepareStatement("SELECT id from ships WHERE ledger = ?");
            ps.setString(1, key);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                ledgerItems.add(ShipService.getShipByID(rs.getString("id")));
            }
            ps = connection.prepareStatement("SELECT * FROM ledgers WHERE id = ?");
            ps.setString(1, key);
            rs = ps.executeQuery();
            if (rs.next()) {
                return new Ledger(rs.getString("id"), UserService.getUserByUsername(rs.getString("username")), rs.getObject("date", LocalDate.class), rs.getBigDecimal("totalPrice"), ledgerItems);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new InvalidSQLException("An error occurred when tyring to read from the database.");
        }
        return null;
    }

    @Override
    public List<Ledger> getAll() {
        return null;
    }

    public ArrayList<Ledger> getLedgerByUsername(String username) {
        ArrayList<Ledger> ledgers = new ArrayList<Ledger>();
        try (Connection connection = ConnectionFactory.getInstance().getConnection()) {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM ledgers WHERE username = ?");
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                ArrayList<Ship> ships = new ArrayList<Ship>();
                PreparedStatement shipPrepredStatement = connection.prepareStatement("SELECT id from ships WHERE ledger = ?");
                shipPrepredStatement.setString(1, rs.getString("id"));
                ResultSet shipResultSet = shipPrepredStatement.executeQuery();
                while (shipResultSet.next()){
                    ships.add(ShipService.getShipByID(shipResultSet.getString("id")));
                }
                ledgers.add(new Ledger(rs.getString("id"), UserService.getUserByUsername(rs.getString("username")), rs.getObject("date", LocalDate.class), rs.getBigDecimal("totalPrice"), ships));
            }
        } catch (SQLException e){
            e.printStackTrace();
            throw new InvalidSQLException("An error occurred when tyring to read from the database.");
        }
        return ledgers;
    }
}