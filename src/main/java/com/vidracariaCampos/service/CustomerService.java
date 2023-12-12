package com.vidracariaCampos.service;

import com.vidracariaCampos.entity.Customer;
import com.vidracariaCampos.repository.CustomerRepositoty;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CustomerService {

    private final CustomerRepositoty customerRepositoty;

    public CustomerService (CustomerRepositoty customerRepositoty){
        this.customerRepositoty = customerRepositoty;
    }
    public Customer save(Customer customer) {
        customer.setRegistrationDate(LocalDateTime.now());
        return customerRepositoty.save(customer);
    }

    public List<Customer> getAllCustomers() {
        return customerRepositoty.findAll();
    }

    public Optional<Customer> findById(UUID id) {
        return customerRepositoty.findById(id);
    }

    public void delete(Customer customer) {
        customerRepositoty.delete(customer);
    }

    public boolean existsByEmail(String email){
        return customerRepositoty.existsByEmail(email);
    }

    public boolean existsByCpf_cnpj(String cpf_cnpj){
        return customerRepositoty.existsByCpfcnpj(cpf_cnpj);
    }
}
