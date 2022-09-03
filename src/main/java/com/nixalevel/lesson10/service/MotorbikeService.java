package com.nixalevel.lesson10.service;

import com.nixalevel.lesson10.annotation.Autowired;
import com.nixalevel.lesson10.annotation.Singleton;
import com.nixalevel.lesson10.model.Motorbike;
import com.nixalevel.lesson10.model.MotorbikeManufacturer;
import com.nixalevel.lesson10.repository.CrudRepository;
import com.nixalevel.lesson10.repository.JDBCMotorbikeRepository;
import com.nixalevel.lesson10.repository.MotorbikeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;

@Singleton
public class MotorbikeService extends VehicleService<Motorbike> {
    private static final Logger LOGGER = LoggerFactory.getLogger(MotorbikeService.class);
    private static final Random RANDOM = new Random();

    private static MotorbikeService instance;

    @Autowired
    public MotorbikeService(CrudRepository<Motorbike> repository) {
        super(repository);
    }

    public static MotorbikeService getInstance() {
        if (instance == null) {
            instance = new MotorbikeService(JDBCMotorbikeRepository.getInstance());
        }
        return instance;
    }

    @Override
    protected Motorbike create() {
        List<String> list = Arrays.asList("test1", "test2");
        return new Motorbike(
                UUID.randomUUID().toString(),
                "Model-" + RANDOM.nextInt(1000),
                getRandomManufacturer(),
                BigDecimal.valueOf(RANDOM.nextDouble(1000.0)),
                RANDOM.nextInt(200, 300),
                list,
                RANDOM.nextInt(1, 100),
                new Date()
        );
    }

    private MotorbikeManufacturer getRandomManufacturer() {
        final MotorbikeManufacturer[] values = MotorbikeManufacturer.values();
        final int index = RANDOM.nextInt(values.length);
        return values[index];
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

    public boolean change(int index, int maxSpeed) {
        List<Motorbike> vehicleList = repository.getAll();
        vehicleList.get(index).setMaxSpeed(maxSpeed);
        repository.update(vehicleList.get(index));
        LOGGER.info("Changed.");
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

    public boolean removeAll() {
        repository.clear();
        return true;
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
