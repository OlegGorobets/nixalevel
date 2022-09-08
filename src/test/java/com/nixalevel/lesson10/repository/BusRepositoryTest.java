/*
package com.nixalevel.lesson10.repository;

import com.nixalevel.lesson10.model.Bus;
import com.nixalevel.lesson10.model.BusManufacturer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

class BusRepositoryTest {

    private BusRepository target;
    private Bus bus;

    @BeforeEach
    void setUp() {
        target = new BusRepository();
        bus = createSimpleBus();
        target.create(bus);
    }

    private Bus createSimpleBus() {
        return new Bus("Model", BusManufacturer.MERCEDES, BigDecimal.ZERO, 10);
    }

    @Test
    void getById_success() {
        final Bus actual = target.getById(bus.getId());
        Assertions.assertNotNull(actual);
        Assertions.assertEquals(bus.getId(), actual.getId());
    }

    @Test
    void getById_fail() {
        final Bus actual = target.getById("1232");
        Assertions.assertNull(actual);
    }

    @Test
    void getAll_success() {
        final List<Bus> actual = target.getAll();
        Assertions.assertEquals(1, actual.size());
    }

    @Test
    void create_success() {
        Assertions.assertTrue(target.create(createSimpleBus()));
    }

    @Test
    void create_fail() {
        Assertions.assertFalse(target.create((Bus) null));
    }

    @Test
    void testCreate_listSuccess() {
        Assertions.assertTrue(target.create(List.of(createSimpleBus())));
    }

    @Test
    void testCreate_listFail() {
        Assertions.assertFalse(target.create((List<Bus>) null));
    }

    @Test
    void update_success() {
        bus.setPrice(BigDecimal.TEN);
        final boolean actual = target.update(bus);
        Assertions.assertTrue(actual);
        final Bus actualBus = target.getById(bus.getId());
        Assertions.assertEquals(BigDecimal.TEN, actualBus.getPrice());
    }

    @Test
    void update_fail() {
        final Bus otherBus = createSimpleBus();
        final boolean actual = target.update(otherBus);
        Assertions.assertFalse(actual);
    }

    @Test
    void delete_success() {
        final boolean actual = target.delete(bus.getId());
        Assertions.assertTrue(actual);
    }

    @Test
    void delete_fail() {
        final boolean actual = target.delete("1234");
        Assertions.assertFalse(actual);
    }
}*/
