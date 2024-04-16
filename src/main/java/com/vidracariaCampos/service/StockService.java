package com.vidracariaCampos.service;
import com.vidracariaCampos.model.entity.Stock;
import com.vidracariaCampos.repository.StockRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class StockService{

    @Autowired
    private StockRepository stockRepository;

    public Stock createStock(Stock stock) {
        return stockRepository.save(stock);
    }

    public Stock updateStock(UUID id, Stock updatedStock) {
      return null;
    }
    public Optional<Stock> getStock(UUID id) {
        return stockRepository.findById(id);
    }

    public List<Stock> getStock() {
        return stockRepository.findAll();
    }

    public void deleteStock(UUID id) {
        stockRepository.deleteById(id);
    }
}
