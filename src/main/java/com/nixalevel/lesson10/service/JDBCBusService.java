package com.nixalevel.lesson10.service;

import com.nixalevel.lesson10.model.Bus;
import com.nixalevel.lesson10.model.BusManufacturer;
import com.nixalevel.lesson10.repository.BusRepository;
import com.nixalevel.lesson10.repository.CrudRepository;
import com.nixalevel.lesson10.repository.JDBCBusRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;

public class JDBCBusService extends VehicleService<Bus> {
    private static final Logger LOGGER = LoggerFactory.getLogger(JDBCBusService.class);
    private static final Random RANDOM = new Random();

    private static JDBCBusService instance;

    public JDBCBusService(CrudRepository<Bus> repository) {
        super(repository);
    }

    public static JDBCBusService getInstance() {
        if (instance == null) {
            instance = new JDBCBusService(JDBCBusRepository.getInstance());
        }
        return instance;
    }

    @Override
    protected Bus create() {
        List<String> list = Arrays.asList("test1", "test2");
        return new Bus(
                UUID.randomUUID().toString(),
                "Model-" + RANDOM.nextInt(1000),
                getRandomManufacturer(),
                BigDecimal.valueOf(RANDOM.nextDouble(1000.0)),
                RANDOM.nextInt(1, 50),
                list,
                RANDOM.nextInt(1, 100),
                new Date()
        );
    }

    private BusManufacturer getRandomManufacturer() {
        final BusManufacturer[] values = BusManufacturer.values();
        final int index = RANDOM.nextInt(values.length);
        return values[index];
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

    public boolean removeAll() {
        repository.clear();
        return true;
    }
}
