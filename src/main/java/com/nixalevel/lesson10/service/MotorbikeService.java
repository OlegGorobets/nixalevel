package com.nixalevel.lesson10.service;

import com.nixalevel.lesson10.model.Motorbike;
import com.nixalevel.lesson10.model.MotorbikeManufacturer;
import com.nixalevel.lesson10.repository.CrudRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;

public class MotorbikeService extends VehicleService<Motorbike> {
    private static final Logger LOGGER = LoggerFactory.getLogger(MotorbikeService.class);
    private static final Random RANDOM = new Random();

    public MotorbikeService(CrudRepository<Motorbike> repository) {
        super(repository);
    }

    @Override
    protected Motorbike create() {
        return new Motorbike(
                "Model-" + RANDOM.nextInt(1000),
                getRandomManufacturer(),
                BigDecimal.valueOf(RANDOM.nextDouble(1000.0)),
                RANDOM.nextInt(200, 300)
        );
    }

    public List<Motorbike> createMotorbikes(int count) {
        List<Motorbike> result = new LinkedList<>();
        for (int i = 0; i < count; i++) {
            final Motorbike motorbike = create();
            result.add(motorbike);
            LOGGER.debug("Created motorbike {}", motorbike.getId());
        }
        return result;
    }

    private MotorbikeManufacturer getRandomManufacturer() {
        final MotorbikeManufacturer[] values = MotorbikeManufacturer.values();
        final int index = RANDOM.nextInt(values.length);
        return values[index];
    }

    public boolean saveMotorbikes(List<Motorbike> motorbikes) {
        repository.create(motorbikes);
        return true;
    }

    public void printAll() {
        for (Motorbike motorbike : repository.getAll()) {
            LOGGER.info(motorbike.toString());
        }
    }

    public boolean changeProductByIndex(List<Motorbike> motorbikes, int index, int maxSpeed) {
        final Motorbike motorbike = motorbikes.get(index);
        repository.getById(motorbike.getId()).setMaxSpeed(maxSpeed);
        LOGGER.info("\nMotorbike {} has been changed.", motorbike);
        return true;
    }

    public boolean findOrCreateDefaultMotorbike(String id) {
        final Motorbike motorbike = repository.findById(id).orElse(createDefaultMotorbike());
        LOGGER.info(motorbike.toString());
        return true;

    }

    public boolean findAndCreateDefaultMotorbike(String id) {
        final Optional<Motorbike> motorbikeOptional = repository.findById(id).or(() ->
                Optional.of(createDefaultMotorbike()));
        motorbikeOptional.ifPresent(motorbike -> repository.delete(motorbike.getId()));
        motorbikeOptional.orElseGet(() -> {
            LOGGER.info("Motorbike with id " + "\"" + id + "\"" + " not found");
            return createDefaultMotorbike();
        });
        LOGGER.info(motorbikeOptional.get().toString());
        return true;
    }

    public boolean findOrThrowException(String id) {
        try {
            final Motorbike motorbike = repository.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("Motorbike with id " + "\"" + id + "\"" + " not found"));
            LOGGER.info(motorbike.toString());
        } catch (IllegalArgumentException exception) {
            LOGGER.error(exception.getMessage());
        }
        return true;
    }

    public boolean filterByManufacturerById(String id, MotorbikeManufacturer motorbikeManufacturer) {
        AtomicBoolean isFind = new AtomicBoolean(false);
        repository.findById(id)
                .map(Motorbike::getMotorbikeManufacturer)
                .filter(manufacturer -> manufacturer.equals(motorbikeManufacturer))
                .ifPresentOrElse(
                        manufacturer -> isFind.set(true),
                        () -> isFind.set(false)
                );
        if (isFind.get()) {
            LOGGER.info(motorbikeManufacturer.toString());
        } else {
            LOGGER.info("Motorbike with manufacturer " + "\"" + motorbikeManufacturer + "\"" + " by " +
                    "\"" + id + "\"" + " not found");
        }
        return isFind.get();
    }

    public Motorbike createDefaultMotorbike() {
        return new Motorbike(
                "Model-Default",
                getRandomManufacturer(),
                BigDecimal.ZERO,
                100
        );
    }
}
