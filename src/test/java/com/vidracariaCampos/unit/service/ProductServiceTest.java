package com.vidracariaCampos.unit.service;
import com.vidracariaCampos.config.ConfigSpringTest;
import com.vidracariaCampos.model.dto.ProductCreateDTO;
import com.vidracariaCampos.model.dto.ProductResponseDTO;
import com.vidracariaCampos.model.dto.ProductUpdateDTO;
import com.vidracariaCampos.model.entity.Product;
import com.vidracariaCampos.model.entity.User;
import com.vidracariaCampos.model.enums.Category;
import com.vidracariaCampos.model.enums.Role;
import com.vidracariaCampos.model.enums.UnitOfMeasure;
import com.vidracariaCampos.repository.ProductRepository;
import com.vidracariaCampos.repository.UserRepository;
import com.vidracariaCampos.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class ProductServiceTest implements ConfigSpringTest {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductService productService;
    @Autowired
    private UserRepository userRepository;
    private User user;
    private ProductCreateDTO productCreateDTO;

    @BeforeEach
    void setUp() {
        productRepository.deleteAll();

        String email = "test@example.com";
        user = new User(null, "Test User", "test@example.com", Role.ADMIN, "ADMIN");
        user.setEmail(email);

        user = (User) userRepository.save(user);

        var authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        productCreateDTO = createDTO();
    }
    @Test
    @Order(1)
    void testSaveProduct_Success() throws Exception {
        ProductResponseDTO savedProductDTO = productService.save(productCreateDTO);

        assertNotNull(savedProductDTO.name());
        assertEquals(productCreateDTO.name(), savedProductDTO.name());
        assertNotNull(savedProductDTO.registrationDate());
    }
    @Test
    @Order(2)
    void testUpdateProduct_Success() throws Exception {

        ProductResponseDTO savedProductDTO = productService.save(productCreateDTO);

        ProductUpdateDTO productUpdateDTO = new ProductUpdateDTO(
                "Updated Test Product",
                UnitOfMeasure.METRO,
                Category.TEMPERADO,
                20.0f,
                15.0f,
                10.0f,
                BigDecimal.valueOf(50.0)
        );

        ProductResponseDTO updatedProductDTO = productService.update(productUpdateDTO, savedProductDTO.id());

        assertEquals(productUpdateDTO.name(), updatedProductDTO.name());
        assertEquals(productUpdateDTO.unitOfMeasure(), updatedProductDTO.unitOfMeasure());
        assertEquals(productUpdateDTO.category(), updatedProductDTO.category());
    }
    @Test
    @Order(3)
    void testGetAllProducts_Success() throws Exception {

        productService.save(new ProductCreateDTO("Product 1", UnitOfMeasure.METRO, Category.TEMPERADO, LocalDateTime.now()));
        productService.save(new ProductCreateDTO("Product 2", UnitOfMeasure.MILIMETRO, Category.COMUM, LocalDateTime.now()));

        List<ProductResponseDTO> productList = productService.getAllProducts();
        assertEquals(2, productList.size());
    }
    @Test
    @Order(4)
    void testGetById_Success() throws Exception {
        ProductResponseDTO savedProductDTO = productService.save(productCreateDTO);

        Product retrievedProductDTO = productService.getById(savedProductDTO.id());

        assertEquals(productCreateDTO.name(), retrievedProductDTO.getName());
        assertEquals(productCreateDTO.unitOfMeasure(), retrievedProductDTO.getUnitOfMeasure());
        assertEquals(productCreateDTO.category(), retrievedProductDTO.getCategory());
    }
    @Test
    void testDeleteProductById_Success() throws Exception {
        ProductResponseDTO savedProductDTO = productService.save(productCreateDTO);
        productService.deleteProductById(savedProductDTO.id());
        assertThrows(Exception.class, () -> productService.getById(savedProductDTO.id()));
    }
    @Test
    @Order(5)
    void testSaveProduct_NameAlreadyExists()throws Exception{
        ProductResponseDTO savedProductDTO = productService.save(productCreateDTO);
        assertThrows(Exception.class, () ->
                productService.save(
                        new ProductCreateDTO(
                                "Test Product",
                               null,
                                null,
                                LocalDateTime.now()
                )));
    }

    @Test
    @Order(6)
    void testUpdateProduct_NameAlreadyExists() throws Exception {
        ProductResponseDTO savedProductDTO = productService.save(productCreateDTO);

        ProductCreateDTO secondProductDTO = new ProductCreateDTO(
                "Second Test Product",
                UnitOfMeasure.MILIMETRO,
                Category.TEMPERADO,
                LocalDateTime.now()
        );

        ProductResponseDTO secondSavedProductDTO = productService.save(secondProductDTO);

        ProductUpdateDTO updateDTO = new ProductUpdateDTO(
                savedProductDTO.name(),
                UnitOfMeasure.MILIMETRO,
                Category.TEMPERADO,
                20.0f,
                15.0f,
                10.0f,
                BigDecimal.valueOf(50.0)
        );

        assertThrows(Exception.class, () -> productService.update(updateDTO, secondSavedProductDTO.id()));
    }

    @Test
    @Order(7)
    void testGetById_ProductNotFound() {
        UUID nonExistentId = UUID.randomUUID();
        assertThrows(Exception.class, () -> productService.getById(nonExistentId));
    }

    @Test
    @Order(8)
    void testDeleteProductById_ProductNotFound() {
        UUID nonExistentId = UUID.randomUUID();
        assertThrows(Exception.class, () -> productService.deleteProductById(nonExistentId));
    }
    public ProductCreateDTO createDTO(){
        return  new ProductCreateDTO(
                "Test Product",
                UnitOfMeasure.CENTIMETRO,
                Category.TEMPERADO,
                LocalDateTime.now()
        );
    }

}
