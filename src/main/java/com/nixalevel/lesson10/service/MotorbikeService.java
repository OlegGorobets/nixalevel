package com.nixalevel.lesson10.service;

import com.nixalevel.lesson10.model.Motorbike;
import com.nixalevel.lesson10.model.MotorbikeManufacturer;
import com.nixalevel.lesson10.repository.MotorbikeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class MotorbikeService {
    private static final Logger LOGGER = LoggerFactory.getLogger(MotorbikeService.class);
    private static final Random RANDOM = new Random();
    private static final MotorbikeRepository MOTORBIKE_REPOSITORY = new MotorbikeRepository();

    public List<Motorbike> createMotorbikes(int count) {
        List<Motorbike> result = new LinkedList<>();
        for (int i = 0; i < count; i++) {
            final Motorbike motorbike = new Motorbike(
                    "Model-" + RANDOM.nextInt(1000),
                    getRandomManufacturer(),
                    BigDecimal.valueOf(RANDOM.nextDouble(1000.0)),
                    RANDOM.nextInt(200, 300)
            );
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

    public void saveMotorbikes(List<Motorbike> motorbikes) {
        MOTORBIKE_REPOSITORY.create(motorbikes);
    }

    public void printAll() {
        for (Motorbike motorbike : MOTORBIKE_REPOSITORY.getAll()) {
            LOGGER.info(motorbike.toString());
        }
    }

    public void deleteProductByIndex(List<Motorbike> motorbikes, int index) {
        MOTORBIKE_REPOSITORY.delete(motorbikes.get(index).getId());
        LOGGER.info("\nMotorbike " + motorbikes.get(index) + " removed from the list.");
    }

    public void changeProductByIndex(List<Motorbike> motorbikes, int index, int maxSpeed) {
        MOTORBIKE_REPOSITORY.getById(motorbikes.get(index).getId()).setMaxSpeed(maxSpeed);
        LOGGER.info("\nMotorbike " + motorbikes.get(index) + " has been changed.");
    }
}
