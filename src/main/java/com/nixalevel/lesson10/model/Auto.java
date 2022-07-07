package com.nixalevel.lesson10.model;

import java.math.BigDecimal;

public class Auto extends Vehicle {
    private String bodyType;

    public Auto(String model, AutoManufacturer autoManufacturer, BigDecimal price, String bodyType) {
        super(model, autoManufacturer, price);
        this.bodyType = bodyType;
    }

    public String getBodyType() {
        return bodyType;
    }

    public void setBodyType(String bodyType) {
        this.bodyType = bodyType;
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
