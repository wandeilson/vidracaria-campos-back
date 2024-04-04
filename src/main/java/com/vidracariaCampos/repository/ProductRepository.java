package com.vidracariaCampos.repository;

import com.vidracariaCampos.model.entity.Customer;
import com.vidracariaCampos.model.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<Product, UUID> {

    boolean existsByName(String name);

    @Query("SELECT p FROM Product p WHERE p.idUser = :idUser")
    List<Product> findProductsByUserId(@Param("idUser") UUID idUser);

    boolean existsByIdAndIdUser(UUID id, UUID idUser);
    @Transactional
    void deleteByIdAndIdUser(UUID uuid, UUID idUser);
    Optional<Product> findByIdAndIdUser(UUID id, UUID idUser);

}
