package com.nixalevel.module.model.invoice;

import com.nixalevel.module.model.customer.Customer;
import com.nixalevel.module.model.product.Product;

import java.util.Date;
import java.util.List;

public class Invoice {
    private List<Product> productList;
    private Customer customer;
    private InvoiceType type;
    private Date created;
    private static int invoiceCount;

    public Invoice(List<Product> productList, Customer customer, InvoiceType type, Date created) {
        this.productList = productList;
        this.customer = customer;
        this.type = type;
        this.created = created;
        invoiceCount++;
    }

    public Invoice() {
    }

    public static int getInvoiceCount() {
        return invoiceCount;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public InvoiceType getType() {
        return type;
    }

    public void setType(InvoiceType type) {
        this.type = type;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    @Override
    public String toString() {
        return "Invoice{" +
                "productList=" + productList +
                ", customer=" + customer +
                ", type=" + type +
                ", created=" + created +
                '}';
    }
}
