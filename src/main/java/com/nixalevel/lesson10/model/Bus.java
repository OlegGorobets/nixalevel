package com.nixalevel.lesson10.model;

import java.math.BigDecimal;
import java.util.List;

public class Bus extends Vehicle {

    private int numberOfSeats;
    private BusManufacturer busManufacturer;
    private static int vehicleCount;

    public int getVehicleCount() {
        return vehicleCount;
    }

    public Bus(String model, BusManufacturer busManufacturer, BigDecimal price, int numberOfSeats) {
        super(model, price, VehicleType.BUS);
        this.busManufacturer = busManufacturer;
        this.numberOfSeats = numberOfSeats;
        vehicleCount++;
    }

    public Bus(String model, BusManufacturer busManufacturer, BigDecimal price, int numberOfSeats,
               List<String> details) {
        super(model, price, VehicleType.BUS, details);
        this.busManufacturer = busManufacturer;
        this.numberOfSeats = numberOfSeats;
        vehicleCount++;
    }

    public int getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setNumberOfSeats(int numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }

    public BusManufacturer getBusManufacturer() {
        return busManufacturer;
    }

    public void setBusManufacturer(BusManufacturer busManufacturer) {
        this.busManufacturer = busManufacturer;
    }

    @Override
    public String toString() {
        return "Bus{" +
                "numberOfSeats=" + numberOfSeats +
                ", id='" + id + '\'' +
                ", model='" + model + '\'' +
                ", price=" + price +
                ", busManufacturer=" + busManufacturer +
                ", details=" + details +
                '}';
    }
}
