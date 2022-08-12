package com.example.model;

public class ProductNotifiable extends Product implements Notification {
    protected String channel;
    protected String email;

    public ProductNotifiable(long id, boolean available, String title, double price, String channel, String email) {
        super(id, available, title, price);
        this.channel = channel;
        this.email = email;
    }

    public ProductNotifiable() {
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = generateAddressForNotification();
    }

    @Override
    public String generateAddressForNotification() {
        return "somerandommail@gmail.com";
    }

    @Override
    public String toString() {
        return "ProductNotifiable{" +
                "channel='" + channel + '\'' +
                ", email='" + email + '\'' +
                ", id=" + id +
                ", available=" + available +
                ", title='" + title + '\'' +
                ", price=" + price +
                '}';
    }
}