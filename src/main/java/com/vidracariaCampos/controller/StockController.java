package com.vidracariaCampos.controller;

import com.vidracariaCampos.model.entity.Stock;
import com.vidracariaCampos.model.entity.TransactionStock;
import com.vidracariaCampos.service.StockService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.geom.GeneralPath;

@RestController
@RequestMapping("/stock")
public class StockController {

    private final StockService stockService;
    public StockController(StockService stockService){
        this.stockService = stockService;
    }

    @PutMapping("/receiveProduct")
    public ResponseEntity<Object> performTransaction(@RequestBody @Valid TransactionStock transactionStock) {
        stockService.performTransaction(transactionStock);
        return null;
    }
    @GetMapping
    public ResponseEntity<Object> getStock(){
        return ResponseEntity.ok(stockService.getStock());
    }

}
