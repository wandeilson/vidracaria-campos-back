package com.vidracariaCampos.service;

import com.vidracariaCampos.model.entity.Customer;
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

    public Customer updade(Customer customer){
        return customerRepositoty.save(customer);
    }

    public List<Customer> getAllCustomers(UUID idUser) {
        return customerRepositoty.findCustomersByUserId(idUser);
    }

    public Optional<Customer> findById(UUID id, UUID idUser) {
        return customerRepositoty.findByIdAndIdUser(id, idUser);
    }

    public void deleteById(UUID id, UUID idUser) {
        customerRepositoty.deleteByIdAndIdUser(id, idUser);
    }

    public boolean existsByEmail(String email, UUID idUser){
        return customerRepositoty.existsByEmailAndIdUser(email, idUser);
    }

    public boolean existsByCpf_cnpj(String cpf_cnpj, UUID idUser){
        return customerRepositoty.existsByCpfcnpjAndIdUser(cpf_cnpj, idUser);
    }

    public boolean existsByIdAndIdUser(UUID id, UUID idUser){
        return customerRepositoty.existsByIdAndIdUser(id, idUser);
    }

}
