package com.vidracariaCampos.controller;
import com.vidracariaCampos.model.entity.TransactionStock;
import com.vidracariaCampos.service.StockService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/stock")
public class StockController {

    private final StockService stockService;
    public StockController(StockService stockService){
        this.stockService = stockService;
    }

    @PutMapping("/receiveProduct")
    public ResponseEntity<Object> performTransaction(@RequestBody @Valid TransactionStock transactionStock) {
        try {
            return ResponseEntity.ok().body(stockService.performTransaction(transactionStock));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @GetMapping
    public ResponseEntity<Object> getStock(){
        return ResponseEntity.ok(stockService.getStock());
    }

}
