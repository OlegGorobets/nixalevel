package com.nixalevel.module.model.product;

import java.math.BigDecimal;
import java.util.UUID;

public abstract class Product {
    protected String id;
    protected String series;
    protected ScreenType screenType;
    protected BigDecimal price;

    public Product(String series, ScreenType screenType, BigDecimal price) {
        this.id = UUID.randomUUID().toString();
        this.series = series;
        this.screenType = screenType;
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        this.series = series;
    }

    public ScreenType getScreenType() {
        return screenType;
    }

    public void setScreenType(ScreenType screenType) {
        this.screenType = screenType;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
