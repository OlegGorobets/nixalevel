package com.nixalevel.lesson10.model;

import com.nixalevel.lesson10.model.vehicle.Vehicle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Container<T extends Vehicle> {

    private static final Logger LOGGER = LoggerFactory.getLogger(Container.class);
    private static final Random RANDOM = new Random();
    private final List<T> vehicles;


    public Container() {
        vehicles = new LinkedList<>();
    }

    public void add(T vehicle) {
        vehicles.add(vehicle);
    }

    public void add(List<T> vehicle) {
        vehicles.addAll(vehicle);
    }

    public void applyDiscount() {
        for (T vehicle : vehicles) {
            BigDecimal prise = vehicle.getPrice();
            BigDecimal discount = prise
                    .divide(BigDecimal.valueOf(100))
                    .multiply(BigDecimal.valueOf(RANDOM.nextInt(10, 30)));
            vehicle.setPrice(prise.subtract(discount));
            LOGGER.info("\n" + vehicle.getClass().getSimpleName() + " {} discount applied.", vehicle);
        }
    }

    public void increasePrice() {
        Number x = RANDOM.nextInt(Integer.MAX_VALUE);
        for (T vehicle : vehicles) {
            BigDecimal prise = vehicle.getPrice();
            vehicle.setPrice(prise.add(BigDecimal.valueOf(x.intValue())));
            LOGGER.info("\n" + vehicle.getClass().getSimpleName() + " {} price increased.", vehicle);
        }
    }

    public void printAll() {
        for (T vehicle : vehicles) {
            LOGGER.info(vehicle.toString());
        }
    }
}
