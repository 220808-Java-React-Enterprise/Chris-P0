package com.revature.buyNlarge.daos;
import com.revature.buyNlarge.models.*;
import com.revature.buyNlarge.utils.custom_exceptions.InvalidSQLException;
import com.revature.buyNlarge.utils.database.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ShipyardDAO implements DAO<Shipyard> {

    @Override
    public void save(Shipyard shipyard) {throw new InvalidSQLException("An error occurred when tyring to save to the database.");}

    @Override
    public void update(Shipyard obj) {throw new InvalidSQLException("An error occurred when tyring to save to the database.");}

    @Override
    public void delete(String id) {throw new InvalidSQLException("An error occurred when tyring to save to the database.");}

    @Override
    public Shipyard getByKey(String key) {
        try (Connection connection = ConnectionFactory.getInstance().getConnection()) {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM shipyards WHERE id = ?");
            ps.setString(1, key);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Shipyard(rs.getString("id"), rs.getString("name"), rs.getString("description"), rs.getString("locationFormula"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new InvalidSQLException("An error occurred when tyring to read from the database.");
        }
        return null;
    }

    @Override
    public List<Shipyard> getAll() {
        ArrayList<Shipyard> shipyards = new ArrayList<Shipyard>();
        try (Connection connection = ConnectionFactory.getInstance().getConnection()) {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM shipyards");
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                shipyards.add(new Shipyard(rs.getString("id"), rs.getString("name"), rs.getString("description"), rs.getString("locationFormula")));
            }
        } catch (SQLException e){
            e.printStackTrace();
            throw new InvalidSQLException("An error occurred when tyring to read from the database.");
        }
        return shipyards;
    }
}