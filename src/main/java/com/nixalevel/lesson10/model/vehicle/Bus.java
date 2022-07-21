package com.nixalevel.lesson10.model.vehicle;

import java.math.BigDecimal;

public class Bus extends Vehicle {

    private int numberOfSeats;
    private BusManufacturer busManufacturer;

    public Bus(String model, BusManufacturer busManufacturer, BigDecimal price, int numberOfSeats) {
        super(model, price);
        this.busManufacturer = busManufacturer;
        this.numberOfSeats = numberOfSeats;
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
                '}';
    }
}
