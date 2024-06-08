package com.vidracariaCampos.service;

import com.vidracariaCampos.model.entity.ProductStock;
import com.vidracariaCampos.repository.ProductStockRepository;
import com.vidracariaCampos.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductStockService {

    private ProductStockRepository productStockRepository;
    private StockService stockService;

    @Autowired
    public ProductStockService(ProductStockRepository productStockRepository, StockService stockService, StockRepository stockRepository) {
        this.productStockRepository = productStockRepository;
        this.stockService = stockService;
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
