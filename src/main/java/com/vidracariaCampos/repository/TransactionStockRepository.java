package com.vidracariaCampos.repository;

import com.vidracariaCampos.model.entity.TransactionStock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface TransactionStockRepository extends JpaRepository<TransactionStock, UUID> {
}
