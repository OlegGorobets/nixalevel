package com.nixalevel.lesson10.model;

import java.util.Date;
import java.util.List;

public class Invoice {
    private String id;
    private Date created;
    private List<Vehicle> vehicles;

    public Invoice(String id, Date created, List<Vehicle> vehicles) {
        this.id = id;
        this.created = created;
        this.vehicles = vehicles;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public List<Vehicle> getVehicles() {
        return vehicles;
    }

    public void setVehicles(List<Vehicle> vehicles) {
        this.vehicles = vehicles;
    }

    @Override
    public String toString() {
        return "Invoice{" +
                "id='" + id + '\'' +
                ", created=" + created +
                ", vehicles=" + vehicles +
                '}';
    }
}
