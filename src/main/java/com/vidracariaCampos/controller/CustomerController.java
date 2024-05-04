
package com.vidracariaCampos.controller;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import com.vidracariaCampos.model.dto.CustomerDTO;
import com.vidracariaCampos.model.entity.Customer;
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
        try {
            return ResponseEntity.created(null).body(customerService.save(customerDTO));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateCustomer(@PathVariable (value = "id") UUID id,
        @RequestBody @Valid CustomerDTO customerDTO){
        try {
            return ResponseEntity.ok().body(customerService.update(customerDTO,id));
        }catch (Exception e){

            if(e.getMessage().equals("Customer not found"))
                return ResponseEntity.notFound().build();

            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<Object> getAllCustomers(){
        try {
            return ResponseEntity.ok(customerService.getAllCustomers());
        } catch (Exception e){
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getCustomerById(@PathVariable (value = "id") UUID id){
        try {
            return ResponseEntity.ok( customerService.findById(id));
        } catch (Exception e){
            if(e.getMessage().equals("Customer not found"))
                return ResponseEntity.notFound().build();

            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteCustomerById (@PathVariable (value = "id") UUID id){
        try {
            customerService.deleteById(id);
            return ResponseEntity.ok().build();
        } catch (Exception e){
            if(e.getMessage().equals("Customer not found"))
                return ResponseEntity.notFound().build();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
