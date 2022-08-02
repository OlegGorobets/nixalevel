package com.nixalevel.lesson10.model;

import java.math.BigDecimal;

public class Motorbike extends Vehicle {

    private int maxSpeed;
    private MotorbikeManufacturer motorbikeManufacturer;
    private static int vehicleCount;

    public int getVehicleCount() {
        return vehicleCount;
    }

    public Motorbike(String model, MotorbikeManufacturer motorbikeManufacturer, BigDecimal price, int maxSpeed) {
        super(model, price, VehicleType.MOTORBIKE);
        this.motorbikeManufacturer = motorbikeManufacturer;
        this.maxSpeed = maxSpeed;
        vehicleCount++;
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
