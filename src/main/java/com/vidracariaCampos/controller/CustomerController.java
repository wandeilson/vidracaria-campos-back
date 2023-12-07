package com.vidracariaCampos.controller;

import com.vidracariaCampos.entity.Customer;
import com.vidracariaCampos.service.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    private final CustomerService customerService;
    public CustomerController(CustomerService customerService){
        this.customerService = customerService;
    }
    public ResponseEntity<Customer> create (@RequestBody Customer customer){
        return ResponseEntity.status(HttpStatus.CREATED).body(customerService.save(customer));

    }
}
