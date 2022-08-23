package com.revature.buyNlarge.daos;
import com.revature.buyNlarge.models.Component;
import com.revature.buyNlarge.utils.custom_exceptions.InvalidSQLException;
import java.util.List;

public class ComponentDAO implements DAO<Component> {

    @Override
    public void save(Component component) {throw new InvalidSQLException("An error occurred when tyring to save to the database.");}

    public void save(Component component, String shipID) {throw new InvalidSQLException("An error occurred when tyring to save to the database.");}

    @Override
    public void update(Component obj) {throw new InvalidSQLException("An error occurred when tyring to read from the database.");}

    @Override
    public void delete(String id) {throw new InvalidSQLException("An error occurred when tyring to read from the database.");}

    @Override
    public Component getByKey(String key) {throw new InvalidSQLException("An error occurred when tyring to read from the database."); }

    @Override
    public List<Component> getAll() {throw new InvalidSQLException("An error occurred when tyring to read from the database.");}
}