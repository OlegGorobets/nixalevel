package com.nixalevel.module.service;

import com.nixalevel.module.model.customer.Customer;

import java.util.Random;
import com.github.javafaker.Faker;

public class PersonService {
    private static final int MIN_AGE = 10;
    private static final int MAX_AGE = 100;

    public Customer generateRandomPerson() {
        Random random = new Random();
        return new Customer(randomEmail(), random.nextInt(MIN_AGE, MAX_AGE));
    }

    private String randomEmail() {
        Faker faker = new Faker();
        return faker.internet().emailAddress();
    }
}
