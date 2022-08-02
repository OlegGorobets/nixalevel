package com.nixalevel.lesson10.repository;

import com.nixalevel.lesson10.model.Vehicle;

import java.util.List;
import java.util.Optional;

public interface CrudRepository<T extends Vehicle> {
    T getById(String id);

    Optional<T> findById(String id);

    List<T> getAll();

    boolean create(T vehicle);

    boolean create(List<T> vehicle);

    boolean update(T vehicle);

    boolean delete(String id);
}
