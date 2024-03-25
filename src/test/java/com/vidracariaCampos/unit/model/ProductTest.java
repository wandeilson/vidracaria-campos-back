package com.vidracariaCampos.unit.model;
import com.vidracariaCampos.model.entity.Product;
import com.vidracariaCampos.model.enums.Category;
import com.vidracariaCampos.model.enums.UnitOfMeasure;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ProductTest {

    @Test
    void testProductCreation() {
        UUID id = UUID.randomUUID();
        String name = "Test Product";
        float height = 10.5f;
        float width = 5.3f;
        float depth = 2.0f;
        UnitOfMeasure unit = UnitOfMeasure.CENTIMETRO;
        BigDecimal price = new BigDecimal(25.99f);
        Category category = Category.TEMPERADO;

        Product product = new Product();
        product.setId(id);
        product.setName(name);
        product.setHeight(height);
        product.setWidth(width);
        product.setDepth(depth);
        product.setUnitOfMeasure(unit);
        product.setPrice(price);
        product.setCategory(category);

        assertEquals(id, product.getId());
        assertEquals(name, product.getName());
        assertEquals(height, product.getHeight());
        assertEquals(width, product.getWidth());
        assertEquals(depth, product.getDepth());
        assertEquals(unit, product.getUnitOfMeasure());
        assertEquals(price, product.getPrice());
        assertEquals(category, product.getCategory());
    }

    @Test
    void testProductDefaultConstructor() {
        Product product = new Product();
        assertNotNull(product);
    }

//    @Test
//    void testProductToString() {
//        UUID id = UUID.randomUUID();
//        String name = "Test Product";
//        float height = 10.5f;
//        float width = 5.3f;
//        float depth = 2.0f;
//        UnitOfMeasure unit = UnitOfMeasure.CENTIMETRO;
//        BigDecimal price = new BigDecimal(25.99f);
//        Category category = Category.TEMPERADO;
//
//        Product product = new Product();
//        product.setId(id);
//        product.setName(name);
//        product.setHeight(height);
//        product.setWidth(width);
//        product.setDepth(depth);
//        product.setUnitOfMeasure(unit);
//        product.setPrice(price);
//        product.setCategory(category);
//
//        String expectedToString = "Product(id=" + id + ", name=Test Product, height=10.5, width=5.3, depth=2.0, unit=CENTIMETRO, price=25.99, category=TEMPERADO)";
//        assertEquals(expectedToString, product.toString());
//    }

    @Test
    void testProductEquals() {
        UUID id = UUID.randomUUID();
        String name = "Test Product";
        float height = 10.5f;
        float width = 5.3f;
        float depth = 2.0f;
        UnitOfMeasure unit = UnitOfMeasure.CENTIMETRO;
        BigDecimal price = new BigDecimal(25.99f);
        Category category = Category.TEMPERADO;

        Product product1 = new Product();
        product1.setId(id);
        product1.setName(name);
        product1.setHeight(height);
        product1.setWidth(width);
        product1.setDepth(depth);
        product1.setUnitOfMeasure(unit);
        product1.setPrice(price);
        product1.setCategory(category);

        Product product2 = new Product();
        product2.setId(id);
        product2.setName(name);
        product2.setHeight(height);
        product2.setWidth(width);
        product2.setDepth(depth);
        product2.setUnitOfMeasure(unit);
        product2.setPrice(price);
        product2.setCategory(category);

        assertEquals(product1, product2);
    }

    @Test
    void testProductHashCode() {
        UUID id = UUID.randomUUID();
        String name = "Test Product";
        float height = 10.5f;
        float width = 5.3f;
        float depth = 2.0f;
        UnitOfMeasure unit = UnitOfMeasure.CENTIMETRO;
        BigDecimal price = new BigDecimal(25.99f);
        Category category = Category.TEMPERADO;

        Product product1 = new Product();
        product1.setId(id);
        product1.setName(name);
        product1.setHeight(height);
        product1.setWidth(width);
        product1.setDepth(depth);
        product1.setUnitOfMeasure(unit);
        product1.setPrice(price);
        product1.setCategory(category);

        Product product2 = new Product();
        product2.setId(id);
        product2.setName(name);
        product2.setHeight(height);
        product2.setWidth(width);
        product2.setDepth(depth);
        product2.setUnitOfMeasure(unit);
        product2.setPrice(price);
        product2.setCategory(category);

        assertEquals(product1.hashCode(), product2.hashCode());
    }
}
