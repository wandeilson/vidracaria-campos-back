package com.vidracariaCampos.controller;

import com.vidracariaCampos.model.dto.ProductPOSTDTO;
import com.vidracariaCampos.model.dto.ProductPUTDTO;
import com.vidracariaCampos.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.UUID;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity<Object> createProduct(@RequestBody @Valid ProductPOSTDTO productPOSTDTO){
        if(productService.existsByName(productPOSTDTO.name()))
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict: Name is already in use!");

        var productEntity = productService.convertToProduct(productPOSTDTO);
        productEntity.setRegistrationDate(LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.save(productEntity));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateProduct(@PathVariable (value = "id") UUID id, @RequestBody @Valid
    ProductPUTDTO productPUTDTO){
        if(productService.existsByName(productPUTDTO.name()))
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict: Name is already in use!");
        if(!productService.existsById(id)){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found");
        }
        var productEntity = productService.convertToProduct(productPUTDTO);
        productEntity.setId(id);
        return ResponseEntity.status(HttpStatus.OK).body(productService.update(productEntity));

    }
}
