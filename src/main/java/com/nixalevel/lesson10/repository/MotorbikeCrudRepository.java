package com.nixalevel.lesson10.repository;

import com.nixalevel.lesson10.model.vehicle.Motorbike;

import java.util.List;
import java.util.Optional;

public interface MotorbikeCrudRepository {
    Motorbike getById(String id);

    Optional<Motorbike> findById(String id);

    List<Motorbike> getAll();

    boolean create(Motorbike motorbike);

    boolean create(List<Motorbike> motorbike);

    boolean update(Motorbike motorbike);

    boolean delete(String id);
}
