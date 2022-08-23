package com.revature.buyNlarge.daos;

import com.revature.buyNlarge.models.*;
import com.revature.buyNlarge.utils.custom_exceptions.InvalidSQLException;
import com.revature.buyNlarge.utils.database.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ComponentTypeDAO implements DAO<ComponentType> {

    @Override
    public void save(ComponentType componentType) {throw new InvalidSQLException("An error occurred when tyring to save to the database.");}

    @Override
    public void update(ComponentType obj) {throw new InvalidSQLException("An error occurred when tyring to save to the database.");}

    @Override
    public void delete(String id) {throw new InvalidSQLException("An error occurred when tyring to save to the database.");}

    private static HashMap<String, ComponentType> componentTypePool = new HashMap<String, ComponentType>();
    @Override
    public ComponentType getByKey(String key) {
        if(componentTypePool.containsKey(key)){
            return componentTypePool.get(key);
        }
        try (Connection connection = ConnectionFactory.getInstance().getConnection()) {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM \"componentTypes\" WHERE id = ?");
            ps.setString(1, key);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                ComponentType componentType =  new ComponentType(rs.getString("id"), rs.getString("name"), rs.getString("description"), ComponentClass.valueOf(rs.getString("class")), rs.getInt("size"), rs.getBigDecimal("basePrice"));
                componentTypePool.put(key, componentType);
                return componentType;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new InvalidSQLException("An error occurred when tyring to read from the database.");
        }
        return null;
    }

    @Override
    public List<ComponentType> getAll() {
        List<ComponentType> componentTypes = new ArrayList<>();
        try (Connection connection = ConnectionFactory.getInstance().getConnection()) {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM \"componentTypes\"");
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                componentTypes.add(new ComponentType(rs.getString("id"), rs.getString("name"), rs.getString("description"), ComponentClass.valueOf(rs.getString("class")), rs.getInt("size"), rs.getBigDecimal("basePrice")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new InvalidSQLException("An error occurred when tyring to read from the database.");
        }
        return componentTypes;
    }
}