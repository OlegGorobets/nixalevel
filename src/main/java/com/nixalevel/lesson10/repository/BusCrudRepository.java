package com.nixalevel.lesson10.repository;

import com.nixalevel.lesson10.model.Bus;

import java.util.List;
import java.util.Optional;

public interface BusCrudRepository {
    Bus getById(String id);

    Optional<Bus> findById(String id);

    List<Bus> getAll();

    boolean create(Bus bus);

    boolean create(List<Bus> bus);

    boolean update(Bus bus);

    boolean delete(String id);
}
