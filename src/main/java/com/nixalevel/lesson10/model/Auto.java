package com.nixalevel.lesson10.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Auto")
public class Auto extends Vehicle {
    @Column(name="auto_body_type")
    private String bodyType;
    @Column(name="auto_manufacturer")
    private AutoManufacturer autoManufacturer;
    @Column(name="auto_created")
    private Date created;
    @Column(name="auto_count")
    private int count;

    @OneToMany(targetEntity=Auto.class, mappedBy="engine", fetch= FetchType.EAGER)
    private List<String> engine;
    private static int vehicleCount;

    public Auto() {
        super();
    }

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

    public Auto(String id, String model, AutoManufacturer autoManufacturer, BigDecimal price, String bodyType,
                List<String> details, int count, Date created) {
        super(id, model, price, VehicleType.AUTO , details);
        this.autoManufacturer = autoManufacturer;
        this.bodyType = bodyType;
        this.count = count;
        this.created = created;
    }

    public static class Builder {
        private final Auto newAuto;
        public Builder() {
            newAuto = new Auto();
        }

        public Builder withModel(String model) {
            newAuto.model = model;
            return this;
        }

        public Builder withAutoManufacturer(AutoManufacturer autoManufacturer) {
            newAuto.autoManufacturer = autoManufacturer;
            return this;
        }

        public Builder withBodyType(String bodyType) {
            newAuto.bodyType = bodyType;
            return this;
        }

        public Builder withPrice(BigDecimal price) {
            newAuto.price = price;
            return this;
        }

        public Builder withCount(int count) {
            newAuto.count = count;
            return this;
        }

        public Auto build() {
            if (newAuto.price == null) {
                throw new IllegalArgumentException("Price is null");
            } else if (newAuto.count <= 0) {
                throw new IllegalArgumentException("Count <= 0>");
            } else if (newAuto.bodyType.length() > 20) {
                throw new IllegalArgumentException("BodyType length > 20");
            } else if (newAuto.id == null) {
                throw new IllegalArgumentException("Id is null");
            }
            return newAuto;
        }

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
                ", created=" + created +
                ", count=" + count +
                ", engine=" + engine +
                ", id='" + id + '\'' +
                ", model='" + model + '\'' +
                ", price=" + price +
                ", type=" + type +
                ", details=" + details +
                '}';
    }
}
