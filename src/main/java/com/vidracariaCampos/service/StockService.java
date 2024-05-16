package com.vidracariaCampos.service;

import com.vidracariaCampos.model.entity.ProductStock;
import com.vidracariaCampos.model.entity.Stock;
import com.vidracariaCampos.model.entity.TransactionStock;
import com.vidracariaCampos.model.enums.TransactionType;
import com.vidracariaCampos.repository.StockRepositoty;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class StockService {

    private StockRepositoty stockRepositoty;
    private Stock stock = new Stock();

    public StockService(StockRepositoty stockRepositoty) {
        this.stockRepositoty = stockRepositoty;
        stockRepositoty.save(stock);
    }


    public Stock getStock() {
        return this.stock = stockRepositoty.findAll().get(0);
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

    }

    public void addProductStock(ProductStock productStock) {
        getStock().getProductStockList().add(productStock);
        stockRepositoty.save(this.stock);
    }
}
