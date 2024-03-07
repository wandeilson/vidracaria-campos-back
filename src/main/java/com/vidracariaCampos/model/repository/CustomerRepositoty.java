package com.vidracariaCampos.model.repository;

import com.vidracariaCampos.model.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;
@Repository
public interface CustomerRepositoty extends JpaRepository<Customer, UUID> {

    boolean existsByEmail(String email);
    boolean existsByCpfcnpj(String cpf_cnpj);

    @Query("SELECT c FROM Customer c WHERE " +
            "LOWER(c.name) LIKE %:search% OR " +
            "LOWER(c.cpfcnpj) LIKE %:search% OR " +
            "LOWER(c.email) LIKE %:search% OR " +
            "LOWER(c.phone) LIKE %:search% OR " +
            "LOWER(c.address.address) LIKE %:search% OR " +
            "LOWER(c.address.zipCode) LIKE %:search% OR " +
            "LOWER(c.address.number) LIKE %:search% OR " +
            "LOWER(c.address.city) LIKE %:search% OR " +
            "LOWER(c.address.state) LIKE %:search% OR " +
            "LOWER(c.address.landmark) LIKE %:search%"
    )
    List<Customer> searchCustomer(@Param("search") String search);




}
