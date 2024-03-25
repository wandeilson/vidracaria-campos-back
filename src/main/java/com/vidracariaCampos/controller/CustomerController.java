package com.vidracariaCampos.controller;

import com.vidracariaCampos.model.dto.CustomerDTO;
import com.vidracariaCampos.model.entity.Customer;
import com.vidracariaCampos.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.time.LocalDateTime;
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

    @PostMapping()
    public ResponseEntity<Object> createCustomer(@RequestBody @Valid CustomerDTO customerDTO){
        if(customerDTO.email() != null){
            if(customerService.existsByEmail(customerDTO.email())){
                return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict: Email is already in use!");
            }
        }
        if (customerDTO.cpfcnpj() != null){
            if(customerService.existsByCpf_cnpj(customerDTO.cpfcnpj())){
                return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict: CPF or CNPJ is already in use!");
            }
        }
        var customerEntity = new Customer();
        BeanUtils.copyProperties(customerDTO, customerEntity);
        customerEntity.setRegistrationDate(LocalDateTime.now());
        customerEntity.setIdUser(AuthenticationController.getUserLogged().getId());
        return ResponseEntity.status(HttpStatus.CREATED).body(customerService.save(customerEntity));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateCustomer(@PathVariable (value = "id") UUID id,
                                                 @RequestBody @Valid CustomerDTO customerDTO){
        Optional<Customer> customerOptional = customerService.findById(id, AuthenticationController.getUserLogged().getId());
        if(!customerService.existsByIdAndIdUser(id, AuthenticationController.getUserLogged().getId())) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Customer not found");
        }
        if(customerDTO.email() != null){
            if(customerService.existsByEmail(customerDTO.email())){
                if(!customerDTO.email().equals(customerOptional.get().getEmail())){
                    return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict: Email is already in use!");
                }
            }
        }
        if (customerDTO.cpfcnpj() != null){
            if(customerService.existsByCpf_cnpj(customerDTO.cpfcnpj())){
                if(!customerDTO.cpfcnpj().equals(customerOptional.get().getCpfcnpj())){
                    return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict: CPF or CNPJ is already in use!");
                }
            }
        }

        var customerEntity = new Customer();
        BeanUtils.copyProperties(customerDTO, customerEntity);
        customerEntity.setRegistrationDate(customerOptional.get().getRegistrationDate());
        customerEntity.setId(customerOptional.get().getId());
        customerEntity.setIdUser(AuthenticationController.getUserLogged().getId());
        return ResponseEntity.status(HttpStatus.OK).body(customerService.save(customerEntity));
    }

    @GetMapping()
    public ResponseEntity<List<Customer>> getAllCustomers(){
        return ResponseEntity.ok(customerService.getAllCustomers(AuthenticationController.getUserLogged().getId()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable (value = "id") UUID id){
        Optional<Customer> customerOptional = customerService.findById(id, AuthenticationController.getUserLogged().getId());
        if(!customerOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(customerOptional.get());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteCustomerById (@PathVariable (value = "id") UUID id){
        Optional<Customer> optionalCustomer = customerService.findById(id, AuthenticationController.getUserLogged().getId());
        if(!optionalCustomer.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Customer not found.");
        }
        customerService.delete(optionalCustomer.get());
        return ResponseEntity.status(HttpStatus.OK).body("Customer deleted successfully.");

    }

    @PostMapping("/{search}")
    public List<Customer> searchCustomers(@PathVariable (value = "search") String search) {
        return customerService.searchCustomers(search);
    }

}
