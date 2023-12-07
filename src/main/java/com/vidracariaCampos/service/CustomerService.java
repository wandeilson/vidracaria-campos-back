package com.vidracariaCampos.service;

import com.vidracariaCampos.entity.Customer;
import com.vidracariaCampos.repository.CustomerRepositoty;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    private final CustomerRepositoty customerRepositoty;

    public CustomerService (CustomerRepositoty customerRepositoty){
        this.customerRepositoty = customerRepositoty;
    }
    public Customer save(Customer customer) {
        return customerRepositoty.save(customer);
    }
}
