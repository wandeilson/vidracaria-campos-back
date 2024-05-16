package com.vidracariaCampos.repository;

import com.vidracariaCampos.model.entity.ProductStock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface ProductStockRepository extends JpaRepository<ProductStock, UUID> {
}
