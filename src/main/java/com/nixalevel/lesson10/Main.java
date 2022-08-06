package com.nixalevel.lesson10;

import com.nixalevel.lesson10.model.*;
import com.nixalevel.lesson10.repository.AutoRepository;
import com.nixalevel.lesson10.repository.BusRepository;
import com.nixalevel.lesson10.repository.MotorbikeRepository;
import com.nixalevel.lesson10.service.AutoService;
import com.nixalevel.lesson10.service.BusService;
import com.nixalevel.lesson10.service.MotorbikeService;
import com.nixalevel.lesson10.service.VehicleService;
import com.nixalevel.lesson10.utility.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.text.ParseException;
import java.util.*;

public class Main {
    private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);
    private static final AutoService AUTO_SERVICE = new AutoService(new AutoRepository());
    private static final BusService BUS_SERVICE = new BusService(new BusRepository());
    private static final MotorbikeService MOTORBIKE_SERVICE = new MotorbikeService(new MotorbikeRepository());
    private static final VehicleService VEHICLE_SERVICE = new AutoService(new AutoRepository());
    private static final Container<Vehicle> CONTAINER = new Container<>();

    private static final Garage<Vehicle> GARAGE = new Garage<>();

    public static void main(String[] args) throws ParseException {
        /* Create all types of products */
        /*final List<Auto> autos = AUTO_SERVICE.createVehicles(5);
        final List<Bus> buses = BUS_SERVICE.createVehicles(5);
        final List<Motorbike> motorbikes = MOTORBIKE_SERVICE.createVehicles(5);
        final List<Vehicle> vehicles = VEHICLE_SERVICE.createVehicles(5);

        Auto auto = new Auto("AutoContainer", AutoManufacturer.BMW, BigDecimal.TEN, "AutoContainer");
        Bus bus = new Bus("AutoContainer", BusManufacturer.MAN, BigDecimal.TEN, 15);
        Motorbike motorbike = new Motorbike("MotorbikeContainer", MotorbikeManufacturer.YAMAHA, BigDecimal.TEN, 200);

        AUTO_SERVICE.saveVehicles(autos);
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
        CONTAINER.increasePrice(11);*/

        /* Garage */
        /*LOGGER.info("\nGarage");
        GARAGE.add(auto);
        GARAGE.add(bus);
        GARAGE.add(motorbike);
        LOGGER.info(GARAGE.printAll());
        GARAGE.search(GARAGE.getRestyling(auto));
        LOGGER.info(GARAGE.search(GARAGE.getRestyling(auto)));
        LOGGER.info(GARAGE.getFirst());
        LOGGER.info(GARAGE.getLast());
        LOGGER.info(GARAGE.set(GARAGE.getRestyling(auto),
                new Auto("TEST", AutoManufacturer.BMW, BigDecimal.ZERO,"qwer")));
        LOGGER.info(GARAGE.getRestylingCount());
        LOGGER.info(GARAGE.printAll());
        LOGGER.info("\n");
        StringBuilder stringBuilder = new StringBuilder();
        for (Vehicle vehicle : GARAGE) {
            stringBuilder.append(vehicle.toString()).append("\n");
        }
        LOGGER.info(stringBuilder.toString());
        LOGGER.info(GARAGE.remove(GARAGE.getRestyling(motorbike)));
        LOGGER.info(GARAGE.printAll());*/

        /* Comparator */
        /*LOGGER.info("\nComparator");
        vehicles.addAll(MOTORBIKE_SERVICE.createVehicles(5));
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

        Comparator<Vehicle> sortByPrice = new Vehicle.SortByPrice();
        Comparator<Vehicle> sortByName = new Vehicle.SortByName();
        Comparator<Vehicle> sortByQuantity = new Vehicle.SortByQuantity();
        Comparator<Vehicle> vehicleComparator = sortByPrice.thenComparing(sortByName).thenComparing(sortByQuantity);

        vehicles.sort(vehicleComparator);

        LOGGER.info("\n");
        vehicles.sort(new Vehicle.SortByPrice());
        for (Vehicle vehicle : vehicles) {
            LOGGER.info(vehicle.toString());
        }*/

        /* UI */
        /*final Action[] actions = Action.values();
        final List<String> names = getNames(actions);
        Command command;
        do {
            command = executeCommand(actions, names);
        } while (command != null);*/

        /* BinaryTree */
        /*BinaryTree<Vehicle> binaryTree = new BinaryTree<>();
        binaryTree.add(new Auto("TEST", AutoManufacturer.BMW, BigDecimal.valueOf(6), "TEST"));
        binaryTree.add(new Auto("TEST", AutoManufacturer.BMW, BigDecimal.valueOf(4), "TEST"));
        binaryTree.add(new Auto("TEST", AutoManufacturer.BMW, BigDecimal.valueOf(8), "TEST"));
        binaryTree.add(new Auto("TEST", AutoManufacturer.BMW, BigDecimal.valueOf(3), "TEST"));
        binaryTree.add(new Auto("TEST", AutoManufacturer.BMW, BigDecimal.valueOf(5), "TEST"));
        binaryTree.add(new Auto("TEST", AutoManufacturer.BMW, BigDecimal.valueOf(7), "TEST"));
        binaryTree.add(new Auto("TEST", AutoManufacturer.BMW, BigDecimal.valueOf(9), "TEST"));

        binaryTree.print();
        binaryTree.sumTree();*/

        /* Stream API */
        /*StreamApi streamApi = new StreamApi();
        System.out.println("Create collection Vehicles");
        final List<Auto> autos = AUTO_SERVICE.createVehicles(1);
        final List<Bus> buses = BUS_SERVICE.createVehicles(1);
        final List<Motorbike> motorbikes = MOTORBIKE_SERVICE.createVehicles(1);
        final List<Vehicle> vehicles = new LinkedList<>();
        vehicles.addAll(autos);
        vehicles.addAll(buses);
        vehicles.addAll(motorbikes);

        System.out.println("Print collection Vehicles");
        for (Vehicle vehicle : vehicles) {
            System.out.println(vehicle.toString());
        }

        System.out.println("findVehiclesPriceExpensiveThan");
        System.out.println(streamApi.findVehiclesPriceExpensiveThan(500, vehicles));

        System.out.println("calculateVehiclesPriceSum");
        System.out.println(streamApi.calculateVehiclesPriceSum(vehicles));

        System.out.println("sortVehiclesByModel");
        for (Map.Entry<String, String> entry : streamApi.sortVehiclesByModel(vehicles).entrySet()) {
            System.out.println(entry.getKey() + ", " + entry.getValue());
        }

        System.out.println("getStatistics");
        System.out.println(streamApi.getStatistics(vehicles));

        System.out.println("checkPrice");
        System.out.println(streamApi.checkPriceForZeroAndNull(vehicles));


        System.out.println("getAuto");
        Map<String, Auto> autoMap = new HashMap<>();
        autoMap.put("1", (Auto) vehicles.get(0));
        autoMap.put("2", new Auto("TEST", AutoManufacturer.BMW, BigDecimal.ONE, "TEST"));
        autoMap.put("3", new Auto("TEST", AutoManufacturer.BMW, BigDecimal.ZERO, "TEST"));
        for (int i = 0; i < streamApi.getAuto(autoMap).size(); i++) {
            System.out.println(streamApi.getAuto(autoMap).get(i));
        }

        System.out.println("checkDetail");
        List<String> detailAutoOne = new LinkedList<>();
        detailAutoOne.add("engine");
        detailAutoOne.add("brakes");
        detailAutoOne.add("wheels");
        detailAutoOne.add("tires");
        detailAutoOne.add("doors");
        List<String> detailAutoTwo = new LinkedList<>();
        detailAutoTwo.add("brakes");
        detailAutoTwo.add("wheels");
        detailAutoTwo.add("tires");
        detailAutoTwo.add("doors");
        List<String> detailAutoThree = new LinkedList<>();
        detailAutoThree.add("wheels");
        detailAutoThree.add("tires");
        detailAutoThree.add("doors");
        vehicles.add(new Auto("TEST", AutoManufacturer.BMW, BigDecimal.TEN, "TEST", detailAutoOne));
        vehicles.add(new Auto("TEST", AutoManufacturer.BMW, BigDecimal.TEN, "TEST", detailAutoTwo));
        vehicles.add(new Auto("TEST", AutoManufacturer.BMW, BigDecimal.TEN, "TEST", detailAutoThree));
        System.out.println(streamApi.checkDetail(vehicles, "tires"));*/

        /* IO/NIO */
        ReadFromFile readFromFile = new ReadFromFile();
        List<Vehicle> vehicles = new LinkedList<>();
        vehicles.add(readFromFile.readFileAndCreateAuto("src/main/resources/readme.xml"));
        vehicles.add(readFromFile.readFileAndCreateAuto("src/main/resources/readme.json"));
        for (Vehicle vehicle : vehicles) {
            System.out.println(vehicle);
        }
    }

    /* UI */
    /*private static List<String> getNames(Action[] actions) {
        final List<String> names = new ArrayList<>(actions.length);
        for (Action action : actions) {
            names.add(action.getName());
        }
        return names;
    }

    private static Command executeCommand(Action[] actions, List<String> names) {
        int userInput = UserInputUtil.getUserInput("What you want:", names);
        final Action action = actions[userInput];
        return action.execute();
    }*/
}
