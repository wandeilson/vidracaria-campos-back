package com.vidracariaCampos.service;
import com.vidracariaCampos.model.entity.StockTransation;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TransationService{

    @Autowired
    private StockService stockService;

    public StockTransation createStockTransation(StockTransation stockTransation) {
        return null;
    }

    public Optional<StockTransation> getStockTransation(UUID id) {
        return null;
    }

    public List<StockTransation> getStockTransation() {
        return null;
    }

    public void deleteStockTransation(UUID id) {

    }
}
