package com.vidracariaCampos.service;

import com.vidracariaCampos.model.entity.Customer;
import com.vidracariaCampos.model.repository.CustomerRepositoty;
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
        if(customerRepositoty.existsByCpfcnpj(customer.getCpfcnpj())){
            throw new RuntimeException("Customer already exists");
        }
        customer.setRegistrationDate(LocalDateTime.now());
        return customerRepositoty.save(customer);
    }

    public List<Customer> getAllCustomers(UUID idUser) {
        return customerRepositoty.findCustomersByUserId(idUser);
    }

    public Optional<Customer> findById(UUID id, UUID idUser) {
        return customerRepositoty.findByIdAndIdUser(id, idUser);
    }

    public void delete(Customer customer) {
        customerRepositoty.findById(customer.getId()).orElseThrow(() -> new IllegalArgumentException("customer not found"));
        customerRepositoty.delete(customer);
    }

    public boolean existsByEmail(String email){
        return customerRepositoty.existsByEmail(email);
    }

    public boolean existsByCpf_cnpj(String cpf_cnpj){
        return customerRepositoty.existsByCpfcnpj(cpf_cnpj);
    }

    public boolean existsByIdAndIdUser(UUID id, UUID idUser){
        return customerRepositoty.existsByIdAndIdUser(id, idUser);
    }

    public List<Customer> searchCustomers(String search){
        return customerRepositoty.searchCustomer(search.toLowerCase());
    }
}
