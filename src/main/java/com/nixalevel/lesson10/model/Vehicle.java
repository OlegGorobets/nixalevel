package com.nixalevel.lesson10.model;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;

public abstract class Vehicle {
    protected String id;
    protected String model;
    protected BigDecimal price;
    protected static int vehicleCount;
    protected VehicleType type;
    protected List<String> details;

    public Vehicle() {
        this.id = UUID.randomUUID().toString();
    }

    public int getVehicleCount() {
        return vehicleCount;
    }

    protected Vehicle(String model, BigDecimal price, VehicleType type) {
        this.id = UUID.randomUUID().toString();
        this.model = model;
        this.price = price;
        this.type = type;
        vehicleCount++;
    }

    protected Vehicle(String model, BigDecimal price, VehicleType type, List<String> details) {
        this.id = UUID.randomUUID().toString();
        this.model = model;
        this.price = price;
        this.type = type;
        this.details = details;
        vehicleCount++;
    }

    protected Vehicle(String id, String model, BigDecimal price, VehicleType type, List<String> details) {
        this.id = id;
        this.model = model;
        this.price = price;
        this.type = type;
        this.details = details;
        vehicleCount++;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public List<String> getDetails() {
        return details;
    }

    public void setDetails(List<String> details) {
        this.details = details;
    }

    public static class SortByPrice implements Comparator<Vehicle> {

        @Override
        public int compare(Vehicle o1, Vehicle o2) {
            return o2.getPrice().compareTo(o1.getPrice());
        }
    }

    public static class SortByName implements Comparator<Vehicle> {

        @Override
        public int compare(Vehicle o1, Vehicle o2) {
            return o1.getModel().compareTo(o2.getModel());
        }
    }

    public static class SortByQuantity implements Comparator<Vehicle> {

        @Override
        public int compare(Vehicle o1, Vehicle o2) {
            return o1.getVehicleCount() - (o2.getVehicleCount());
        }
    }
}
