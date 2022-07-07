package com.nixalevel.lesson10.model;

import java.math.BigDecimal;
import java.util.UUID;

public class Vehicle {
    protected final String id;
    protected String model;
    protected BigDecimal price;
    protected AutoManufacturer autoManufacturer;
    protected BusManufacturer busManufacturer;
    protected MotorbikeManufacturer motorbikeManufacturer;

    protected Vehicle(String model, AutoManufacturer autoManufacturer, BigDecimal price) {
        this.id = UUID.randomUUID().toString();
        this.model = model;
        this.autoManufacturer = autoManufacturer;
        this.price = price;
    }

    public Vehicle(String model, BusManufacturer busManufacturer, BigDecimal price) {
        this.id = UUID.randomUUID().toString();
        this.model = model;
        this.price = price;
        this.busManufacturer = busManufacturer;
    }

    public Vehicle(String model, MotorbikeManufacturer motorbikeManufacturer, BigDecimal price) {
        this.id = UUID.randomUUID().toString();
        this.model = model;
        this.price = price;
        this.motorbikeManufacturer = motorbikeManufacturer;
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

    public AutoManufacturer getAutoManufacturer() { //
        return autoManufacturer;
    }

    public void setAutoManufacturer(AutoManufacturer autoManufacturer) { //
        this.autoManufacturer = autoManufacturer;
    }

    public BusManufacturer getBusManufacturer() {
        return busManufacturer;
    }

    public void setBusManufacturer(BusManufacturer busManufacturer) {
        this.busManufacturer = busManufacturer;
    }

    public MotorbikeManufacturer getMotorbikeManufacturer() {
        return motorbikeManufacturer;
    }

    public void setMotorbikeManufacturer(MotorbikeManufacturer motorbikeManufacturer) {
        this.motorbikeManufacturer = motorbikeManufacturer;
    }
}
