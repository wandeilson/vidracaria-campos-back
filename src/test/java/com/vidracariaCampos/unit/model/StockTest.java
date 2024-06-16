package com.vidracariaCampos.unit.model;
import com.vidracariaCampos.model.entity.ProductStock;
import com.vidracariaCampos.model.entity.Stock;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class StockTest {

    @Test
    void testStockCreation() {
        UUID id = UUID.randomUUID();
        List<ProductStock> productStockList = new ArrayList<>();

        Stock stock = new Stock();
        stock.setId(id);
        stock.setProductStockList(productStockList);

        assertEquals(id, stock.getId());
        assertNotNull(stock.getProductStockList());
    }

    @Test
    void testStockDefaultConstructor() {
        Stock stock = new Stock();
        assertNotNull(stock);
    }

    @Test
    void testStockEquals() {
        UUID id = UUID.randomUUID();
        List<ProductStock> productStockList1 = new ArrayList<>();
        List<ProductStock> productStockList2 = new ArrayList<>();

        Stock stock1 = new Stock();
        stock1.setId(id);
        stock1.setProductStockList(productStockList1);

        Stock stock2 = new Stock();
        stock2.setId(id);
        stock2.setProductStockList(productStockList2);

        assertEquals(stock1, stock2);
    }

    @Test
    void testStockHashCode() {
        UUID id = UUID.randomUUID();
        List<ProductStock> productStockList1 = new ArrayList<>();
        List<ProductStock> productStockList2 = new ArrayList<>();

        Stock stock1 = new Stock();
        stock1.setId(id);
        stock1.setProductStockList(productStockList1);

        Stock stock2 = new Stock();
        stock2.setId(id);
        stock2.setProductStockList(productStockList2);

        assertEquals(stock1.hashCode(), stock2.hashCode());
    }
}
