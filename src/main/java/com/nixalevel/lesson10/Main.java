package com.nixalevel.lesson10;

import com.nixalevel.lesson10.model.Auto;
import com.nixalevel.lesson10.model.Bus;
import com.nixalevel.lesson10.model.Motorbike;
import com.nixalevel.lesson10.service.AutoService;
import com.nixalevel.lesson10.service.BusService;
import com.nixalevel.lesson10.service.MotorbikeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class Main {
    private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);
    private static final AutoService AUTO_SERVICE = new AutoService();
    private static final BusService BUS_SERVICE = new BusService();
    private static final MotorbikeService MOTORBIKE_SERVICE = new MotorbikeService();

    public static void main(String[] args) {
        /* Create all types of products */
        final List<Auto> autos = AUTO_SERVICE.createAutos(5);
        final List<Bus> buses = BUS_SERVICE.createBuses(5);
        final List<Motorbike> motorbikes = MOTORBIKE_SERVICE.createMotorbikes(5);

        AUTO_SERVICE.saveAutos(autos);
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

    }
}
