package com.vidracariaCampos.service;

import com.vidracariaCampos.model.entity.ProductStock;
import com.vidracariaCampos.model.entity.Stock;
import com.vidracariaCampos.model.entity.TransactionStock;
import com.vidracariaCampos.model.enums.TransactionType;
import com.vidracariaCampos.repository.StockRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class StockService {

    private final StockRepository stockRepository;
    private Stock stock;

    public StockService(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
        if(stockRepository.findAll().isEmpty()) {
            stock = new Stock();
            stockRepository.save(stock);
        }
    }


    public Stock getStock() {
        return this.stock = stockRepository.findAll().get(0);
    }

    public void performTransaction(TransactionStock transactionStock) {
        List<ProductStock> productStockList = getStock().getProductStockList();
        for(ProductStock productStock: productStockList){
            if(productStock.getIdProduct().equals(transactionStock.getIdProduct())){
                TransactionType transactionType = transactionStock.getTransactionType();
                switch (transactionType){
                    case ENTRADA:
                        int quantity = transactionStock.getMovementQuantity();
                        productStock.setActualQuantity(productStock.getActualQuantity() + quantity);
                        break;
                    case SAIDA:
                        break;
                    case BAIXAESTOQUE:
                        break;

                }
                transactionStock.setTransactionDate(LocalDateTime.now());
            }
        }
        stockRepository.save(this.stock);

    }

    public void addProductStock(ProductStock productStock) {
        getStock().getProductStockList().add(productStock);
        stockRepository.save(this.stock);
    }

}
