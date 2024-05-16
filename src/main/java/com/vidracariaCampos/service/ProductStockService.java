package com.vidracariaCampos.service;

import com.vidracariaCampos.model.entity.ProductStock;
import com.vidracariaCampos.repository.ProductStockRepository;
import org.springframework.stereotype.Service;

@Service
public class ProductStockService {

    private final ProductStockRepository productStockRepository;
    private final StockService stockService;

    public ProductStockService(ProductStockRepository productStockRepository, StockService stockService) {
        this.productStockRepository = productStockRepository;
        this.stockService = stockService;
    }

    public void create(ProductStock productStock){
       addToStock(productStockRepository.save(productStock));
    }
    public void addToStock(ProductStock productStock){
        stockService.addProductStock(productStock);
    }
}
