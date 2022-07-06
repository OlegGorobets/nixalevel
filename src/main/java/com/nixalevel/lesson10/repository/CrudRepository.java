package com.nixalevel.lesson10.repository;

import com.nixalevel.lesson10.model.Auto;

import java.util.List;

public interface CrudRepository {
    Auto getById(String id);

    List<Auto> getAll();

    boolean create(Auto auto);

    boolean create(List<Auto> auto);

    boolean update(Auto auto);

    boolean delete(String id);
}
