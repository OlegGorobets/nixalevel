package com.nixalevel.lesson10.service;

import com.nixalevel.lesson10.repository.JDBCInvoiceRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

public class InvoiceService {
    private static final Logger LOGGER = LoggerFactory.getLogger(InvoiceService.class);
    private final JDBCInvoiceRepository repository;
    private static InvoiceService instance;

    public InvoiceService(JDBCInvoiceRepository repository) {
        this.repository = repository;
    }

    public static InvoiceService getInstance() {
        if (instance == null) {
            instance = new InvoiceService(JDBCInvoiceRepository.getInstance());
        }
        return instance;
    }

    public void createInvoice(int vehicleNumber) {
        if (vehicleNumber <= repository.getCountRowsWithoutInvoiceId()) {
            String uuid = UUID.randomUUID().toString();
            repository.create(uuid, new Date());
            repository.setInvoiceId(uuid, vehicleNumber);
            LOGGER.info("Created {} {} with {} vehicles", "Invoice", uuid, vehicleNumber);
        } else {
            throw new IllegalArgumentException("The number of vehicles is less than the specified");
        }
    }

    public void getAll() {
        repository.getAll().forEach(System.out::println);
    }

    public void getCountOfInvoice() {
        System.out.println("Number of invoices: " + repository.getCountRowsWithInvoiceId());
    }

    public void updateCreatedTime(String id) {
        System.out.println("Invoice created time updated. " + repository.updateCreatedInvoice(id));
    }

    public void getInvoiceExpensiveThan(int amount) {
        for (Map.Entry<String, BigDecimal> entry : repository.getInvoiceWithAmount().entrySet()) {
            if (entry.getValue().compareTo(new BigDecimal(amount)) > 0) {
                System.out.println("Invoice: " + entry.getKey() + ", Amount: " + entry.getValue());
            }
        }
    }

    public void getGroupByAmount() {
        for (Map.Entry<String, BigDecimal> entry : repository.groupInvoiceByAmount().entrySet()) {
            System.out.println("Invoice: " + entry.getKey() + ", Amount: " + entry.getValue());
        }
    }

    public boolean removeAll() {
        repository.clear();
        return true;
    }
}
