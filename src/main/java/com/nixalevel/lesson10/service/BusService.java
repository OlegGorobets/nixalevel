package com.nixalevel.lesson10.service;

import com.nixalevel.lesson10.model.Bus;
import com.nixalevel.lesson10.model.BusManufacturer;
import com.nixalevel.lesson10.repository.BusRepository;
import com.nixalevel.lesson10.repository.CrudRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;

public class BusService extends VehicleService<Bus> {
    private static final Logger LOGGER = LoggerFactory.getLogger(BusService.class);
    private static final Random RANDOM = new Random();

    private static BusService instance;

    public BusService(CrudRepository<Bus> repository) {
        super(repository);
    }

    public static BusService getInstance() {
        if (instance == null) {
            instance = new BusService(BusRepository.getInstance());
        }
        return instance;
    }

    @Override
    protected Bus create() {
        return new Bus(
                "Model-" + RANDOM.nextInt(1000),
                getRandomManufacturer(),
                BigDecimal.valueOf(RANDOM.nextDouble(1000.0)),
                RANDOM.nextInt(20, 40)
        );
    }

    private BusManufacturer getRandomManufacturer() {
        final BusManufacturer[] values = BusManufacturer.values();
        final int index = RANDOM.nextInt(values.length);
        return values[index];
    }

    public boolean saveBuses(List<Bus> buses) {
        repository.create(buses);
        return true;
    }

    public void printAll() {
        for (Bus bus : repository.getAll()) {
            LOGGER.info(bus.toString());
        }
    }

    public boolean changeProductByIndex(List<Bus> buses, int index, int numberOfSeats) {
        final Bus bus = buses.get(index);
        repository.getById(bus.getId()).setNumberOfSeats(numberOfSeats);
        LOGGER.info("\nBus {} has been changed.", bus);
        return true;
    }

    public boolean change(int index, int numberOfSeats) {
        List<Bus> vehicleList = repository.getAll();
        vehicleList.get(index).setNumberOfSeats(numberOfSeats);
        repository.update(vehicleList.get(index));
        LOGGER.info("Changed.");
        return true;
    }

    public boolean findOrCreateDefaultBus(String id) {
        final Bus bus = repository.findById(id).orElse(createDefaultBus());
        LOGGER.info(bus.toString());
        return true;

    }

    public boolean findAndCreateDefaultBus(String id) {
        final Optional<Bus> busOptional = repository.findById(id).or(() -> Optional.of(createDefaultBus()));
        busOptional.ifPresent(bus -> repository.delete(bus.getId()));
        busOptional.orElseGet(() -> {
            LOGGER.info("Bus with id " + "\"" + id + "\"" + " not found");
            return createDefaultBus();
        });
        LOGGER.info(busOptional.get().toString());
        return true;
    }

    public boolean findOrThrowException(String id) {
        try {
            final Bus bus = repository.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("Bus with id " + "\"" + id + "\"" + " not found"));
            LOGGER.info(bus.toString());
        } catch (IllegalArgumentException exception) {
            LOGGER.error(exception.getMessage());
        }
        return true;
    }

    public boolean filterByManufacturerById(String id, BusManufacturer busManufacturer) {
        AtomicBoolean isFind = new AtomicBoolean(false);
        repository.findById(id)
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
