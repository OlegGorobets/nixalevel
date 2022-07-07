package com.nixalevel.lesson10.repository;

import com.nixalevel.lesson10.model.Motorbike;

import java.util.List;

public interface MotorbikeCrudRepository {
    Motorbike getById(String id);

    List<Motorbike> getAll();

    boolean create(Motorbike motorbike);

    boolean create(List<Motorbike> motorbike);

    boolean update(Motorbike motorbike);

    boolean delete(String id);
}
