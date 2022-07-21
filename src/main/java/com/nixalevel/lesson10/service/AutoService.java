package com.nixalevel.lesson10.service;

import com.nixalevel.lesson10.model.Auto;
import com.nixalevel.lesson10.model.AutoManufacturer;
import com.nixalevel.lesson10.repository.AutoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;

public class AutoService {
    private static final Logger LOGGER = LoggerFactory.getLogger(AutoService.class);
    private static final Random RANDOM = new Random();
    private static final AutoRepository AUTO_REPOSITORY = new AutoRepository();

    public List<Auto> createAutos(int count) {
        final List<Auto> result = new LinkedList<>();
        for (int i = 0; i < count; i++) {
            final Auto auto = new Auto(
                    "Model-" + RANDOM.nextInt(1000),
                    getRandomManufacturer(),
                    BigDecimal.valueOf(RANDOM.nextDouble(1000.0)),
                    "Model-" + RANDOM.nextInt(1000)
            );
            result.add(auto);
            LOGGER.info("Created auto {}", auto.getId());
        }
        return result;
    }

    private AutoManufacturer getRandomManufacturer() {
        final AutoManufacturer[] values = AutoManufacturer.values();
        final int index = RANDOM.nextInt(values.length);
        return values[index];
    }

    public boolean saveAutos(List<Auto> autos) {
        AUTO_REPOSITORY.create(autos);
        return true;
    }

    public void printAll() {
        for (Auto auto : AUTO_REPOSITORY.getAll()) {
            LOGGER.info(auto.toString());
        }
    }

    public boolean deleteProductByIndex(List<Auto> autos, int index) {
        final Auto auto = autos.get(index);
        AUTO_REPOSITORY.delete(auto.getId());
        LOGGER.info("\nAuto {} removed from the list.", auto);
        return true;
    }

    public boolean changeProductByIndex(List<Auto> autos, int index, String bodyType) {
        final Auto auto = autos.get(index);
        AUTO_REPOSITORY.getById(auto.getId()).setBodyType(bodyType);
        LOGGER.info("\nAuto {} has been changed.", auto);
        return true;
    }

    public boolean findOrCreateDefaultAuto(String id) {
        final Auto auto = AUTO_REPOSITORY.findById(id).orElse(createDefaultAuto());
        LOGGER.info(auto.toString());
        return true;
    }

    public boolean findAndCreateDefaultAuto(String id) {
        final Optional<Auto> autoOptional = AUTO_REPOSITORY.findById(id).or(() -> Optional.of(createDefaultAuto()));
        autoOptional.ifPresent(auto -> AUTO_REPOSITORY.delete(auto.getId()));
        autoOptional.orElseGet(() -> {
            LOGGER.info("Auto with id " + "\"" + id + "\"" + " not found");
            return createDefaultAuto();
        });
        LOGGER.info(autoOptional.get().toString());
        return true;
    }

    public boolean findOrThrowException(String id) {
        try {
            final Auto auto = AUTO_REPOSITORY.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("Auto with id " + "\"" + id + "\"" + " not found"));
            LOGGER.info(auto.toString());
        } catch (IllegalArgumentException exception) {
            LOGGER.error(exception.getMessage());
        }
        return true;
    }

    public boolean filterByManufacturerById(String id, AutoManufacturer autoManufacturer) {
        AtomicBoolean isFind = new AtomicBoolean(false);
        AUTO_REPOSITORY.findById(id)
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

    public Auto createDefaultAuto() {
        return new Auto(
                "Model-Default",
                getRandomManufacturer(),
                BigDecimal.ZERO,
                "Model-Default"
        );
    }
}