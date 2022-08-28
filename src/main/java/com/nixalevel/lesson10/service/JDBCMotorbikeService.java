package com.nixalevel.lesson10.service;

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

public class JDBCMotorbikeService extends VehicleService<Motorbike> {
    private static final Logger LOGGER = LoggerFactory.getLogger(JDBCMotorbikeService.class);
    private static final Random RANDOM = new Random();

    private static JDBCMotorbikeService instance;

    public JDBCMotorbikeService(CrudRepository<Motorbike> repository) {
        super(repository);
    }

    public static JDBCMotorbikeService getInstance() {
        if (instance == null) {
            instance = new JDBCMotorbikeService(JDBCMotorbikeRepository.getInstance());
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

    public boolean removeAll() {
        repository.clear();
        return true;
    }
}
