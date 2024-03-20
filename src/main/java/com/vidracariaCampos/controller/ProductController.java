package com.vidracariaCampos.controller;

import com.vidracariaCampos.model.dto.ProductCreateDTO;
import com.vidracariaCampos.model.dto.ProductResponseDTO;
import com.vidracariaCampos.model.dto.ProductUpdateDTO;
import com.vidracariaCampos.model.entity.Product;
import com.vidracariaCampos.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity<Object> createProduct(@RequestBody @Valid ProductCreateDTO productCreateDTO){
        if(productService.existsByName(productCreateDTO.name()))
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict: Name is already in use!");

        var productEntity = productService.convertToProduct(productCreateDTO);
        productEntity.setRegistrationDate(LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.save(productEntity));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateProduct(@PathVariable (value = "id") UUID id, @RequestBody @Valid
    ProductUpdateDTO productUpdateDTO){
        Product productUpdated;
        if(!productService.existsById(id)){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found");
        }else{
            var productOptional  = productService.getById(id);
            Product productEntity = productOptional.orElseThrow();
            if(productEntity.getName().equals(productUpdateDTO.name())){
                productUpdated = productService.convertToProduct(productUpdateDTO);
                productUpdated.setId(productEntity.getId());
                productUpdated.setRegistrationDate(productEntity.getRegistrationDate());
            } else if (productService.existsByName(productUpdateDTO.name())) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict: Name is already in use!");
            } else {
                productUpdated = productService.convertToProduct(productUpdateDTO);
                productUpdated.setId(productEntity.getId());
                productUpdated.setRegistrationDate(productEntity.getRegistrationDate());
            }
            return ResponseEntity.status(HttpStatus.OK).body(productService.update(productUpdated));
        }

    }


    @GetMapping
    public ResponseEntity<List<ProductResponseDTO>>  getAllProducts(){
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object>  getProductById(@PathVariable (value = "id") UUID id){
        if(!productService.existsById(id))
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Customer not found.");
        return ResponseEntity.status(HttpStatus.OK).body(productService.getById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteProductById (@PathVariable (value = "id") UUID id){
        if(!productService.existsById(id))
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Customer not found.");

        productService.deleteProductById(id);
        return ResponseEntity.status(HttpStatus.OK).body("Customer deleted successfully.");

    }


}
