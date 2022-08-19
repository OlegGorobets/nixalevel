package com.nixalevel.module.model.product;

import java.math.BigDecimal;

public class Telephone extends Product{
    private String model;

    public Telephone(String series, String model, ScreenType screenType, BigDecimal price) {
        super(series, screenType, price);
        this.model = model;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    @Override
    public String toString() {
        return "Telephone{" +
                "model='" + model + '\'' +
                ", id='" + id + '\'' +
                ", series='" + series + '\'' +
                ", screenType=" + screenType +
                ", price=" + price +
                '}';
    }
}
