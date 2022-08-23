package com.revature.buyNlarge.daos;
import com.revature.buyNlarge.models.Component;
import com.revature.buyNlarge.services.ShipService;
import com.revature.buyNlarge.utils.custom_exceptions.InvalidSQLException;
import com.revature.buyNlarge.utils.database.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class ComponentDAO implements DAO<Component> {

    @Override
    public void save(Component component){throw new InvalidSQLException();}

    public void save(Component component, String shipID) {
        try (Connection connection = ConnectionFactory.getInstance().getConnection()) {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO components (ship, type, condition) VALUES (?, ?, ?)");
            ps.setString(1, shipID);
            ps.setString(2, component.getType().getId());
            ps.setObject(3, component.getCondition().name(), java.sql.Types.OTHER);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new InvalidSQLException("An error occurred when tyring to save to the database.");
        }
    }

    @Override
    public void update(Component obj) {throw new InvalidSQLException("An error occurred when tyring to read from the database.");}

    @Override
    public void delete(String id) {throw new InvalidSQLException("An error occurred when tyring to read from the database.");}

    @Override
    public Component getByKey(String key) {throw new InvalidSQLException("An error occurred when tyring to read from the database."); }

    @Override
    public List<Component> getAll() {throw new InvalidSQLException("An error occurred when tyring to read from the database.");}
}