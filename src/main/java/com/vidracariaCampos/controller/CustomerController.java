package com.vidracariaCampos.controller;
import jakarta.validation.Valid;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import com.vidracariaCampos.model.dto.CustomerDTO;
import com.vidracariaCampos.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping
    public ResponseEntity<Object> createCustomer(@RequestBody @Valid CustomerDTO customerDTO){
        return ResponseEntity.created(null).body(customerService.save(customerDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateCustomer(@PathVariable (value = "id") UUID id,
        @RequestBody @Valid CustomerDTO customerDTO){
        return ResponseEntity.ok().body(customerService.update(customerDTO,id));
    }

    @GetMapping
    public ResponseEntity<Object> getAllCustomers(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "15") int size){
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(customerService.getAllCustomers(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getCustomerById(@PathVariable (value = "id") UUID id){
        return ResponseEntity.ok( customerService.findById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteCustomerById (@PathVariable (value = "id") UUID id){
        customerService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
