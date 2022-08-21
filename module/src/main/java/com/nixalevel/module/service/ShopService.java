package com.nixalevel.module.service;

import com.nixalevel.module.input.ReadFromFile;
import com.nixalevel.module.input.UserInput;
import com.nixalevel.module.model.customer.Customer;
import com.nixalevel.module.model.invoice.Invoice;
import com.nixalevel.module.model.invoice.InvoiceType;
import com.nixalevel.module.model.product.Product;
import com.nixalevel.module.repository.ShopRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class ShopService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ShopService.class);

    private static final List<Product> READ_FROM_FILE = ReadFromFile.createProductList();
    private static final double USER_INPUT = UserInput.getUserInput();
    private static final int MIN_PRODUCT_QUANTITY = 1;
    private static final int MAX_PRODUCT_QUANTITY = 5;
    private final ShopRepository repository;

    public ShopService(ShopRepository repository) {
        this.repository = repository;
    }

    public boolean add(Invoice invoice) {
        if (invoice == null) {
            throw new IllegalArgumentException("Invoice must not be null");
        }
        repository.add(invoice);
        return true;
    }

    public List<Invoice> getAll() {
        return repository.getAll();
    }

    public boolean printAll() {
        repository.getAll().forEach(System.out::println);
        return true;
    }

    private List<Product> generateRandomProductList() {
        List<Product> randomProductList = new ArrayList<>();
        Random random = new Random();
        int size = random.nextInt(MIN_PRODUCT_QUANTITY, MAX_PRODUCT_QUANTITY);
        for (int i = 0; i < size; i++) {
            randomProductList.add(READ_FROM_FILE.get(random.nextInt(READ_FROM_FILE.size())));
        }
        return randomProductList;
    }

    public Invoice generateRandomInvoice(Customer person) {
        List<Product> randomProductList = generateRandomProductList();
        BigDecimal sum = productPriceSum(randomProductList);
        InvoiceType invoiceType;
        Date date = new Date();
        if (BigDecimal.valueOf(USER_INPUT).compareTo(sum) < 0 || BigDecimal.valueOf(USER_INPUT).compareTo(sum) == 0) {
            invoiceType = InvoiceType.RETAIL;
        } else {
            invoiceType = InvoiceType.WHOLESALE;
        }
        LOGGER.info("Time: {}, User data: {}, Invoice data: {}, {}", date, person, randomProductList, invoiceType);
        return new Invoice(randomProductList, person, invoiceType, date);
    }

    private BigDecimal productPriceSum(List<Product> invoiceList) {
        return invoiceList.stream()
                .map(Product::getPrice)
                .reduce(BigDecimal::add)
                .orElse(BigDecimal.ZERO);
    }


}
