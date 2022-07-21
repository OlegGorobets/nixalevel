package com.nixalevel.lesson10.service;

import com.nixalevel.lesson10.model.vehicle.Vehicle;
import com.nixalevel.lesson10.repository.CrudRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedList;
import java.util.List;

public abstract class VehicleService<T extends Vehicle> {
    private static final Logger LOGGER = LoggerFactory.getLogger(VehicleService.class);
    protected final CrudRepository<T> repository;

    protected VehicleService(CrudRepository<T> repository) {
        this.repository = repository;
    }

    public List<T> createVehicles(int count) {
        final List<T> result = new LinkedList<>();
        for (int i = 0; i < count; i++) {
            final T vehicle = create();
            result.add(vehicle);
            LOGGER.info("Created " + vehicle.getClass().getSimpleName() + " {}", vehicle.getId());
        }
        return result;
    }

    protected abstract T create();

    public boolean saveVehicles(List<T> vehicle) {
        repository.create(vehicle);
        return true;
    }

    public void printAll() {
        for (T vehicle : repository.getAll()) {
            LOGGER.info(vehicle.toString());
        }
    }

    public boolean deleteProductByIndex(List<T> vehicles, int index) {
        final T vehicle = vehicles.get(index);
        repository.delete(vehicle.getId());
        LOGGER.info("\n" + vehicle.getClass().getSimpleName() + " {} removed from the list.", vehicle);
        return true;
    }
}