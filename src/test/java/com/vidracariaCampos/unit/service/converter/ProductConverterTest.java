package com.vidracariaCampos.unit.service.converter;
import com.vidracariaCampos.model.dto.*;
import com.vidracariaCampos.model.entity.Product;
import com.vidracariaCampos.model.entity.ProductStock;
import com.vidracariaCampos.model.enums.Category;
import com.vidracariaCampos.model.enums.UnitOfMeasure;
import com.vidracariaCampos.service.converter.ProductConverter;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ProductConverterTest {

    @Test
    void convertToProductCreateDTO() {

        Product product = new Product();
        product.setName("Test Product");
        product.setUnitOfMeasure(UnitOfMeasure.CENTIMETRO);
        product.setCategory(Category.TEMPERADO);

        ProductCreateDTO productCreateDTO = ProductConverter.convertToProductCreateDTO(product);

        assertNotNull(productCreateDTO);
        assertEquals("Test Product", productCreateDTO.name());
        assertEquals(UnitOfMeasure.CENTIMETRO, productCreateDTO.unitOfMeasure());
        assertEquals(Category.TEMPERADO, productCreateDTO.category());
    }

    @Test
    void convertToProductUpdateDTO() {
        Product product = new Product();
        product.setName("Test Product");
        product.setUnitOfMeasure(UnitOfMeasure.CENTIMETRO);
        product.setCategory(Category.TEMPERADO);
        product.setHeight(10);
        product.setWidth(5);
        product.setDepth(3);
        product.setPrice(BigDecimal.TEN);

        ProductUpdateDTO productUpdateDTO = ProductConverter.convertToProductUpdateDTO(product);

        assertNotNull(productUpdateDTO);
        assertEquals("Test Product", productUpdateDTO.name());
        assertEquals(UnitOfMeasure.CENTIMETRO, productUpdateDTO.unitOfMeasure());
        assertEquals(Category.TEMPERADO, productUpdateDTO.category());
        assertEquals(10, productUpdateDTO.height());
        assertEquals(5, productUpdateDTO.width());
        assertEquals(3, productUpdateDTO.depth());
        assertEquals(BigDecimal.TEN, productUpdateDTO.price());
    }


    @Test
    void setActualQuantity() {
        // Arrange
        List<ProductWithQuantityDTO> productList = new ArrayList<>();
        productList.add(new ProductWithQuantityDTO(UUID.randomUUID(), "Product 1", UnitOfMeasure.CENTIMETRO, Category.TEMPERADO, 10, 5, 3, BigDecimal.TEN, 20));
        productList.add(new ProductWithQuantityDTO(UUID.randomUUID(), "Product 2", UnitOfMeasure.MILIMETRO, Category.COMUM, 15, 7, 4, BigDecimal.TEN, 30));

        List<ProductStock> stockList = new ArrayList<>();
        stockList.add(new ProductStock());
        stockList.add(new ProductStock());

        List<ProductWithQuantityDTO> updatedProductList = ProductConverter.setActualQuantity(productList, stockList);

        assertNotNull(updatedProductList);
        assertEquals(20, updatedProductList.get(0).getActualQuantity());
        assertEquals(30, updatedProductList.get(1).getActualQuantity());
    }

}
