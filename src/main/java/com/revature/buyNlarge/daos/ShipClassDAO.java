package com.revature.buyNlarge.daos;

import com.revature.buyNlarge.models.ShipClass;
import com.revature.buyNlarge.models.Shipyard;
import com.revature.buyNlarge.utils.custom_exceptions.InvalidSQLException;
import com.revature.buyNlarge.utils.database.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ShipClassDAO implements DAO<ShipClass> {

    @Override
    public void save(ShipClass shipClass) {
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
    public void update(ShipClass obj) {}

    @Override
    public void delete(String id) {}

    @Override
    public ShipClass getByKey(String key) {
        try (Connection connection = ConnectionFactory.getInstance().getConnection()) {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM \"shipClasses\" WHERE id = ?");
            ps.setString(1, key);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new ShipClass(rs.getString("id"), rs.getString("name"), rs.getString("description"),
                        rs.getInt("engineMaxSize"), rs.getInt("engineMinSize"), rs.getInt("smallHardPoints"),
                        rs.getInt("mediumHardPoints"), rs.getInt("largeHardPoints"), rs.getInt("smallAuxPoints"),
                        rs.getInt("mediumAuxPoints"), rs.getInt("largeAuxPoints"), rs.getInt("cabins"),
                        rs.getInt("bays"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new InvalidSQLException("An error occurred when tyring to read from the database.");
        }
        return null;
    }

    @Override
    public List<ShipClass> getAll() {
        return null;
    }
}