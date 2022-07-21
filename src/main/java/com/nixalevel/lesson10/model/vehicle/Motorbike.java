package com.nixalevel.lesson10.model.vehicle;

import java.math.BigDecimal;

public class Motorbike extends Vehicle {

    private int maxSpeed;
    private MotorbikeManufacturer motorbikeManufacturer;

    public Motorbike(String model, MotorbikeManufacturer motorbikeManufacturer, BigDecimal price, int maxSpeed) {
        super(model, price);
        this.motorbikeManufacturer = motorbikeManufacturer;
        this.maxSpeed = maxSpeed;
    }

    public int getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(int maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public MotorbikeManufacturer getMotorbikeManufacturer() {
        return motorbikeManufacturer;
    }

    public void setMotorbikeManufacturer(MotorbikeManufacturer motorbikeManufacturer) {
        this.motorbikeManufacturer = motorbikeManufacturer;
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
