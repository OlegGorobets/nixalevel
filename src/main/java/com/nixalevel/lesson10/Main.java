package com.nixalevel.lesson10;

import com.nixalevel.lesson10.model.*;
import com.nixalevel.lesson10.model.vehicle.*;
import com.nixalevel.lesson10.repository.AutoRepository;
import com.nixalevel.lesson10.repository.BusRepository;
import com.nixalevel.lesson10.repository.MotorbikeRepository;
import com.nixalevel.lesson10.service.AutoService;
import com.nixalevel.lesson10.service.BusService;
import com.nixalevel.lesson10.service.MotorbikeService;
import com.nixalevel.lesson10.service.VehicleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.List;

public class Main {
    private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);
    private static final AutoService AUTO_SERVICE = new AutoService(new AutoRepository());
    private static final BusService BUS_SERVICE = new BusService(new BusRepository());
    private static final MotorbikeService MOTORBIKE_SERVICE = new MotorbikeService(new MotorbikeRepository());
    private static final VehicleService VEHICLE_SERVICE = new AutoService(new AutoRepository());
    private static final Container<Vehicle> CONTAINER = new Container<>();

    private static final Garage<Vehicle> GARAGE = new Garage<>();

    public static void main(String[] args) {
        /* Create all types of products */
        final List<Auto> autos = AUTO_SERVICE.createVehicles(5);
        final List<Bus> buses = BUS_SERVICE.createBuses(5);
        final List<Motorbike> motorbikes = MOTORBIKE_SERVICE.createMotorbikes(5);
        final List<Vehicle> vehicles = VEHICLE_SERVICE.createVehicles(5);

        Auto auto = new Auto("AutoContainer", AutoManufacturer.BMW, BigDecimal.TEN, "AutoContainer");
        Auto auto1 = new Auto("AutoContainer", AutoManufacturer.BMW, BigDecimal.TEN, "AutoContainer");
        Bus bus = new Bus("AutoContainer", BusManufacturer.MAN, BigDecimal.TEN, 15);
        Motorbike motorbike = new Motorbike("MotorbikeContainer", MotorbikeManufacturer.YAMAHA, BigDecimal.TEN, 200);

        /*AUTO_SERVICE.saveVehicles(autos);
        BUS_SERVICE.saveBuses(buses);
        MOTORBIKE_SERVICE.saveMotorbikes(motorbikes);*/

        /* Display in console */
        /*LOGGER.info("\nCreate all types of products");
        AUTO_SERVICE.printAll();
        BUS_SERVICE.printAll();
        MOTORBIKE_SERVICE.printAll();*/

        /* Change one specific product */
        /*AUTO_SERVICE.changeProductByIndex(autos, 1, "Model-Test123");
        BUS_SERVICE.changeProductByIndex(buses, 0, 10);
        MOTORBIKE_SERVICE.changeProductByIndex(motorbikes, 1, 100);*/

        /* Display change in console */
        /*LOGGER.info("\nChange one specific product");
        AUTO_SERVICE.printAll();
        BUS_SERVICE.printAll();
        MOTORBIKE_SERVICE.printAll();*/

        /* Delete a specific product */
        /*AUTO_SERVICE.deleteProductByIndex(autos, 0);
        BUS_SERVICE.deleteProductByIndex(buses, 1);
        MOTORBIKE_SERVICE.deleteProductByIndex(motorbikes, 0);*/

        /* Display change in console */
        /*LOGGER.info("\nDelete a specific product");
        AUTO_SERVICE.printAll();
        BUS_SERVICE.printAll();
        MOTORBIKE_SERVICE.printAll();*/

        /* Optional */
        /*LOGGER.info("\nOptional");
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
        MOTORBIKE_SERVICE.filterByManufacturerById(motorbikes.get(0).getId(), MotorbikeManufacturer.KAWASAKI);*/

        /* Container */
        /*LOGGER.info("\nContainer");
        CONTAINER.add(new Auto("AutoContainer", AutoManufacturer.BMW, BigDecimal.TEN, "AutoContainer"));
        CONTAINER.add(new Bus("AutoContainer", BusManufacturer.MAN, BigDecimal.TEN, 15));
        CONTAINER.add(new Motorbike("MotorbikeContainer", MotorbikeManufacturer.YAMAHA, BigDecimal.TEN, 200));
        CONTAINER.printAll();
        CONTAINER.applyDiscount();
        CONTAINER.increasePrice();*/

        /* Garage */
        LOGGER.info("\nGarage");
        GARAGE.add(auto);
        GARAGE.add(bus);
        GARAGE.add(motorbike);
        LOGGER.info(GARAGE.printAll());
        GARAGE.search(GARAGE.getRestyling(auto));
        LOGGER.info(GARAGE.search(GARAGE.getRestyling(auto)));
        LOGGER.info(GARAGE.getFirst());
        LOGGER.info(GARAGE.getLast());
        LOGGER.info(GARAGE.set(GARAGE.getRestyling(motorbike), new Auto("TEST", AutoManufacturer.BMW, BigDecimal.ZERO,"qwer")));
        LOGGER.info(GARAGE.getRestylingCount());
        LOGGER.info(GARAGE.printAll());
        LOGGER.info("\n");
        StringBuilder stringBuilder = new StringBuilder();
        for (Vehicle vehicle : GARAGE) {
            stringBuilder.append(vehicle.toString()).append("\n");
        }
        LOGGER.info(GARAGE.printAll());
        LOGGER.info(GARAGE.remove(GARAGE.getRestyling(auto)));
        LOGGER.info(GARAGE.printAll());

        /* Comparator */
        LOGGER.info("\nComparator");
        vehicles.addAll(MOTORBIKE_SERVICE.createMotorbikes(5));
        for (Vehicle vehicle : vehicles) {
            LOGGER.info(vehicle.toString());
        }
        LOGGER.info("\n");
        vehicles.sort(new Vehicle.SortByPrice());
        for (Vehicle vehicle : vehicles) {
            LOGGER.info(vehicle.toString());
        }
        LOGGER.info("\n");
        vehicles.addAll(autos);
        vehicles.addAll(buses);
        vehicles.addAll(motorbikes);
        for (Vehicle vehicle : vehicles) {
            LOGGER.info(vehicle.toString());
        }
        LOGGER.info("\n");
        vehicles.sort(new Vehicle.SortByPrice());
        for (Vehicle vehicle : vehicles) {
            LOGGER.info(vehicle.toString());
        }
        LOGGER.info("\n");
        vehicles.sort(new Vehicle.SortByName());
        for (Vehicle vehicle : vehicles) {
            LOGGER.info(vehicle.toString());
        }
        LOGGER.info("\n");
        vehicles.sort(new Vehicle.SortByQuantity());
        for (Vehicle vehicle : vehicles) {
            LOGGER.info(vehicle.toString());
        }
    }
}
