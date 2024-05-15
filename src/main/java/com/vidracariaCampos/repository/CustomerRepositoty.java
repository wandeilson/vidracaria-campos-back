package com.vidracariaCampos.repository;

import com.vidracariaCampos.model.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Repository
public interface CustomerRepositoty extends JpaRepository<Customer, UUID> {
    boolean existsByEmail(String email);
    boolean existsAllByCpfcnpj(String email);

    boolean existsById (UUID id);

    boolean existsByEmailAndIdUser(String email, UUID idUser);
    boolean existsByCpfcnpjAndIdUser(String cpf_cnpj, UUID idUser);

    @Query("SELECT c FROM Customer c WHERE c.idUser = :idUser")
    List<Customer> findCustomersByUserId(@Param("idUser") UUID idUser);

    boolean existsByIdAndIdUser(UUID id, UUID idUser);

    @Transactional
    void deleteByIdAndIdUser(UUID uuid, UUID idUser);

    Optional<Customer> findByIdAndIdUser(UUID id, UUID idUser);
}