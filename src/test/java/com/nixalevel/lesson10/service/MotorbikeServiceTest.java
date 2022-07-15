package com.nixalevel.lesson10.service;

import com.nixalevel.lesson10.model.Motorbike;
import com.nixalevel.lesson10.model.MotorbikeManufacturer;
import com.nixalevel.lesson10.repository.MotorbikeRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.util.List;

class MotorbikeServiceTest {

    private MotorbikeService target;
    private Motorbike motorbike;
    private MotorbikeRepository motorbikeRepository;

    private int index;

    @BeforeEach
    void setUp() {
        motorbikeRepository = Mockito.mock(MotorbikeRepository.class);
        target = new MotorbikeService();
        motorbike = createSimpleMotorbike();
    }

    private Motorbike createSimpleMotorbike() {
        return new Motorbike("Model", MotorbikeManufacturer.KAWASAKI, BigDecimal.ZERO, 100);
    }

    @Test
    void createBuses_negativeCount() {
        final List<Motorbike> actual = target.createMotorbikes(-1);
        Assertions.assertEquals(0, actual.size());
    }

    @Test
    void createBuses_zeroCount() {
        final List<Motorbike> actual = target.createMotorbikes(0);
        Assertions.assertEquals(0, actual.size());
    }

    @Test
    void createBuses_fiveCount() {
        final List<Motorbike> actual = target.createMotorbikes(5);
        Assertions.assertEquals(5, actual.size());
    }

    @Test
    void saveBuses() {
        final boolean actual = target.saveMotorbikes(List.of(motorbike));
        Assertions.assertTrue(actual);
    }

    @Test
    void printAll() {
        List<Motorbike> motorbikes = List.of(createSimpleMotorbike(), createSimpleMotorbike());
        Mockito.when(motorbikeRepository.getAll()).thenReturn(motorbikes);
        target.printAll();
    }

    @Test
    void deleteProductByIndex_oneFromOne() {
        final List<Motorbike> actual = target.createMotorbikes(1);
        int index = 0;
        Assertions.assertNotNull(target.deleteProductByIndex(actual, index));
    }

    @Test
    void deleteProductByIndex_success() {
        index = 4;
        final List<Motorbike> actual = target.createMotorbikes(5);
        final Motorbike motorbike1 = actual.get(index);
        Mockito.when(motorbikeRepository.delete(motorbike1.getId())).thenReturn(true);
        Assertions.assertTrue(target.deleteProductByIndex(actual, index));
    }

    @Test
    void deleteProductByIndex_failIndex() {
        index = 5;
        final List<Motorbike> actual = target.createMotorbikes(5);
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> target.deleteProductByIndex(actual, index));
    }

    @Test
    void changeProductByIndex() {
        index = 0;
        final List<Motorbike> actual = target.createMotorbikes(5);
        Assertions.assertEquals(11, target.changeProductByIndex(actual, 3, 300));
    }
}