package com.nixalevel.lesson10.service;

import com.nixalevel.lesson10.model.Auto;
import com.nixalevel.lesson10.model.AutoManufacturer;
import com.nixalevel.lesson10.repository.CrudRepository;
import com.nixalevel.lesson10.repository.JDBCAutoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;

public class AutoService extends VehicleService<Auto> {
    private static final Logger LOGGER = LoggerFactory.getLogger(AutoService.class);
    private static final Random RANDOM = new Random();

    private static AutoService instance;

    public AutoService(CrudRepository<Auto> repository) {
        super(repository);
    }

    public static AutoService getInstance() {
        if (instance == null) {
            instance = new AutoService(JDBCAutoRepository.getInstance());
        }
        return instance;
    }

    @Override
    protected Auto create() {
        List<String> list = Arrays.asList("test1", "test2");
        return new Auto(
                UUID.randomUUID().toString(),
                "Model-" + RANDOM.nextInt(1000),
                getRandomManufacturer(),
                BigDecimal.valueOf(RANDOM.nextDouble(1000.0)),
                "Model-" + RANDOM.nextInt(1000),
                list,
                RANDOM.nextInt(1, 100),
                new Date()
        );
    }

    private AutoManufacturer getRandomManufacturer() {
        final AutoManufacturer[] values = AutoManufacturer.values();
        final int index = RANDOM.nextInt(values.length);
        return values[index];
    }

    public boolean changeProductByIndex(List<Auto> autos, int index, String bodyType) {
        final Auto auto = autos.get(index);
        repository.getById(auto.getId()).setBodyType(bodyType);
        LOGGER.info("\nAuto {} has been changed.", auto);
        return true;
    }

    public boolean change(int index, String bodyType) {
        List<Auto> vehicleList = repository.getAll();
        vehicleList.get(index).setBodyType(bodyType);
        repository.update(vehicleList.get(index));
        LOGGER.info("Changed.");
        return true;
    }

    public boolean findOrCreateDefaultAuto(String id) {
        final Auto auto = repository.findById(id).orElse(createDefaultAuto());
        LOGGER.info(auto.toString());
        return true;
    }

    public boolean findAndCreateDefaultAuto(String id) {
        final Optional<Auto> autoOptional = repository.findById(id).or(() -> Optional.of(createDefaultAuto()));
        autoOptional.ifPresent(auto -> repository.delete(auto.getId()));
        autoOptional.orElseGet(() -> {
            LOGGER.info("Auto with id " + "\"" + id + "\"" + " not found");
            return createDefaultAuto();
        });
        LOGGER.info(autoOptional.get().toString());
        return true;
    }

    public boolean findOrThrowException(String id) {
        try {
            final Auto auto = repository.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("Auto with id " + "\"" + id + "\"" + " not found"));
            LOGGER.info(auto.toString());
        } catch (IllegalArgumentException exception) {
            LOGGER.error(exception.getMessage());
        }
        return true;
    }

    public boolean filterByManufacturerById(String id, AutoManufacturer autoManufacturer) {
        AtomicBoolean isFind = new AtomicBoolean(false);
        repository.findById(id)
                .map(Auto::getAutoManufacturer)
                .filter(manufacturer -> manufacturer.equals(autoManufacturer))
                .ifPresentOrElse(
                        manufacturer -> isFind.set(true),
                        () -> isFind.set(false)
                );
        if (isFind.get()) {
            LOGGER.info(autoManufacturer.toString());
        } else {
            LOGGER.info("Auto with manufacturer " + "\"" + autoManufacturer + "\"" + " by " +
                    "\"" + id + "\"" + " not found");
        }
        return isFind.get();
    }

    public boolean removeAll() {
        repository.clear();
        return true;
    }

    public Auto createDefaultAuto() {
        return new Auto(
                "Model-Default",
                getRandomManufacturer(),
                BigDecimal.ZERO,
                "Model-Default"
        );
    }
}