/*
package com.nixalevel.lesson10.repository;

import com.nixalevel.lesson10.model.Auto;
import com.nixalevel.lesson10.model.AutoManufacturer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

class AutoRepositoryTest {
    private AutoRepository target;
    private Auto auto;

    @BeforeEach
    void setUp() {
        target = new AutoRepository();
        auto = createSimpleAuto();
        target.create(auto);
    }

    private Auto createSimpleAuto() {
        return new Auto("Model", AutoManufacturer.BMW, BigDecimal.ZERO, "Type");
    }

    @Test
    void getById_success() {
        final Auto actual = target.getById(auto.getId());
        Assertions.assertNotNull(actual);
        Assertions.assertEquals(auto.getId(), actual.getId());
    }

    @Test
    void getById_fail() {
        final Auto actual = target.getById("1232");
        Assertions.assertNull(actual);
    }

    @Test
    void getAll_success() {
        final List<Auto> actual = target.getAll();
        Assertions.assertEquals(1, actual.size());
    }

    @Test
    void create_success() {
        Assertions.assertTrue(target.create(createSimpleAuto()));
    }

    @Test
    void create_fail() {
        Assertions.assertFalse(target.create((Auto) null));
    }

    @Test
    void testCreate_listSuccess() {
        Assertions.assertTrue(target.create(List.of(createSimpleAuto())));
    }

    @Test
    void testCreate_listFail() {
        Assertions.assertFalse(target.create((List<Auto>) null));
    }

    @Test
    void update_success() {
        auto.setPrice(BigDecimal.TEN);
        final boolean actual = target.update(auto);
        Assertions.assertTrue(actual);
        final Auto actualAuto = target.getById(auto.getId());
        Assertions.assertEquals(BigDecimal.TEN, actualAuto.getPrice());
    }

    @Test
    void update_fail() {
        final Auto actualAuto = createSimpleAuto();
        final boolean actual = target.update(actualAuto);
        Assertions.assertFalse(actual);
    }

    @Test
    void delete_success() {
        final boolean actual = target.delete(auto.getId());
        Assertions.assertTrue(actual);
    }

    @Test
    void delete_fail() {
        final boolean actual = target.delete("1234");
        Assertions.assertFalse(actual);
    }

}*/
