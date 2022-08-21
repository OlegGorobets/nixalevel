package com.example.service;

import com.example.model.Product;
import com.example.model.ProductBundle;
import com.example.model.ProductNotifiable;
import com.example.repository.ProductRepository;

import java.util.List;
import java.util.Random;

public class ProductService {
    private final ProductRepository repository;

    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    public void save(Product product) {
        repository.save(product);
    }

    public List<Product> getAll() {
        return repository.getAll();
    }

    public Product generateRandomProduct() {
        Random random = new Random();
        ProductBundle productBundle = new ProductBundle();
        ProductNotifiable productNotifiable = new ProductNotifiable();
        if (random.nextBoolean()) {
            productBundle.setId(random.nextLong());
            productBundle.setAvailable(random.nextBoolean());
            productBundle.setTitle(random.nextFloat() + "" + random.nextDouble());
            productBundle.setPrice(random.nextDouble());
            productBundle.setChannel(random.nextBoolean() + "" + random.nextDouble());
            productBundle.setEmail("");
            productBundle.setAmount(random.nextInt(15));
            return productBundle;
        } else {
            productNotifiable.setId(random.nextLong());
            productNotifiable.setAvailable(random.nextBoolean());
            productNotifiable.setTitle(random.nextFloat() + "" + random.nextDouble());
            productNotifiable.setPrice(random.nextDouble());
            productNotifiable.setChannel(random.nextBoolean() + "" + random.nextDouble());
            productNotifiable.setEmail("");
            return productNotifiable;
        }
    }
}
