package com.nixalevel.lesson10.repository;

import com.nixalevel.lesson10.model.Bus;

import java.util.List;

public interface BusCrudRepository {
    Bus getById(String id);

    List<Bus> getAll();

    boolean create(Bus bus);

    boolean create(List<Bus> bus);

    boolean update(Bus bus);

    boolean delete(String id);
}
