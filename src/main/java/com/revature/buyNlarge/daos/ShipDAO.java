package com.revature.buyNlarge.daos;

import com.revature.buyNlarge.models.*;
import com.revature.buyNlarge.services.ComponentService;
import com.revature.buyNlarge.services.ShipClassService;
import com.revature.buyNlarge.services.ShipyardService;
import com.revature.buyNlarge.utils.custom_exceptions.InvalidSQLException;
import com.revature.buyNlarge.utils.database.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ShipDAO implements DAO<Ship> {

    @Override
    public void save(Ship ship) {
        try (Connection connection = ConnectionFactory.getInstance().getConnection()) {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO ships (id, name, description, location, \"basePrice\", condition, class) VALUES (?, ?, ?, ?, ?, ?, ?)");
            ps.setString(1, ship.getID());
            ps.setString(2, ship.getName());
            ps.setString(3, ship.getDescription());
            if(!ShipyardService.isShipyardRegistered(ship.getShipyard().getID())){
                ShipyardService.registerShipyard(ship.getShipyard());
            }
            ps.setString(4, ship.getShipyard().getID());
            ps.setBigDecimal(5, ship.getBasePrice());
            ps.setObject(6, ship.getCondition().name());
            if(!ShipClassService.isShipClassRegistered(ship.getShipClass().getID())){
                ShipClassService.registerShipClass(ship.getShipClass());
            }
            ps.setString(7, ship.getShipClass().getID());
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
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM ships WHERE id = ?");
            ps.setString(1, key);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                //TODO fix the components list
                return new Ship(rs.getString("id"), rs.getString("name"), rs.getString("description"),
                        ShipyardService.getShipyardByID(rs.getString("shipyard")), rs.getBigDecimal("basePrice"),
                        Condition.valueOf(rs.getString("condition")), ShipClassService.getShipClassByID(rs.getString("shipClass")),
                        new ArrayList<Component>());
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
                //TODO fix the components list
                ships.add(new Ship(rs.getString("id"), rs.getString("name"), rs.getString("description"),
                        ShipyardService.getShipyardByID(rs.getString("location")), rs.getBigDecimal("basePrice"),
                        Condition.valueOf(rs.getString("condition")), ShipClassService.getShipClassByID(rs.getString("class")),
                        new ArrayList<Component>()));
            }
        } catch (SQLException e){
            e.printStackTrace();
            throw new InvalidSQLException("An error occurred when tyring to read from the database.");
        }
        return ships;
    }
}