package com.nixalevel.lesson10;

import com.nixalevel.lesson10.model.*;
import com.nixalevel.lesson10.model.vehicle.*;
import com.nixalevel.lesson10.repository.AutoRepository;
import com.nixalevel.lesson10.repository.BusRepository;
import com.nixalevel.lesson10.repository.MotorbikeRepository;
import com.nixalevel.lesson10.service.AutoService;
import com.nixalevel.lesson10.service.BusService;
import com.nixalevel.lesson10.service.MotorbikeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.List;

public class Main {
    private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);
    private static final AutoService AUTO_SERVICE = new AutoService(new AutoRepository());
    private static final BusService BUS_SERVICE = new BusService(new BusRepository());
    private static final MotorbikeService MOTORBIKE_SERVICE = new MotorbikeService(new MotorbikeRepository());
    private static final Container<Vehicle> CONTAINER = new Container<>();

    public static void main(String[] args) {
        /* Create all types of products */
        final List<Auto> autos = AUTO_SERVICE.createVehicles(5);
        final List<Bus> buses = BUS_SERVICE.createBuses(5);
        final List<Motorbike> motorbikes = MOTORBIKE_SERVICE.createMotorbikes(5);

        AUTO_SERVICE.saveVehicles(autos);
        BUS_SERVICE.saveBuses(buses);
        MOTORBIKE_SERVICE.saveMotorbikes(motorbikes);

        /* Display in console */
        LOGGER.info("\nCreate all types of products");
        AUTO_SERVICE.printAll();
        BUS_SERVICE.printAll();
        MOTORBIKE_SERVICE.printAll();

        /* Change one specific product */
        AUTO_SERVICE.changeProductByIndex(autos, 1, "Model-Test123");
        BUS_SERVICE.changeProductByIndex(buses, 0, 10);
        MOTORBIKE_SERVICE.changeProductByIndex(motorbikes, 1, 100);

        /* Display change in console */
        LOGGER.info("\nChange one specific product");
        AUTO_SERVICE.printAll();
        BUS_SERVICE.printAll();
        MOTORBIKE_SERVICE.printAll();

        /* Delete a specific product */
        AUTO_SERVICE.deleteProductByIndex(autos, 0);
        BUS_SERVICE.deleteProductByIndex(buses, 1);
        MOTORBIKE_SERVICE.deleteProductByIndex(motorbikes, 0);

        /* Display change in console */
        LOGGER.info("\nDelete a specific product");
        AUTO_SERVICE.printAll();
        BUS_SERVICE.printAll();
        MOTORBIKE_SERVICE.printAll();

        /* Optional */
        LOGGER.info("\nOptional");
        AUTO_SERVICE.findOrCreateDefaultAuto(autos.get(0).getId());
        AUTO_SERVICE.findAndCreateDefaultAuto(autos.get(0).getId());
        AUTO_SERVICE.findOrThrowException(autos.get(0).getId());
        AUTO_SERVICE.filterByManufacturerById(autos.get(0).getId(), AutoManufacturer.KIA);

        BUS_SERVICE.findOrCreateDefaultBus(buses.get(0).getId());
        BUS_SERVICE.findAndCreateDefaultBus(buses.get(0).getId());
        BUS_SERVICE.findOrThrowException(buses.get(0).getId());
        BUS_SERVICE.filterByManufacturerById(buses.get(0).getId(), BusManufacturer.MAN);

        MOTORBIKE_SERVICE.findOrCreateDefaultMotorbike(motorbikes.get(0).getId());
        MOTORBIKE_SERVICE.findAndCreateDefaultMotorbike(motorbikes.get(0).getId());
        MOTORBIKE_SERVICE.findOrThrowException(motorbikes.get(0).getId());
        MOTORBIKE_SERVICE.filterByManufacturerById(motorbikes.get(0).getId(), MotorbikeManufacturer.KAWASAKI);

        /* Container */
        LOGGER.info("\nContainer");
        CONTAINER.add(new Auto("AutoContainer", AutoManufacturer.BMW, BigDecimal.TEN, "AutoContainer"));
        CONTAINER.add(new Bus("AutoContainer", BusManufacturer.MAN, BigDecimal.TEN, 15));
        CONTAINER.add(new Motorbike("MotorbikeContainer", MotorbikeManufacturer.YAMAHA, BigDecimal.TEN, 200));
        CONTAINER.printAll();
        CONTAINER.applyDiscount();
        CONTAINER.increasePrice();
    }
}
