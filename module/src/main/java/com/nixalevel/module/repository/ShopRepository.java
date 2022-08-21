package com.nixalevel.module.repository;

import com.nixalevel.module.model.invoice.Invoice;

import java.util.ArrayList;
import java.util.List;

public class ShopRepository {
    private final List<Invoice> invoices;

    public ShopRepository() {
        invoices = new ArrayList<>();
    }

    public List<Invoice> getAll() {
        return invoices;
    }

    public boolean add(Invoice invoice) {
        if (invoice == null) {
            throw new IllegalArgumentException("Invoice must not be null");
        }
        invoices.add(invoice);
        return true;
    }
}
