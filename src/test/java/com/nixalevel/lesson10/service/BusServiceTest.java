package com.nixalevel.lesson10.service;

import com.nixalevel.lesson10.model.Bus;
import com.nixalevel.lesson10.model.BusManufacturer;
import com.nixalevel.lesson10.repository.BusRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyString;

class BusServiceTest {

    private BusService target;
    private Bus bus;
    private BusRepository busRepository;

    private int index;

    @BeforeEach
    void setUp() {
        busRepository = Mockito.mock(BusRepository.class);
        target = new BusService();
        bus = createSimpleBus();
    }

    private Bus createSimpleBus() {
        return new Bus("Model", BusManufacturer.MERCEDES, BigDecimal.ZERO, 11);
    }

    @Test
    void createBuses_negativeCount() {
        final List<Bus> actual = target.createBuses(-1);
        Assertions.assertEquals(0, actual.size());
    }

    @Test
    void createBuses_zeroCount() {
        final List<Bus> actual = target.createBuses(0);
        Assertions.assertEquals(0, actual.size());
    }

    @Test
    void createBuses_fiveCount() {
        final List<Bus> actual = target.createBuses(5);
        Assertions.assertEquals(5, actual.size());
    }

    @Test
    void saveBuses() {
        final boolean actual = target.saveBuses(List.of(bus));
        Assertions.assertTrue(actual);
    }

    @Test
    void printAll() {
        List<Bus> buses = List.of(createSimpleBus(), createSimpleBus());
        Mockito.when(busRepository.getAll()).thenReturn(buses);
        target.printAll();
    }

    @Test
    void deleteProductByIndex_oneFromOne() {
        final List<Bus> actual = target.createBuses(1);
        int index = 0;
        Assertions.assertNotNull(target.deleteProductByIndex(actual, index));
    }

    @Test
    void deleteProductByIndex_success() {
        index = 4;
        final List<Bus> actual = target.createBuses(5);
        final Bus bus1 = actual.get(index);
        Mockito.when(busRepository.delete(bus1.getId())).thenReturn(true);
        Assertions.assertTrue(target.deleteProductByIndex(actual, index));
    }

    @Test
    void deleteProductByIndex_failIndex() {
        index = 5;
        final List<Bus> actual = target.createBuses(5);
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> target.deleteProductByIndex(actual, index));
    }

    @Test
    void changeProductByIndex() {
        index = 0;
        final List<Bus> actual = target.createBuses(5);
        Assertions.assertEquals(11, target.changeProductByIndex(actual, 3, 11));
    }

    @Test
    void findOrCreateDefaultAuto() {
        Mockito.when(busRepository.findById(anyString())).thenReturn(Optional.of(createSimpleBus()));
        Assertions.assertTrue(target.findOrCreateDefaultBus(anyString()));
    }

    @Test
    void findAndCreateDefaultAuto() {
        Mockito.when(busRepository.findById(anyString())).thenReturn(Optional.of(createSimpleBus()));
        Assertions.assertTrue(target.findOrCreateDefaultBus(anyString()));
    }

    @Test
    void findOrThrowException_success() {
        Mockito.when(busRepository.findById(anyString())).thenReturn(Optional.of(createSimpleBus()));
        Assertions.assertThrows(IllegalArgumentException.class, () -> target.findOrThrowException(anyString()));
    }

    @Test
    void findOrThrowException_fail() {
        Mockito.when(busRepository.findById(anyString())).thenReturn(Optional.empty());
        Assertions.assertThrows(IllegalArgumentException.class, () -> target.findOrThrowException(anyString()));
    }

    @Test
    void filterByManufacturerById_success() {
        Mockito.when(busRepository.findById(anyString())).thenReturn(Optional.of(createSimpleBus()));
        Assertions.assertFalse(target.filterByManufacturerById(anyString(), BusManufacturer.MAN));
    }

    @Test
    void filterByManufacturerById_fail() {
        Mockito.when(busRepository.findById(anyString())).thenReturn(Optional.empty());
        Assertions.assertFalse(target.filterByManufacturerById(anyString(), BusManufacturer.ICARUS));
    }
}