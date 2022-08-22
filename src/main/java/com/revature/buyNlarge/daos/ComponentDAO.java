package com.revature.buyNlarge.daos;

import com.revature.buyNlarge.models.Component;
import com.revature.buyNlarge.utils.custom_exceptions.InvalidSQLException;
import com.revature.buyNlarge.utils.database.ConnectionFactory;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class ComponentDAO implements DAO<Component> {

    @Override
    public void save(Component component) {
        try (Connection connection = ConnectionFactory.getInstance().getConnection()) {/**
            PreparedStatement ps = connection.prepareStatement("INSERT INTO ships (id, name, description, location, \"basePrice\", condition, class) VALUES (?, ?, ?, ?, ?, ?, ?)");
            ps.setString(1, ship.getID());
            ps.setString(2, ship.getName());
            ps.setString(3, ship.getDescription());
            if(ShipyardService.isShipyardInDatabase(ship.getLocation().getID())){

            }
            ps.setString(4, ship.getLocation().getID());
            ps.setBigDecimal(5, ship.getBasePrice());
            ps.setObject(6, ship.getCondition().name());
            ps.setString(7, ship.getShipClass().getID());
            ps.executeUpdate();**/
        } catch (SQLException e) {
            e.printStackTrace();
            throw new InvalidSQLException("An error occurred when tyring to save to the database.");
        }
    }

    public void save(Component component, String shipID) {
        try (Connection connection = ConnectionFactory.getInstance().getConnection()) {/**
         PreparedStatement ps = connection.prepareStatement("INSERT INTO ships (id, name, description, location, \"basePrice\", condition, class) VALUES (?, ?, ?, ?, ?, ?, ?)");
         ps.setString(1, ship.getID());
         ps.setString(2, ship.getName());
         ps.setString(3, ship.getDescription());
         if(ShipyardService.isShipyardInDatabase(ship.getLocation().getID())){

         }
         ps.setString(4, ship.getLocation().getID());
         ps.setBigDecimal(5, ship.getBasePrice());
         ps.setObject(6, ship.getCondition().name());
         ps.setString(7, ship.getShipClass().getID());
         ps.executeUpdate();**/
        } catch (SQLException e) {
            e.printStackTrace();
            throw new InvalidSQLException("An error occurred when tyring to save to the database.");
        }
    }

    @Override
    public void update(Component obj) {}

    @Override
    public void delete(String id) {}

    @Override
    public Component getByKey(String key) {
        /**
        try (Connection connection = ConnectionFactory.getInstance().getConnection()) {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM ships WHERE id = ?");
            ps.setString(1, key);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                //TODO the new shipyard and new shipClass need to be fixed and the components list
                return new Ship(rs.getString("id"), rs.getString("name"), rs.getString("description"),
                        new Shipyard("", "", "", ""), rs.getBigDecimal("basePrice"),
                        Condition.valueOf(rs.getString("condition")), new ShipClass("", "", "", 0,
                        0, 0, 0, 0, 0, 0, 0,
                        0, 0), new ArrayList<Component>());
            }
        } catch (SQLException e) {
            e.printStackTrace();**/
            throw new InvalidSQLException("An error occurred when tyring to read from the database.");
        //}
        //return null;
    }

    @Override
    public List<Component> getAll() {
        return null;
    }
}