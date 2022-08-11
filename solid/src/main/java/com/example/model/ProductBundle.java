package com.example.model;

public class ProductBundle extends ProductNotifiable {
    private int amount;

    public ProductBundle(long id, boolean available, String title, double price, String channel, String email, int amount) {
        super(id, available, title, price, channel, email);
        this.amount = amount;
    }

    public ProductBundle() {
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "ProductBundle{" +
                "amount=" + amount +
                ", channel='" + channel + '\'' +
                ", email='" + email + '\'' +
                ", id=" + id +
                ", available=" + available +
                ", title='" + title + '\'' +
                ", price=" + price +
                '}';
    }
}