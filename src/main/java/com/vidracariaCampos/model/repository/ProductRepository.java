package com.vidracariaCampos.model.repository;

import com.vidracariaCampos.model.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<Product, UUID> {

    boolean existsByName(String name);

    @Override
    boolean existsById(UUID id);
}
