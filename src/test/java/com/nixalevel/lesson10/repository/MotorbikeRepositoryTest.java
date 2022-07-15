package com.nixalevel.lesson10.repository;

import com.nixalevel.lesson10.model.Motorbike;
import com.nixalevel.lesson10.model.MotorbikeManufacturer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

class MotorbikeRepositoryTest {
    private MotorbikeRepository target;
    private Motorbike motorbike;

    @BeforeEach
    void setUp() {
        target = new MotorbikeRepository();
        motorbike = createSimpleMotorbike();
        target.create(motorbike);
    }

    private Motorbike createSimpleMotorbike() {
        return new Motorbike("Model", MotorbikeManufacturer.KAWASAKI, BigDecimal.ZERO, 10);
    }

    @Test
    void getById_success() {
        final Motorbike actual = target.getById(motorbike.getId());
        Assertions.assertNotNull(actual);
        Assertions.assertEquals(motorbike.getId(), actual.getId());
    }

    @Test
    void getById_fail() {
        final Motorbike actual = target.getById("1232");
        Assertions.assertNull(actual);
    }

    @Test
    void getAll_success() {
        final List<Motorbike> actual = target.getAll();
        Assertions.assertEquals(1, actual.size());
    }

    @Test
    void create_success() {
        Assertions.assertTrue(target.create(createSimpleMotorbike()));
    }

    @Test
    void create_fail() {
        Assertions.assertFalse(target.create((Motorbike) null));
    }

    @Test
    void testCreate_listSuccess() {
        Assertions.assertTrue(target.create(List.of(createSimpleMotorbike())));
    }

    @Test
    void testCreate_listFail() {
        Assertions.assertFalse(target.create((List<Motorbike>) null));
    }

    @Test
    void update_success() {
        motorbike.setPrice(BigDecimal.TEN);
        final boolean actual = target.update(motorbike);
        Assertions.assertTrue(actual);
        final Motorbike actualMotorbike = target.getById(motorbike.getId());
        Assertions.assertEquals(BigDecimal.TEN, actualMotorbike.getPrice());
    }

    @Test
    void update_fail() {
        final Motorbike otherMotorbike = createSimpleMotorbike();
        final boolean actual = target.update(otherMotorbike);
        Assertions.assertFalse(actual);
    }

    @Test
    void delete_success() {
        final boolean actual = target.delete(motorbike.getId());
        Assertions.assertTrue(actual);
    }

    @Test
    void delete_fail() {
        final boolean actual = target.delete("1234");
        Assertions.assertFalse(actual);
    }
}