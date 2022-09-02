package com.nixalevel.lesson10.model;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Motorbike")
public class Motorbike extends Vehicle {

    private int maxSpeed;
    private MotorbikeManufacturer motorbikeManufacturer;
    private Date created;
    private int count;
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

    public Motorbike(String model, MotorbikeManufacturer motorbikeManufacturer, BigDecimal price, int maxSpeed,
                     List<String> details) {
        super(model, price, VehicleType.MOTORBIKE, details);
        this.motorbikeManufacturer = motorbikeManufacturer;
        this.maxSpeed = maxSpeed;
        vehicleCount++;
    }

    public Motorbike(String id, String model, MotorbikeManufacturer motorbikeManufacturer, BigDecimal price, int maxSpeed,
               List<String> details, int count, Date created) {
        super(id, model, price, VehicleType.BUS , details);
        this.motorbikeManufacturer = motorbikeManufacturer;
        this.maxSpeed = maxSpeed;
        this.count = count;
        this.created = created;
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

    @Override
    public String toString() {
        return "Motorbike{" +
                "maxSpeed=" + maxSpeed +
                ", motorbikeManufacturer=" + motorbikeManufacturer +
                ", created=" + created +
                ", count=" + count +
                ", id='" + id + '\'' +
                ", model='" + model + '\'' +
                ", price=" + price +
                ", type=" + type +
                ", details=" + details +
                '}';
    }
}
