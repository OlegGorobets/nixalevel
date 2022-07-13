package com.nixalevel.lesson10.service;

import com.nixalevel.lesson10.model.Auto;
import com.nixalevel.lesson10.model.Bus;
import com.nixalevel.lesson10.model.BusManufacturer;
import com.nixalevel.lesson10.repository.BusRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class BusService {
    private static final Logger LOGGER = LoggerFactory.getLogger(BusService.class);
    private static final Random RANDOM = new Random();
    private static final BusRepository BUS_REPOSITORY = new BusRepository();

    public List<Bus> createBuses(int count) {
        List<Bus> result = new LinkedList<>();
        for (int i = 0; i < count; i++) {
            final Bus bus = new Bus(
                    "Model-" + RANDOM.nextInt(1000),
                    getRandomManufacturer(),
                    BigDecimal.valueOf(RANDOM.nextDouble(1000.0)),
                    RANDOM.nextInt(20, 40)
            );
            result.add(bus);
            LOGGER.debug("Created bus {}", bus.getId());
        }
        return result;
    }

    private BusManufacturer getRandomManufacturer() {
        final BusManufacturer[] values = BusManufacturer.values();
        final int index = RANDOM.nextInt(values.length);
        return values[index];
    }

    public void saveBuses(List<Bus> buses) {
        BUS_REPOSITORY.create(buses);
    }

    public void printAll() {
        for (Bus bus : BUS_REPOSITORY.getAll()) {
            LOGGER.info(bus.toString());
        }
    }

    public void deleteProductByIndex(List<Bus> buses, int index) {
        final Bus bus = buses.get(index);
        BUS_REPOSITORY.delete(bus.getId());
        LOGGER.info("\nBus {} removed from the list.", bus);
    }

    public void changeProductByIndex(List<Bus> buses, int index, int numberOfSeats) {
        final Bus bus = buses.get(index);
        BUS_REPOSITORY.getById(bus.getId()).setNumberOfSeats(numberOfSeats);
        LOGGER.info("\nBus {} has been changed.", bus);
    }
}
