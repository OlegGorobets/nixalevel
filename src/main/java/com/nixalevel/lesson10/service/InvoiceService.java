package com.nixalevel.lesson10.service;

import com.nixalevel.lesson10.model.Invoice;
import com.nixalevel.lesson10.model.Vehicle;
import com.nixalevel.lesson10.repository.hibernate.HibernateInvoiceRepository;
import com.nixalevel.lesson10.repository.mongo.MongoInvoiceRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

public class InvoiceService {
    private static final Logger LOGGER = LoggerFactory.getLogger(InvoiceService.class);
    private final MongoInvoiceRepository repository;
    private static InvoiceService instance;

    public InvoiceService(MongoInvoiceRepository repository) {
        this.repository = repository;
    }

    public static InvoiceService getInstance() {
        if (instance == null) {
            instance = new InvoiceService(MongoInvoiceRepository.getInstance());
        }
        return instance;
    }

    public void getInvoiceExpensiveThan(int amount) {
        List<Invoice> invoices = repository.getAll().stream()
                .filter(invoice -> invoice.getVehicles().stream()
                        .map(Vehicle::getPrice)
                        .reduce(BigDecimal.ZERO, BigDecimal::add).compareTo(BigDecimal.valueOf(amount)) > 0)
                .toList();
        invoices.forEach(System.out::println);
    }

    public void getGroupByAmount() {
        Map<BigDecimal, List<Invoice>> map = repository.getAll().stream()
                .collect(Collectors.groupingBy(price -> price.getVehicles().stream()
                        .map(Vehicle::getPrice)
                        .reduce(BigDecimal.ZERO, BigDecimal::add)));
        for (Map.Entry<BigDecimal, List<Invoice>> entry : map.entrySet()) {
            System.out.println("Amount: " + entry.getKey() + ", Invoice: " + entry.getValue());
        }
    }

    public void getInvoiceCount() {
        System.out.println("Number of invoices: " + repository.getCount());
    }

    public void setCreated(String id, Date created) {
        if (repository.updateCreated(id, created)) {
            System.out.println("Invoice created time updated. " + created.toString());
        } else {
            System.out.println("No invoice.");
        }
    }

    public void createInvoice(List<Vehicle> vehicleList) {
        final Invoice invoice = create(vehicleList);
        repository.create(invoice);
        LOGGER.info("Created " + invoice.getClass().getSimpleName() + " {}", invoice.getId());
    }


    public Invoice create(List<Vehicle> vehicleList) {
        return new Invoice(
                UUID.randomUUID().toString(),
                new Date(),
                vehicleList
        );
    }

    public boolean remove(String id) {
        repository.delete(id);
        LOGGER.info("Removed.");
        return true;
    }

    public boolean change(Invoice invoice) {
        repository.update(invoice);
        LOGGER.info("Changed.");
        return true;
    }

    public boolean removeAll() {
        repository.clear();
        LOGGER.info("Clear.");
        return true;
    }

    public List<Invoice> getAll() {
        return repository.getAll();
    }
}
