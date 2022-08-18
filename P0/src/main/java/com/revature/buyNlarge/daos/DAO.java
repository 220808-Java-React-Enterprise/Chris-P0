package com.revature.buyNlarge.daos;
import java.io.IOException;
import java.util.List;

public interface DAO<T> {
    void save(T obj) throws IOException;
    void update(T obj);
    void delete(String id);
    T getByKey(String id);
    List<T> getAll();
}
