package com.vidracariaCampos.controller;

import com.vidracariaCampos.entity.Customer;
import com.vidracariaCampos.service.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    private final CustomerService customerService;
    public CustomerController(CustomerService customerService){
        this.customerService = customerService;
    }
    @PostMapping
    public ResponseEntity<Customer> create (@RequestBody Customer customer){
        return ResponseEntity.status(HttpStatus.CREATED).body(customerService.save(customer));
    }

    @GetMapping()
    public ResponseEntity<List<Customer>> getAllCustomers(){
        return ResponseEntity.ok(customerService.getAllCustomers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable (value = "id") UUID id){
        Optional<Customer> customerOptional = customerService.findById(id);
        if(!customerOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(customerOptional.get());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteCustomerById (@PathVariable (value = "id") UUID id){
        Optional<Customer> optionalCustomer = customerService.findById(id);
        if(!optionalCustomer.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Customer not found.");
        }
        customerService.delete(optionalCustomer.get());
        return ResponseEntity.status(HttpStatus.OK).body("Customer deleted successfully.");

    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateCustomer(@PathVariable (value = "id") UUID id,
                                                    @RequestBody Customer customer){
        Optional<Customer> customerOptional = customerService.findById(id);
        if(!customerOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Customer not found");
        }

        customer.setId(customerOptional.get().getId());

        return ResponseEntity.status(HttpStatus.OK).body(customerService.save(customer));
    }











}
