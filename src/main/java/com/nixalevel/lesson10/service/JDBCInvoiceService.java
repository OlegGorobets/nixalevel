package com.nixalevel.lesson10.service;

import com.nixalevel.lesson10.model.Auto;
import com.nixalevel.lesson10.repository.CrudRepository;
import com.nixalevel.lesson10.repository.JDBCAutoRepository;
import com.nixalevel.lesson10.repository.JDBCInvoiceRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;

public class JDBCInvoiceService {
    private static final Logger LOGGER = LoggerFactory.getLogger(JDBCAutoService.class);
    private final JDBCInvoiceRepository repository;
    private static JDBCInvoiceService instance;

    public JDBCInvoiceService(JDBCInvoiceRepository repository) {
        this.repository = repository;
    }

    public static JDBCInvoiceService getInstance() {
        if (instance == null) {
            instance = new JDBCInvoiceService(JDBCInvoiceRepository.getInstance());
        }
        return instance;
    }
}
