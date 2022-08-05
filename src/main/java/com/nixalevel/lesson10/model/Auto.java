package com.nixalevel.lesson10.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class Auto extends Vehicle {
    private String bodyType;
    private AutoManufacturer autoManufacturer;
    private Date created; //Date
    private int count;
    private List<String> engine;
    private static int vehicleCount;

    public int getVehicleCount() {
        return vehicleCount;
    }

    public Auto(String model, AutoManufacturer autoManufacturer, BigDecimal price, String bodyType) {
        super(model, price, VehicleType.AUTO);
        this.autoManufacturer = autoManufacturer;
        this.bodyType = bodyType;
        vehicleCount++;
    }

    public Auto(String model, AutoManufacturer autoManufacturer, BigDecimal price, String bodyType,
                List<String> details) {
        super(model, price, VehicleType.AUTO, details);
        this.autoManufacturer = autoManufacturer;
        this.bodyType = bodyType;
        vehicleCount++;
    }

    public Auto(String model, AutoManufacturer autoManufacturer, BigDecimal price, String bodyType,
                Date created, int count, List<String> engine) {
        super(model, price, VehicleType.AUTO);
        this.autoManufacturer = autoManufacturer;
        this.bodyType = bodyType;
        this.created = created;
        this.count = count;
        this.engine = engine;
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

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<String> getEngine() {
        return engine;
    }

    public void setEngine(List<String> engine) {
        this.engine = engine;
    }

    public static void setVehicleCount(int vehicleCount) {
        Auto.vehicleCount = vehicleCount;
    }

    @Override
    public String toString() {
        return "Auto{" +
                "bodyType='" + bodyType + '\'' +
                ", autoManufacturer=" + autoManufacturer +
                ", created='" + created + '\'' +
                ", count=" + count +
                ", engine=" + engine +
                '}';
    }
}
