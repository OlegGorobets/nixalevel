package com.nixalevel.lesson10.service;

import com.nixalevel.lesson10.model.Bus;
import com.nixalevel.lesson10.model.BusManufacturer;
import com.nixalevel.lesson10.repository.BusRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;

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

    public boolean saveBuses(List<Bus> buses) {
        BUS_REPOSITORY.create(buses);
        return true;
    }

    public void printAll() {
        for (Bus bus : BUS_REPOSITORY.getAll()) {
            LOGGER.info(bus.toString());
        }
    }

    public boolean deleteProductByIndex(List<Bus> buses, int index) {
        final Bus bus = buses.get(index);
        BUS_REPOSITORY.delete(bus.getId());
        LOGGER.info("\nBus {} removed from the list.", bus);
        return true;
    }

    public boolean changeProductByIndex(List<Bus> buses, int index, int numberOfSeats) {
        final Bus bus = buses.get(index);
        BUS_REPOSITORY.getById(bus.getId()).setNumberOfSeats(numberOfSeats);
        LOGGER.info("\nBus {} has been changed.", bus);
        return true;
    }

    public boolean findOrCreateDefaultBus(String id) {
        final Bus bus = BUS_REPOSITORY.findById(id).orElse(createDefaultBus());
        LOGGER.info(bus.toString());
        return true;

    }

    public boolean findAndCreateDefaultBus(String id) {
        final Optional<Bus> busOptional = BUS_REPOSITORY.findById(id).or(() -> Optional.of(createDefaultBus()));
        busOptional.ifPresent(bus -> BUS_REPOSITORY.delete(bus.getId()));
        busOptional.orElseGet(() -> {
            LOGGER.info("Bus with id " + "\"" + id + "\"" + " not found");
            return createDefaultBus();
        });
        LOGGER.info(busOptional.get().toString());
        return true;
    }

    public boolean findOrThrowException(String id) {
        try {
            final Bus bus = BUS_REPOSITORY.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("Bus with id " + "\"" + id + "\"" + " not found"));
            LOGGER.info(bus.toString());
        } catch (IllegalArgumentException exception) {
            LOGGER.error(exception.getMessage());
        }
        return true;
    }

    public boolean filterByManufacturerById(String id, BusManufacturer busManufacturer) {
        AtomicBoolean isFind = new AtomicBoolean(false);
        BUS_REPOSITORY.findById(id)
                .map(Bus::getBusManufacturer)
                .filter(manufacturer -> manufacturer.equals(busManufacturer))
                .ifPresentOrElse(
                        manufacturer -> isFind.set(true),
                        () -> isFind.set(false)
                );
        if (isFind.get()) {
            LOGGER.info(busManufacturer.toString());
        } else {
            LOGGER.info("Bus with manufacturer " + "\"" + busManufacturer + "\"" + " by " +
                    "\"" + id + "\"" + " not found");
        }
        return isFind.get();
    }

    public Bus createDefaultBus() {
        return new Bus(
                "Model-Default",
                getRandomManufacturer(),
                BigDecimal.ZERO,
                0
        );
    }
}
