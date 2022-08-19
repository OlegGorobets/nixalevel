package com.nixalevel.module.service;

import com.nixalevel.module.model.customer.Customer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

class PersonServiceTest {
    private PersonService personService;
    private Customer customer = new Customer();

    @BeforeEach
    void setUp() {
        personService = Mockito.mock(PersonService.class);
    }

    @Test
    void generateRandomPerson() {
        Mockito.when(personService.generateRandomPerson()).thenReturn(customer);
    }
}