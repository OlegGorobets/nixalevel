package com.example.model;

public abstract class Product {
    protected long id;
    protected boolean available;
    protected String title;
    protected double price;

    public Product(long id, boolean available, String title, double price) {
        this.id = id;
        this.available = available;
        this.title = title;
        this.price = price;
    }

    public Product() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}