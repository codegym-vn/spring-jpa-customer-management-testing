package com.codegym.cms.service.impl;

import com.codegym.cms.model.Customer;
import com.codegym.cms.repository.CustomerRepository;
import com.codegym.cms.service.CustomerService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import static org.mockito.Mockito.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit.jupiter.SpringJUnitJupiterConfig;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringJUnitJupiterConfig(classes = {CustomerServiceTestConfig.class})
class CustomerServiceImplTest {

    private static Customer customer;
    private static List<Customer> emptyCustomers;
    private static List<Customer> customers;

    static {
        customer = new Customer("John", "Chris");
        emptyCustomers = new ArrayList<>();
        customers = new ArrayList<>();
        customers.add(customer);
    }

    @Autowired
    private CustomerService customerService;

    @Autowired
    private CustomerRepository customerRepository;

    @AfterEach
    private void resetMocks(){
        Mockito.reset(customerRepository);
    }

    @Test
    void findAllWith1Customer() {
        when(customerRepository.findAll()).thenReturn(customers);

        List<Customer> result = customerService.findAll();
        verify(customerRepository).findAll();
        assertEquals(customers, result);
    }

    @Test
    void findAllWith0Customer() {
        when(customerRepository.findAll()).thenReturn(emptyCustomers);

        List<Customer> result = customerService.findAll();
        verify(customerRepository).findAll();
        assertEquals(emptyCustomers, result);
    }

    @Test
    void findByIdFound() {
        Long id = 1l;
        when(customerRepository.findById(id)).thenReturn(customer);

        Customer result = customerService.findById(id);
        verify(customerRepository).findById(id);
        assertEquals(customer, result);
    }

    @Test
    void findByIdNotFound() {
        Long id = 1l;
        when(customerRepository.findById(id)).thenReturn(null);

        Customer result = customerService.findById(id);
        verify(customerRepository).findById(id);
        assertEquals(null, result);
    }

    @Test
    void save() {
        customerService.save(customer);
        verify(customerRepository).save(customer);
    }

    @Test
    void remove() {
        Long id = 1l;
        customerService.remove(id);
        verify(customerRepository).remove(id);
    }
}