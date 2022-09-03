package com.nixalevel.lesson10.service;

import com.nixalevel.lesson10.model.Vehicle;
import com.nixalevel.lesson10.repository.CrudRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public abstract class VehicleService<T extends Vehicle> {
    private static final Logger LOGGER = LoggerFactory.getLogger(VehicleService.class);
    protected final CrudRepository<T> repository;

    protected VehicleService(CrudRepository<T> repository) {
        this.repository = repository;
    }

    public void createVehicles(int count) {
        for (int i = 0; i < count; i++) {
            final T vehicle = create();
            repository.create(vehicle);
            LOGGER.info("Created " + vehicle.getClass().getSimpleName() + " {}", vehicle.getId());
        }
    }

    protected abstract T create();

    public void print() {
        List<T> vehicleList = repository.getAll();
        for (T t : vehicleList) {
            System.out.printf("%s%n", t);
        }
        LOGGER.info("Printed.");
    }

    public boolean deleteProductByIndex(List<T> vehicles, int index) {
        final T vehicle = vehicles.get(index);
        repository.delete(vehicle.getId());
        LOGGER.info("\n" + vehicle.getClass().getSimpleName() + " {} removed from the list.", vehicle);
        return true;
    }

    public boolean remove(int index) {
        List<T> vehicleList = repository.getAll();
        vehicleList.remove(index);
        LOGGER.info("Removed.");
        return true;
    }

    public List<T> getAll() {
        return repository.getAll();
    }
}
