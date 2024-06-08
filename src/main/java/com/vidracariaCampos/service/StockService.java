package com.vidracariaCampos.service;

import com.vidracariaCampos.model.entity.ProductStock;
import com.vidracariaCampos.model.entity.Stock;
import com.vidracariaCampos.model.entity.TransactionStock;
import com.vidracariaCampos.model.enums.TransactionType;
import com.vidracariaCampos.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class StockService {

    private StockRepository stockRepository;
    private TransactionStockService transactionStockService;
    private Stock stock;

    @Autowired
    public StockService(StockRepository stockRepository,  TransactionStockService transactionStockService) {
        this.stockRepository = stockRepository;
        this.transactionStockService = transactionStockService;
        if(stockRepository.findAll().isEmpty()) {
            stock = new Stock();
            stockRepository.save(stock);
        }
    }


    public Stock getStock() {
        return this.stock = stockRepository.findAll().get(0);
    }

    public String performTransaction(TransactionStock transactionStock) {
        String status = null;
        List<ProductStock> productStockList = getStock().getProductStockList();
        for(ProductStock productStock: productStockList){
            if(productStock.getIdProduct().equals(transactionStock.getIdProduct())){
                TransactionType transactionType = transactionStock.getTransactionType();
                switch (transactionType){
                    case ENTRADA:
                        int quantity = transactionStock.getMovementQuantity();
                        productStock.setActualQuantity(productStock.getActualQuantity() + quantity);
                        status = "Entry successfully completed.";
                        break;
                    case SAIDA:
                        break;
                    case BAIXAESTOQUE:
                        break;

                }
                transactionStock.setTransactionDate(LocalDateTime.now());
                transactionStockService.save(transactionStock);
            }
        }
        stockRepository.save(this.stock);
        return status;

    }

    public void addProductStock(ProductStock productStock) {
        getStock().getProductStockList().add(productStock);
        stockRepository.save(this.stock);
    }

}
