package com.vidracariaCampos.service;

import com.vidracariaCampos.model.entity.ProductStock;
import com.vidracariaCampos.repository.ProductStockRepository;
import com.vidracariaCampos.repository.StockRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductStockService {

    private final ProductStockRepository productStockRepository;
    private final StockService stockService;
    private final StockRepository stockRepository;

    public ProductStockService(ProductStockRepository productStockRepository, StockService stockService, StockRepository stockRepository) {
        this.productStockRepository = productStockRepository;
        this.stockService = stockService;
        this.stockRepository = stockRepository;
    }

    public void create(ProductStock productStock){
        productStock.setStock(stockService.getStock());
        addToStock(productStockRepository.save(productStock));
    }
    public void addToStock(ProductStock productStock){
        stockService.addProductStock(productStock);
    }

    public List<ProductStock> getAllProductsStock(){
        return productStockRepository.findAll();
    }
}
