package com.nixalevel.lesson10.model;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Bus")
public class Bus extends Vehicle {

    private int numberOfSeats;
    private BusManufacturer busManufacturer;
    private Date created;
    private int count;
    private static int vehicleCount;

    public int getVehicleCount() {
        return vehicleCount;
    }

    public Bus(String model, BusManufacturer busManufacturer, BigDecimal price, int numberOfSeats) {
        super(model, price, VehicleType.BUS);
        this.busManufacturer = busManufacturer;
        this.numberOfSeats = numberOfSeats;
        vehicleCount++;
    }

    public Bus(String model, BusManufacturer busManufacturer, BigDecimal price, int numberOfSeats,
               List<String> details) {
        super(model, price, VehicleType.BUS, details);
        this.busManufacturer = busManufacturer;
        this.numberOfSeats = numberOfSeats;
        vehicleCount++;
    }

    public Bus(String id, String model, BusManufacturer busManufacturer, BigDecimal price, int numberOfSeats,
                List<String> details, int count, Date created) {
        super(id, model, price, VehicleType.BUS , details);
        this.busManufacturer = busManufacturer;
        this.numberOfSeats = numberOfSeats;
        this.count = count;
        this.created = created;
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
        return "Bus{" +
                "numberOfSeats=" + numberOfSeats +
                ", busManufacturer=" + busManufacturer +
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
