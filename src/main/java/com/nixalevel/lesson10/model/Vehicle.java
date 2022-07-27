package com.nixalevel.lesson10.model;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.UUID;

public abstract class Vehicle {
    protected String id;
    protected String model;
    protected BigDecimal price;
    protected static int vehicleCount;

    public int getVehicleCount() {
        return vehicleCount;
    }

    protected Vehicle(String model, BigDecimal price) {
        this.id = UUID.randomUUID().toString();
        this.model = model;
        this.price = price;
        vehicleCount++;
    }

    public String getId() {
        return id;
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

    public static class Sort implements Comparator<Vehicle> {

        @Override
        public int compare(Vehicle o1, Vehicle o2) {
            int resSortByPrice = o2.getPrice().compareTo(o1.getPrice());
            System.out.println(resSortByPrice);
            return resSortByPrice;
        }
    }
}