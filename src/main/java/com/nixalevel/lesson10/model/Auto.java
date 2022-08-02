package com.nixalevel.lesson10.model;

import java.math.BigDecimal;

public class Auto extends Vehicle {
    private String bodyType;
    private AutoManufacturer autoManufacturer;
    private static int vehicleCount;

    public int getVehicleCount() {
        return vehicleCount;
    }

    public Auto(String model, AutoManufacturer autoManufacturer, BigDecimal price, String bodyType) {
        super(model, price);
        this.autoManufacturer = autoManufacturer;
        this.bodyType = bodyType;
        vehicleCount++;
    }

    public String getBodyType() {
        return bodyType;
    }

    public void setBodyType(String bodyType) {
        this.bodyType = bodyType;
    }

    public AutoManufacturer getAutoManufacturer() {
        return autoManufacturer;
    }

    public void setAutoManufacturer(AutoManufacturer autoManufacturer) {
        this.autoManufacturer = autoManufacturer;
    }

    @Override
    public String toString() {
        return "Auto{" +
                "bodyType='" + bodyType + '\'' +
                ", id='" + id + '\'' +
                ", model='" + model + '\'' +
                ", price=" + price +
                ", manufacturer=" + autoManufacturer +
                '}';
    }
}
