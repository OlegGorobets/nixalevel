package com.nixalevel.lesson10.model;

import java.math.BigDecimal;

public class Bus extends Vehicle {

    private int numberOfSeats;

    public Bus(String model, BusManufacturer busManufacturer, BigDecimal price, int numberOfSeats) {
        super(model, busManufacturer, price);
        this.numberOfSeats = numberOfSeats;
    }

    public int getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setNumberOfSeats(int numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
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
