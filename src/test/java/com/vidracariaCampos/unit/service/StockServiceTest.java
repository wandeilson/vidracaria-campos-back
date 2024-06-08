package com.vidracariaCampos.unit.service;
import com.vidracariaCampos.config.ConfigSpringTest;
import com.vidracariaCampos.model.entity.ProductStock;
import com.vidracariaCampos.model.entity.Stock;
import com.vidracariaCampos.model.entity.TransactionStock;
import com.vidracariaCampos.model.enums.TransactionType;
import com.vidracariaCampos.repository.StockRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import com.vidracariaCampos.service.StockService;
import com.vidracariaCampos.service.TransactionStockService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import java.time.LocalDateTime;
import java.util.UUID;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

public class StockServiceTest implements ConfigSpringTest {

    @Autowired
    private StockRepository stockRepository;

    @Autowired
    private TransactionStockService transactionStockService;

    @Autowired
    private StockService stockService;
    private Stock stock;

    @BeforeEach
    void setUp() {
        stock = new Stock();
        stock.setId(UUID.randomUUID());
        stock.setProductStockList(new ArrayList<>());
    }


    @Test
    void testPerformTransaction() {
        Stock stock = stockService.getStock();

        ProductStock productStock = new ProductStock();
        productStock.setId(UUID.randomUUID());
        productStock.setIdProduct(UUID.randomUUID());
        productStock.setActualQuantity(10);

        stock.getProductStockList().add(productStock);

        stockRepository.save(stock);

        TransactionStock transactionStock = new TransactionStock();
        transactionStock.setId(UUID.randomUUID());
        transactionStock.setIdProduct(productStock.getIdProduct());
        transactionStock.setTransactionType(TransactionType.ENTRADA);
        transactionStock.setMovementQuantity(5);
        transactionStock.setTransactionDate(LocalDateTime.now());

        String status = stockService.performTransaction(transactionStock);

        assertNotNull(status);
        assertEquals("Entry successfully completed.", status);
    }

    @Test
    void testAddProductStock() {
        Stock stock = stockService.getStock();

        ProductStock productStock = new ProductStock();
        productStock.setId(UUID.randomUUID());
        productStock.setIdProduct(UUID.randomUUID());
        productStock.setActualQuantity(10);

        stockService.addProductStock(productStock);

        Stock updatedStock = stockService.getStock();

        assertNotNull(updatedStock);
        assertEquals(1, updatedStock.getProductStockList().size());
    }
    @Test
    void testPerformTransaction_ProductNotFound() {
        TransactionStock transactionStock = new TransactionStock();
        transactionStock.setId(UUID.randomUUID());
        transactionStock.setIdProduct(null);
        transactionStock.setTransactionType(TransactionType.ENTRADA);
        transactionStock.setMovementQuantity(3);

        Assertions.assertNotEquals("Entry successfully completed.", stockService.performTransaction(transactionStock));
    }

    @Test
    void testPerformTransaction_InvalidTransactionType() {
        ProductStock productStock = new ProductStock();
        productStock.setId(UUID.randomUUID());
        productStock.setIdProduct(UUID.randomUUID());
        productStock.setActualQuantity(5);
        stock.getProductStockList().add(productStock);

        TransactionStock transactionStock = new TransactionStock();
        transactionStock.setId(UUID.randomUUID());
        transactionStock.setIdProduct(productStock.getIdProduct());
        transactionStock.setTransactionType(null);
        transactionStock.setMovementQuantity(3);

        Assertions.assertNotEquals("Entry successfully completed.", stockService.performTransaction(transactionStock));
    }

}
