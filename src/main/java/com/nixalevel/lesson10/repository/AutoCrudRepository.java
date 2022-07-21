package com.nixalevel.lesson10.repository;

import com.nixalevel.lesson10.model.vehicle.Auto;

import java.util.List;
import java.util.Optional;

public interface AutoCrudRepository {
    Auto getById(String id);

    Optional<Auto> findById(String id);

    List<Auto> getAll();

    boolean create(Auto auto);

    boolean create(List<Auto> auto);

    boolean update(Auto auto);

    boolean delete(String id);
}
