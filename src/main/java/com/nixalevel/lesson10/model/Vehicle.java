package com.nixalevel.lesson10.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Vehicle {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    protected String id;
    @Column(name = "model")
    protected String model;
    @Column(name = "price")
    protected BigDecimal price;
    protected static int vehicleCount;
    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    protected VehicleType type;
    @Column(name = "details")
    @ElementCollection(fetch = FetchType.EAGER)
    protected List<String> details;

    protected Vehicle() {
        this.id = UUID.randomUUID().toString();
    }

    public int getVehicleCount() {
        return vehicleCount;
    }

    protected Vehicle(String model, BigDecimal price, VehicleType type) {
        this.id = UUID.randomUUID().toString();
        this.model = model;
        this.price = price;
        this.type = type;
        vehicleCount++;
    }

    protected Vehicle(String model, BigDecimal price, VehicleType type, List<String> details) {
        this.id = UUID.randomUUID().toString();
        this.model = model;
        this.price = price;
        this.type = type;
        this.details = details;
        vehicleCount++;
    }

    protected Vehicle(String id, String model, BigDecimal price, VehicleType type, List<String> details) {
        this.id = id;
        this.model = model;
        this.price = price;
        this.type = type;
        this.details = details;
        vehicleCount++;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public List<String> getDetails() {
        return details;
    }

    public void setDetails(List<String> details) {
        this.details = details;
    }

    public static class SortByPrice implements Comparator<Vehicle> {

        @Override
        public int compare(Vehicle o1, Vehicle o2) {
            return o2.getPrice().compareTo(o1.getPrice());
        }
    }

    public static class SortByName implements Comparator<Vehicle> {

        @Override
        public int compare(Vehicle o1, Vehicle o2) {
            return o1.getModel().compareTo(o2.getModel());
        }
    }

    public static class SortByQuantity implements Comparator<Vehicle> {

        @Override
        public int compare(Vehicle o1, Vehicle o2) {
            return o1.getVehicleCount() - (o2.getVehicleCount());
        }
    }
}
