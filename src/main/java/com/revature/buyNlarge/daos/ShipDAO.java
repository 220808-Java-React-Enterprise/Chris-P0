package com.revature.buyNlarge.daos;

import com.revature.buyNlarge.models.*;
import com.revature.buyNlarge.services.*;
import com.revature.buyNlarge.utils.custom_exceptions.InvalidSQLException;
import com.revature.buyNlarge.utils.database.ConnectionFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ShipDAO implements DAO<Ship> {

    @Override
    public void save(Ship ship) {
        try (Connection connection = ConnectionFactory.getInstance().getConnection()) {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO ships (id, name, description, location, \"basePrice\", condition, class, ledger) VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
            ps.setString(1, ship.getID());
            ps.setString(2, ship.getName());
            ps.setString(3, ship.getDescription());
            if(!ShipyardService.isShipyardRegistered(ship.getShipyard().getID())){
                ShipyardService.registerShipyard(ship.getShipyard());
            }
            ps.setString(4, ship.getShipyard().getID());
            ps.setBigDecimal(5, ship.getBasePrice());
            ps.setObject(6, ship.getCondition(), java.sql.Types.OTHER);
            if(!ShipClassService.isShipClassRegistered(ship.getShipClass().getID())){
                ShipClassService.registerShipClass(ship.getShipClass());
            }
            ps.setString(7, ship.getShipClass().getID());
            if(ship.getLedgerID() == null) {
                ps.setNull(8, Types.VARCHAR);
            }else{
                ps.setString(8, ship.getLedgerID());
            }
            ps.executeUpdate();
            for(Component component : ship.getComponents()){
                ComponentService.registerComponent(component, ship.getID());
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new InvalidSQLException("An error occurred when tyring to save to the database.");
        }
    }

    @Override
    public void update(Ship obj) {}

    @Override
    public void delete(String id) {}

    @Override
    public Ship getByKey(String key) {
        try (Connection connection = ConnectionFactory.getInstance().getConnection()) {
            ArrayList<Component> components = new ArrayList<Component>();
            PreparedStatement ps = connection.prepareStatement("SELECT ship from components WHERE ship = ?");
            ps.setString(1, key);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                components.add(new Component(ComponentTypeService.getComponentTypeByID(rs.getString("type")), Condition.valueOf(rs.getString("condition"))));
            }
            ps = connection.prepareStatement("SELECT * FROM ships WHERE id = ?");
            ps.setString(1, key);
            rs = ps.executeQuery();
            if (rs.next()) {
                return new Ship(rs.getString("id"), rs.getString("name"), rs.getString("description"),
                        ShipyardService.getShipyardByID(rs.getString("location")), rs.getBigDecimal("basePrice"),
                        Condition.valueOf(rs.getString("condition")), ShipClassService.getShipClassByID(rs.getString("class")),
                        rs.getString("ledger"), components);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new InvalidSQLException("An error occurred when tyring to read from the database.");
        }
        return null;
    }

    @Override
    public List<Ship> getAll() {
        ArrayList<Ship> ships = new ArrayList<Ship>();
        try (Connection connection = ConnectionFactory.getInstance().getConnection()) {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM ships");
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                ArrayList<Component> components = new ArrayList<Component>();
                PreparedStatement psc = connection.prepareStatement("SELECT * from components WHERE ship = ?");
                psc.setString(1, rs.getString("id"));
                ResultSet rsc = psc.executeQuery();
                while (rsc.next()){
                    components.add(new Component(ComponentTypeService.getComponentTypeByID(rsc.getString("type")), Condition.valueOf(rsc.getString("condition"))));
                }
                ships.add(new Ship(rs.getString("id"), rs.getString("name"), rs.getString("description"),
                        ShipyardService.getShipyardByID(rs.getString("location")), rs.getBigDecimal("basePrice"),
                        Condition.valueOf(rs.getString("condition")), ShipClassService.getShipClassByID(rs.getString("class")),
                        rs.getString("ledger"), components));
            }
        } catch (SQLException e){
            e.printStackTrace();
            throw new InvalidSQLException("An error occurred when tyring to read from the database.");
        }
        return ships;
    }

    public void assignShipsToLedger(ArrayList<Ship> ships, Ledger ledger) {
        try (Connection connection = ConnectionFactory.getInstance().getConnection()) {
            PreparedStatement ps = connection.prepareStatement("UPDATE ships SET ledger = ? WHERE id = ?");
            ps.setString(1, ledger.getID());
            for(Ship ship : ships) {
                ps.setString(2, ship.getID());
                ps.executeUpdate();
            }
        } catch (SQLException e){
            e.printStackTrace();
            throw new InvalidSQLException("An error occurred when tyring to read from the database.");
        }
    }

    public List<Ship> getAllAvailable() {
        ArrayList<Ship> ships = new ArrayList<Ship>();
        try (Connection connection = ConnectionFactory.getInstance().getConnection()) {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM ships WHERE ledger IS NULL");
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                ArrayList<Component> components = new ArrayList<Component>();
                PreparedStatement psc = connection.prepareStatement("SELECT * from components WHERE ship = ?");
                psc.setString(1, rs.getString("id"));
                ResultSet rsc = psc.executeQuery();
                while (rsc.next()){
                    components.add(new Component(ComponentTypeService.getComponentTypeByID(rsc.getString("type")), Condition.valueOf(rsc.getString("condition"))));
                }
                ships.add(new Ship(rs.getString("id"), rs.getString("name"), rs.getString("description"),
                        ShipyardService.getShipyardByID(rs.getString("location")), rs.getBigDecimal("basePrice"),
                        Condition.valueOf(rs.getString("condition")), ShipClassService.getShipClassByID(rs.getString("class")),
                        rs.getString("ledger"), components));
            }
        } catch (SQLException e){
            e.printStackTrace();
            throw new InvalidSQLException("An error occurred when tyring to read from the database.");
        }
        return ships;
    }
}