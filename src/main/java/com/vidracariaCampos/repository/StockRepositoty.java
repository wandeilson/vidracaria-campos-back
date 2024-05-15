package com.vidracariaCampos.repository;

import com.vidracariaCampos.model.entity.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface StockRepositoty extends JpaRepository<Stock, UUID> {
}
