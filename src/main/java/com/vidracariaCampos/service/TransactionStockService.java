package com.vidracariaCampos.service;

import com.vidracariaCampos.model.entity.TransactionStock;
import com.vidracariaCampos.repository.TransactionStockRepository;
import org.springframework.stereotype.Service;

@Service
public class TransactionStockService {

    private final TransactionStockRepository transactionStockRepository;

    public TransactionStockService(TransactionStockRepository transactionStockRepository) {
        this.transactionStockRepository = transactionStockRepository;
    }

    public void save(TransactionStock transactionStock){
        transactionStockRepository.save(transactionStock);
    }
}
