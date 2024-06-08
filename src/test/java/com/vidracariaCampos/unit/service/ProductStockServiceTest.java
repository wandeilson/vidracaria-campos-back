package com.vidracariaCampos.unit.service;
import com.vidracariaCampos.config.ConfigSpringTest;
import com.vidracariaCampos.model.entity.ProductStock;
import com.vidracariaCampos.model.entity.Stock;
import com.vidracariaCampos.model.entity.User;
import com.vidracariaCampos.model.enums.Role;
import com.vidracariaCampos.repository.ProductRepository;
import com.vidracariaCampos.repository.ProductStockRepository;
import com.vidracariaCampos.repository.UserRepository;
import com.vidracariaCampos.service.ProductStockService;
import com.vidracariaCampos.service.StockService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

public class ProductStockServiceTest implements ConfigSpringTest {

    @Autowired
    private ProductStockRepository productStockRepository;
    @Autowired
    private StockService stockService;
    @Autowired
    private ProductStockService productStockService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProductRepository productRepository;
    private User user;


    @BeforeEach
    void setUp() {
        productRepository.deleteAll();

        String email = "test@example.com";
        user = new User(null, "Test User", "test@example.com", Role.ADMIN, "ADMIN");
        user.setEmail(email);

        user = (User) userRepository.save(user);

        var authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    @Test
    void testCreateProductStock() {
        ProductStock productStock = new ProductStock();


        Stock stock = stockService.getStock();
        assertNotNull(stock);

        productStock.setStock(stock);

        productStockService.create(productStock);

        ProductStock savedProductStock = productStockRepository.findById(productStock.getId()).orElse(null);
        assertNotNull(savedProductStock);
        assertEquals(stock.getId(), savedProductStock.getStock().getId());
    }


    @Test
    void testAddToStock() {
        ProductStock productStock = new ProductStock();
        productStock.setId(UUID.randomUUID());

        productStockService.addToStock(productStock);

        ProductStock savedProductStock = productStockRepository.findById(productStock.getId()).orElse(null);
        assertNotNull(savedProductStock);
    }

    @Test
    void testGetAllProductsStock() {
        List<ProductStock> productStockList = List.of(new ProductStock(), new ProductStock());

        productStockRepository.saveAll(productStockList);

        List<ProductStock> retrievedProductStockList = productStockService.getAllProductsStock();

        assertEquals(productStockList.size(), retrievedProductStockList.size());
    }

}
