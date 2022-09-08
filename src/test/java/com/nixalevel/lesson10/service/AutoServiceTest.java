/*
package com.nixalevel.lesson10.service;

import com.nixalevel.lesson10.model.Auto;
import com.nixalevel.lesson10.model.AutoManufacturer;
import com.nixalevel.lesson10.repository.AutoRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyString;

class AutoServiceTest {

    private AutoService target;
    private Auto auto;
    private AutoRepository autoRepository;

    private int index;

    @BeforeEach
    void setUp() {
        autoRepository = Mockito.mock(AutoRepository.class);
        target = new AutoService();
    }

    private Auto createSimpleAuto() {
        return new Auto("Model", AutoManufacturer.KIA, BigDecimal.ZERO, "Type");
    }

    @Test
    void createBuses_negativeCount() {
        final List<Auto> actual = target.createAutos(-1);
        Assertions.assertEquals(0, actual.size());
    }

    @Test
    void createBuses_zeroCount() {
        final List<Auto> actual = target.createAutos(0);
        Assertions.assertEquals(0, actual.size());
    }

    @Test
    void createBuses_fiveCount() {
        final List<Auto> actual = target.createAutos(5);
        Assertions.assertEquals(5, actual.size());
    }

    @Test
    void saveBuses() {
        final boolean actual = target.saveAutos(List.of(createSimpleAuto()));
        Assertions.assertTrue(actual);
    }

    @Test
    void printAll() {
        List<Auto> autos = List.of(createSimpleAuto(), createSimpleAuto());
        Mockito.when(autoRepository.getAll()).thenReturn(autos);
        target.printAll();
    }

    @Test
    void deleteProductByIndex_oneFromOne() {
        final List<Auto> actual = target.createAutos(1);
        int index = 0;
        Assertions.assertNotNull(target.deleteProductByIndex(actual, index));
    }

    @Test
    void deleteProductByIndex_success() {
        index = 4;
        final List<Auto> actual = target.createAutos(5);
        final Auto auto1 = actual.get(index);
        Mockito.when(autoRepository.delete(auto1.getId())).thenReturn(true);
        Assertions.assertTrue(target.deleteProductByIndex(actual, index));
    }

    @Test
    void deleteProductByIndex_failIndex() {
        index = 5;
        final List<Auto> actual = target.createAutos(5);
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> target.deleteProductByIndex(actual, index));
    }

    @Test
    void changeProductByIndex() {
        index = 0;
        final List<Auto> actual = target.createAutos(5);
        Assertions.assertEquals(11, target.changeProductByIndex(actual, 3, "123"));
    }

    @Test
    void findOrCreateDefaultAuto() {
        Mockito.when(autoRepository.findById(anyString())).thenReturn(Optional.of(createSimpleAuto()));
        Assertions.assertTrue(target.findOrCreateDefaultAuto(anyString()));
    }

    @Test
    void findAndCreateDefaultAuto() {
        Mockito.when(autoRepository.findById(anyString())).thenReturn(Optional.of(createSimpleAuto()));
        Assertions.assertTrue(target.findOrCreateDefaultAuto(anyString()));
    }

    @Test
    void findOrThrowException_success() {
        Mockito.when(autoRepository.findById(anyString())).thenReturn(Optional.of(createSimpleAuto()));
        Assertions.assertThrows(IllegalArgumentException.class, () -> target.findOrThrowException(anyString()));
    }

    @Test
    void findOrThrowException_fail() {
        Mockito.when(autoRepository.findById(anyString())).thenReturn(Optional.empty());
        Assertions.assertThrows(IllegalArgumentException.class, () -> target.findOrThrowException(anyString()));
    }

    @Test
    void filterByManufacturerById_success() {
        Mockito.when(autoRepository.findById(anyString())).thenReturn(Optional.of(createSimpleAuto()));
        Assertions.assertFalse(target.filterByManufacturerById(anyString(), AutoManufacturer.KIA));
    }

    @Test
    void filterByManufacturerById_fail() {
        Mockito.when(autoRepository.findById(anyString())).thenReturn(Optional.empty());
        Assertions.assertFalse(target.filterByManufacturerById(anyString(), AutoManufacturer.OPEL));
    }
}*/
