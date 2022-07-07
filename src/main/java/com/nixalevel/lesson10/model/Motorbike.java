package com.nixalevel.lesson10.model;

import java.math.BigDecimal;

public class Motorbike extends Vehicle {

    private int maxSpeed;

    public Motorbike(String model, MotorbikeManufacturer motorbikeManufacturer, BigDecimal price, int maxSpeed) {
        super(model, motorbikeManufacturer, price);
        this.maxSpeed = maxSpeed;
    }

    public int getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(int maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    @Override
    public String toString() {
        return "Motorbike{" +
                "maxSpeed=" + maxSpeed +
                ", id='" + id + '\'' +
                ", model='" + model + '\'' +
                ", price=" + price +
                ", motorbikeManufacturer=" + motorbikeManufacturer +
                '}';
    }
}
