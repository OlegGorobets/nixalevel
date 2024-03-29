package com.nixalevel.lesson10;

import com.nixalevel.lesson10.config.HibernateFactoryUtil;
import com.nixalevel.lesson10.model.*;
import com.nixalevel.lesson10.repository.hibernate.HibernateAutoRepository;
import com.nixalevel.lesson10.repository.hibernate.HibernateBusRepository;
import com.nixalevel.lesson10.repository.hibernate.HibernateInvoiceRepository;
import com.nixalevel.lesson10.repository.hibernate.HibernateMotorbikeRepository;
import com.nixalevel.lesson10.repository.collection.AutoRepository;
import com.nixalevel.lesson10.repository.collection.BusRepository;
import com.nixalevel.lesson10.repository.collection.MotorbikeRepository;
/*import com.nixalevel.lesson10.repository.jdbc.JDBCAutoRepository;
import com.nixalevel.lesson10.repository.jdbc.JDBCBusRepository;
import com.nixalevel.lesson10.repository.jdbc.JDBCMotorbikeRepository;*/
import com.nixalevel.lesson10.repository.mongo.MongoAutoRepository;
import com.nixalevel.lesson10.repository.mongo.MongoBusRepository;
import com.nixalevel.lesson10.repository.mongo.MongoInvoiceRepository;
import com.nixalevel.lesson10.repository.mongo.MongoMotorbikeRepository;
import com.nixalevel.lesson10.service.*;
import com.nixalevel.lesson10.utility.*;
import org.flywaydb.core.Flyway;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class Main {
    private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);

    /*private static final AutoService AUTO_SERVICE = new AutoService(new AutoRepository());
    private static final BusService BUS_SERVICE = new BusService(BusRepository.getInstance());
    private static final MotorbikeService MOTORBIKE_SERVICE = new MotorbikeService(MotorbikeRepository.getInstance());
    private static final VehicleService VEHICLE_SERVICE = new AutoService(AutoRepository.getInstance());
    private static final Container<Vehicle> CONTAINER = new Container<>();*/

    /*private static final Garage<Vehicle> GARAGE = new Garage<>();*/

    /*private static final AutoService JDBC_AUTO_SERVICE = new AutoService(JDBCAutoRepository.getInstance());
    private static final BusService JDBC_BUS_SERVICE = new BusService(JDBCBusRepository.getInstance());
    private static final MotorbikeService JDBC_MOTORBIKE_SERVICE = new MotorbikeService(JDBCMotorbikeRepository.getInstance());
    private static final InvoiceService JDBC_INVOICE_SERVICE = new InvoiceService(JDBCInvoiceRepository.getInstance());*/

   /* private static final AutoService HIBERNATE_AUTO_SERVICE = new AutoService(HibernateAutoRepository.getInstance());
    private static final BusService HIBERNATE_BUS_SERVICE = new BusService(HibernateBusRepository.getInstance());
    private static final MotorbikeService HIBERNATE_MOTORBIKE_SERVICE = new MotorbikeService(HibernateMotorbikeRepository.getInstance());
    private static final InvoiceService HIBERNATE_INVOICE_SERVICE = new InvoiceService(HibernateInvoiceRepository.getInstance());*/

    private static final AutoService MONGO_AUTO_SERVICE = new AutoService(MongoAutoRepository.getInstance());
    private static final BusService MONGO_BUS_SERVICE = new BusService(MongoBusRepository.getInstance());
    private static final MotorbikeService MONGO_MOTORBIKE_SERVICE = new MotorbikeService(MongoMotorbikeRepository.getInstance());
    private static final InvoiceService MONGO_INVOICE_SERVICE = new InvoiceService(MongoInvoiceRepository.getInstance());

    public static void main(String[] args) throws ParseException, SQLException, InterruptedException {
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
        /*ReadFromFile readFromFile = new ReadFromFile();
        final List<Vehicle> vehicles = new LinkedList<>();
        final List<String> filePaths = new LinkedList<>();
        filePaths.add("readme.xml");
        filePaths.add("readme.json");

        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream inputStream;
        for (String filePath : filePaths) {
            inputStream = classLoader.getResourceAsStream(filePath);
            vehicles.add(readFromFile.readFileAndCreateAuto(inputStream));
        }

        for (Vehicle vehicle : vehicles) {
            System.out.println(vehicle);
        }*/

        /* Builder */
        /*Auto auto = new Auto.Builder()
                .withAutoManufacturer(AutoManufacturer.TOYOTA)
                .withCount(1)
                .withPrice(BigDecimal.valueOf(7000000))
                .withBodyType("Hatchback")
                .withModel("TRUENO")
                .build();
        System.out.println(auto);*/

            /* Reflection */
        /*AutoService autoServiceOne = ReflectionUtil.getClass(AutoService.class);
        Field declaredFieldAutoServiceOne = autoServiceOne.getClass().getDeclaredField("instance");
        declaredFieldAutoServiceOne.setAccessible(true);
        System.out.println(declaredFieldAutoServiceOne.get(autoServiceOne).hashCode());
        System.out.println(autoServiceOne.hashCode());

        AutoService autoServiceTwo = ReflectionUtil.getClass(AutoService.class);
        Field declaredFieldAutoServiceTwo = autoServiceTwo.getClass().getDeclaredField("instance");
        declaredFieldAutoServiceTwo.setAccessible(true);
        System.out.println(declaredFieldAutoServiceTwo.get(autoServiceTwo).hashCode());
        System.out.println(autoServiceTwo.hashCode());

        AutoRepository autoRepositoryOne = ReflectionUtil.getClass(AutoRepository.class);
        Field declaredFieldAutoRepositoryOne = autoRepositoryOne.getClass().getDeclaredField("instance");
        declaredFieldAutoRepositoryOne.setAccessible(true);
        System.out.println(declaredFieldAutoRepositoryOne.get(autoRepositoryOne).hashCode());
        System.out.println(autoRepositoryOne.hashCode());

        AutoRepository autoRepositoryTwo = ReflectionUtil.getClass(AutoRepository.class);
        Field declaredFieldAutoRepositoryTwo = autoRepositoryTwo.getClass().getDeclaredField("instance");
        declaredFieldAutoRepositoryTwo.setAccessible(true);
        System.out.println(declaredFieldAutoRepositoryTwo.get(autoRepositoryTwo).hashCode());
        System.out.println(autoRepositoryTwo.hashCode());*/

            /* JDBC */
        /*JDBCConfig.getConnection();
        JDBC_AUTO_SERVICE.removeAll();
        JDBC_BUS_SERVICE.removeAll();
        JDBC_MOTORBIKE_SERVICE.removeAll();
        INVOICE_SERVICE.removeAll();


        JDBC_AUTO_SERVICE.createVehicles(5);
        JDBC_AUTO_SERVICE.print();

        JDBC_BUS_SERVICE.createVehicles(5);
        JDBC_BUS_SERVICE.print();

        JDBC_MOTORBIKE_SERVICE.createVehicles(5);
        JDBC_MOTORBIKE_SERVICE.print();

        JDBC_INVOICE_SERVICE.createInvoice(5);
        JDBC_INVOICE_SERVICE.createInvoice(5);
        JDBC_INVOICE_SERVICE.createInvoice(5);
        JDBC_INVOICE_SERVICE.getAll();

        JDBC_INVOICE_SERVICE.getInvoiceExpensiveThan(500);
        JDBC_INVOICE_SERVICE.getCountOfInvoice();
        JDBC_INVOICE_SERVICE.updateCreatedTime("");
        JDBC_INVOICE_SERVICE.getGroupByAmount();*/

        /* Hibernate */
        /*HibernateFactoryUtil.getSessionFactory();
        HIBERNATE_AUTO_SERVICE.createVehicles(5);
        HIBERNATE_BUS_SERVICE.createVehicles(5);
        HIBERNATE_MOTORBIKE_SERVICE.createVehicles(5);

        HIBERNATE_AUTO_SERVICE.removeAll();
        HIBERNATE_BUS_SERVICE.removeAll();
        HIBERNATE_MOTORBIKE_SERVICE.removeAll();

        HIBERNATE_AUTO_SERVICE.getAll().forEach(System.out::println);
        HIBERNATE_BUS_SERVICE.getAll().forEach(System.out::println);
        HIBERNATE_MOTORBIKE_SERVICE.getAll().forEach(System.out::println);

        List<Vehicle> vehicleList = Stream.of(HIBERNATE_AUTO_SERVICE.getAll(),
                        HIBERNATE_BUS_SERVICE.getAll(),
                        HIBERNATE_MOTORBIKE_SERVICE.getAll())
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
        List<Vehicle> forInvoiceOne = new ArrayList<>();
        forInvoiceOne.add(vehicleList.get(0));
        forInvoiceOne.add(vehicleList.get(1));
        forInvoiceOne.add(vehicleList.get(2));
        List<Vehicle> forInvoiceTwo = new ArrayList<>();
        forInvoiceTwo.add(vehicleList.get(3));
        forInvoiceTwo.add(vehicleList.get(4));
        forInvoiceTwo.add(vehicleList.get(5));
        List<Vehicle> forInvoiceThree = new ArrayList<>();
        forInvoiceThree.add(vehicleList.get(6));
        forInvoiceThree.add(vehicleList.get(7));
        forInvoiceThree.add(vehicleList.get(8));

        HIBERNATE_INVOICE_SERVICE.createInvoice(forInvoiceOne);
        HIBERNATE_INVOICE_SERVICE.createInvoice(forInvoiceTwo);
        HIBERNATE_INVOICE_SERVICE.createInvoice(forInvoiceThree);

        HIBERNATE_INVOICE_SERVICE.getAll().forEach(System.out::println);

        HIBERNATE_INVOICE_SERVICE.getInvoiceCount();
        HIBERNATE_INVOICE_SERVICE.setCreated("a1166fb5-09bd-4479-8ee9-5a729c1089bb", new Date());
        HIBERNATE_INVOICE_SERVICE.getInvoiceExpensiveThan(1500);
        HIBERNATE_INVOICE_SERVICE.getGroupByAmount();*/

        /* Flyway */
        /*Flyway flyway = Flyway.configure()
                .dataSource("jdbc:postgresql://localhost:5432/hibernate", "postgres", "root")
                .baselineOnMigrate(true)
                .locations("db/migration")
                .load();
        flyway.migrate();*/

        /* NoSQL */
        MONGO_AUTO_SERVICE.removeAll();
        MONGO_BUS_SERVICE.removeAll();
        MONGO_MOTORBIKE_SERVICE.removeAll();
        MONGO_INVOICE_SERVICE.removeAll();

        MONGO_AUTO_SERVICE.createVehicles(5);
        MONGO_BUS_SERVICE.createVehicles(5);
        MONGO_MOTORBIKE_SERVICE.createVehicles(5);

        MONGO_AUTO_SERVICE.getAll().forEach(System.out::println);
        MONGO_BUS_SERVICE.getAll().forEach(System.out::println);
        MONGO_MOTORBIKE_SERVICE.getAll().forEach(System.out::println);

        List<Vehicle> vehicleList = Stream.of(MONGO_AUTO_SERVICE.getAll(),
                        MONGO_BUS_SERVICE.getAll(),
                        MONGO_MOTORBIKE_SERVICE.getAll())
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
        List<Vehicle> forInvoiceOne = new ArrayList<>();
        forInvoiceOne.add(vehicleList.get(0));
        forInvoiceOne.add(vehicleList.get(1));
        forInvoiceOne.add(vehicleList.get(2));
        List<Vehicle> forInvoiceTwo = new ArrayList<>();
        forInvoiceTwo.add(vehicleList.get(3));
        forInvoiceTwo.add(vehicleList.get(4));
        forInvoiceTwo.add(vehicleList.get(5));
        List<Vehicle> forInvoiceThree = new ArrayList<>();
        forInvoiceThree.add(vehicleList.get(6));
        forInvoiceThree.add(vehicleList.get(7));
        forInvoiceThree.add(vehicleList.get(8));

        MONGO_INVOICE_SERVICE.createInvoice(forInvoiceOne);
        MONGO_INVOICE_SERVICE.createInvoice(forInvoiceTwo);
        MONGO_INVOICE_SERVICE.createInvoice(forInvoiceThree);

        /* Concurrency */
        Factory factory = new Factory();
        factory.startFactory();
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
