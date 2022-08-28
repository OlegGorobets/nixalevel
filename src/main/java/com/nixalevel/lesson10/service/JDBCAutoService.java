package com.nixalevel.lesson10.service;

import com.nixalevel.lesson10.model.Auto;
import com.nixalevel.lesson10.model.AutoManufacturer;
import com.nixalevel.lesson10.repository.CrudRepository;
import com.nixalevel.lesson10.repository.JDBCAutoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.*;

public class JDBCAutoService extends VehicleService<Auto> {
    private static final Logger LOGGER = LoggerFactory.getLogger(JDBCAutoService.class);
    private static final Random RANDOM = new Random();

    private static JDBCAutoService instance;

    public JDBCAutoService(CrudRepository<Auto> repository) {
        super(repository);
    }

    public static JDBCAutoService getInstance() {
        if (instance == null) {
            instance = new JDBCAutoService(JDBCAutoRepository.getInstance());
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

    public boolean removeAll() {
        repository.clear();
        return true;
    }
}