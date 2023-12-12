package com.vidracariaCampos.repository;

import com.vidracariaCampos.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface CustomerRepositoty extends JpaRepository<Customer, UUID> {

    public boolean existsByEmail(String email);
    public boolean existsByCpfcnpj(String cpf_cnpj);

}
