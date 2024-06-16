package com.vidracariaCampos.unit.model;
import com.vidracariaCampos.model.entity.ProductStock;
import com.vidracariaCampos.model.entity.Stock;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ProductStockTest {

    @Test
    void testProductStockCreation() {
        UUID id = UUID.randomUUID();
        UUID stockId = UUID.randomUUID();
        UUID productId = UUID.randomUUID();
        int actualQuantity = 10;
        int totalEntries = 5;
        int totalExits = 3;

        ProductStock productStock = new ProductStock();
        productStock.setId(id);

        Stock stock = new Stock();
        stock.setId(stockId);
        productStock.setStock(stock);

        productStock.setIdProduct(productId);
        productStock.setActualQuantity(actualQuantity);
        productStock.setTotalEntries(totalEntries);
        productStock.setTotalExits(totalExits);

        assertEquals(id, productStock.getId());
        assertEquals(stockId, productStock.getStock().getId());
        assertEquals(productId, productStock.getIdProduct());
        assertEquals(actualQuantity, productStock.getActualQuantity());
        assertEquals(totalEntries, productStock.getTotalEntries());
        assertEquals(totalExits, productStock.getTotalExits());
    }

    @Test
    void testProductStockDefaultConstructor() {
        ProductStock productStock = new ProductStock();
        assertNotNull(productStock);
    }

    @Test
    void testProductStockEquals() {
        UUID id = UUID.randomUUID();
        UUID stockId = UUID.randomUUID();
        UUID productId = UUID.randomUUID();
        int actualQuantity = 10;
        int totalEntries = 5;
        int totalExits = 3;

        ProductStock productStock1 = new ProductStock();
        productStock1.setId(id);

        Stock stock = new Stock();
        stock.setId(stockId);
        productStock1.setStock(stock);

        productStock1.setIdProduct(productId);
        productStock1.setActualQuantity(actualQuantity);
        productStock1.setTotalEntries(totalEntries);
        productStock1.setTotalExits(totalExits);

        ProductStock productStock2 = new ProductStock();
        productStock2.setId(id);

        Stock stock2 = new Stock();
        stock2.setId(stockId);
        productStock2.setStock(stock2);

        productStock2.setIdProduct(productId);
        productStock2.setActualQuantity(actualQuantity);
        productStock2.setTotalEntries(totalEntries);
        productStock2.setTotalExits(totalExits);

        assertEquals(productStock1, productStock2);
    }

    @Test
    void testProductStockHashCode() {
        UUID id = UUID.randomUUID();
        UUID stockId = UUID.randomUUID();
        UUID productId = UUID.randomUUID();
        int actualQuantity = 10;
        int totalEntries = 5;
        int totalExits = 3;

        ProductStock productStock1 = new ProductStock();
        productStock1.setId(id);

        Stock stock = new Stock();
        stock.setId(stockId);
        productStock1.setStock(stock);

        productStock1.setIdProduct(productId);
        productStock1.setActualQuantity(actualQuantity);
        productStock1.setTotalEntries(totalEntries);
        productStock1.setTotalExits(totalExits);

        ProductStock productStock2 = new ProductStock();
        productStock2.setId(id);

        Stock stock2 = new Stock();
        stock2.setId(stockId);
        productStock2.setStock(stock2);

        productStock2.setIdProduct(productId);
        productStock2.setActualQuantity(actualQuantity);
        productStock2.setTotalEntries(totalEntries);
        productStock2.setTotalExits(totalExits);

        assertEquals(productStock1.hashCode(), productStock2.hashCode());
    }
}
