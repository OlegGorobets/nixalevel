package com.nixalevel.module.service;

import com.nixalevel.module.model.customer.Customer;
import com.nixalevel.module.model.invoice.Invoice;
import com.nixalevel.module.repository.ShopRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.ByteArrayInputStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ShopServiceTest {

    private ShopService target;
    private ShopRepository shopRepository;
    private Invoice invoice = new Invoice();
    private Customer customer = new Customer();


    @BeforeEach
    void setUp() {
        ByteArrayInputStream in = new ByteArrayInputStream("1".getBytes());
        System.setIn(in);

        shopRepository = Mockito.mock(ShopRepository.class);
        target = new ShopService(shopRepository);
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

    @Test
    void getAll() {
        List<Invoice> actual = List.of(invoice, invoice);
        Mockito.when(target.getAll()).thenReturn(actual);
        Assertions.assertNotNull(actual);
        Assertions.assertEquals(2, actual.size());
    }

    @Test
    void printAll() {
        List<Invoice> actual = List.of(invoice, invoice);
        Mockito.when(target.getAll()).thenReturn(actual);
        target.printAll();
    }

    @Test
    void generateRandomInvoice() {
        assertInstanceOf(Invoice.class, target.generateRandomInvoice(customer));
    }
}