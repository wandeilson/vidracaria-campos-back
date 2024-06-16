package com.vidracariaCampos.unit.model;

import com.vidracariaCampos.model.enums.TransactionType;
import com.vidracariaCampos.model.entity.TransactionStock;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class TransactionStockTest {

    @Test
    void testTransactionStockCreation() {
        UUID id = UUID.randomUUID();
        UUID idProduct = UUID.randomUUID();
        TransactionType transactionType = TransactionType.ENTRADA;
        LocalDateTime transactionDate = LocalDateTime.now();
        int movementQuantity = 50;

        TransactionStock transactionStock = new TransactionStock();
        transactionStock.setId(id);
        transactionStock.setIdProduct(idProduct);
        transactionStock.setTransactionType(transactionType);
        transactionStock.setTransactionDate(transactionDate);
        transactionStock.setMovementQuantity(movementQuantity);

        assertEquals(id, transactionStock.getId());
        assertEquals(idProduct, transactionStock.getIdProduct());
        assertEquals(transactionType, transactionStock.getTransactionType());
        assertEquals(transactionDate, transactionStock.getTransactionDate());
        assertEquals(movementQuantity, transactionStock.getMovementQuantity());
    }

    @Test
    void testTransactionStockDefaultConstructor() {
        TransactionStock transactionStock = new TransactionStock();
        assertNotNull(transactionStock);
    }

    @Test
    void testTransactionStockValidation() {
        TransactionStock transactionStock = new TransactionStock();
        transactionStock.setMovementQuantity(101);

        assertEquals(101, transactionStock.getMovementQuantity());
    }
}
