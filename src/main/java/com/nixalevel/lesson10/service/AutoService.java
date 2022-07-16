package com.nixalevel.lesson10.service;

import com.nixalevel.lesson10.model.Auto;
import com.nixalevel.lesson10.model.AutoManufacturer;
import com.nixalevel.lesson10.repository.AutoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class AutoService {
    private static final Logger LOGGER = LoggerFactory.getLogger(AutoService.class);
    private static final Random RANDOM = new Random();
    private static final AutoRepository AUTO_REPOSITORY = new AutoRepository();

    public List<Auto> createAutos(int count) {
        List<Auto> result = new LinkedList<>();
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

    public void saveAutos(List<Auto> autos) {
        AUTO_REPOSITORY.create(autos);
    }

    public void printAll() {
        for (Auto auto : AUTO_REPOSITORY.getAll()) {
            LOGGER.info(auto.toString());
        }
    }

    public void deleteProductByIndex(List<Auto> autos, int index) {
        final Auto auto = autos.get(index);
        AUTO_REPOSITORY.delete(auto.getId());
        LOGGER.info("\nAuto {} removed from the list.", auto);
    }

    public void changeProductByIndex(List<Auto> autos, int index, String bodyType) {
        final Auto auto = autos.get(index);
        AUTO_REPOSITORY.getById(auto.getId()).setBodyType(bodyType);
        LOGGER.info("\nAuto {} has been changed.", auto);
    }
}
