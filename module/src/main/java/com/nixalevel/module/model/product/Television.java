package com.nixalevel.module.model.product;

import java.math.BigDecimal;

public class Television extends Product{
    private double diagonal;
    private String country;

    public Television(String series, double diagonal, ScreenType screenType, String country, BigDecimal price) {
        super(series, screenType, price);
        this.diagonal = diagonal;
        this.country = country;
    }

    public double getDiagonal() {
        return diagonal;
    }

    public void setDiagonal(double diagonal) {
        this.diagonal = diagonal;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return "Television{" +
                "diagonal=" + diagonal +
                ", country='" + country + '\'' +
                ", id='" + id + '\'' +
                ", series='" + series + '\'' +
                ", screenType=" + screenType +
                ", price=" + price +
                '}';
    }
}
