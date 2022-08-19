package com.nixalevel.module.repository;

import com.nixalevel.module.model.invoice.Invoice;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

class ShopRepositoryTest {

    private ShopRepository target;

    @BeforeEach
    void setUp() {
        target = new ShopRepository();
        Invoice invoice = new Invoice();
        target.add(invoice);
    }

    @Test
    void getAll() {
        final List<Invoice> actual = target.getAll();
        Assertions.assertNotNull(actual);
        Assertions.assertEquals(1, actual.size());
    }

    @Test
    void add_fail() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> target.add(null));
    }

    @Test
    void add_success() {
        final boolean actual = target.add(new Invoice());
        Assertions.assertTrue(actual);
    }
}