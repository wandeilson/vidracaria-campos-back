package com.vidracariaCampos.controller;

import com.vidracariaCampos.model.dto.ProductCreateDTO;
import com.vidracariaCampos.model.dto.ProductResponseDTO;
import com.vidracariaCampos.model.dto.ProductUpdateDTO;
import com.vidracariaCampos.model.entity.Product;
import com.vidracariaCampos.service.ProductConverter;
import com.vidracariaCampos.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity createProduct(@RequestBody @Valid ProductCreateDTO productCreateDTO){
        try {
            return ResponseEntity.created(null).body(productService.save(productCreateDTO));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateProduct(@PathVariable (value = "id") UUID id, @RequestBody @Valid
    ProductUpdateDTO productUpdateDTO){
        try {
            return ResponseEntity.ok().body(productService.update(productUpdateDTO,id));
        }catch (Exception e){

            if(e.getMessage().equals("Product not found"))
                return ResponseEntity.notFound().build();

            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<Object> getAllProducts(){
        try {
            return ResponseEntity.ok(productService.getAllProducts());
        }catch (Exception e){
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @GetMapping("/productsWithQuantity")
    public ResponseEntity<Object> getAllProductsWithQuantity(){
        try {
            return ResponseEntity.ok(productService.getAllProductsWithQuantity());
        }catch (Exception e){
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }
    @GetMapping("/productsOnlyWithName")
    public ResponseEntity<Object> getAllProductsOnlyWithName(){
        try {
            return ResponseEntity.ok(productService.getAllProductsOnlyWithName());
        }catch (Exception e){
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getProductById(@PathVariable (value = "id") UUID id){
        try {
            return ResponseEntity.ok(productService.getById(id));
        }catch (Exception e){

            if(e.getMessage().equals("Product not found"))
                return ResponseEntity.notFound().build();

            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteProductById (@PathVariable (value = "id") UUID id){
        try {
            productService.deleteProductById(id);
            return ResponseEntity.ok("");
        }catch (Exception e){

            if(e.getMessage().equals("Product not found"))
                return ResponseEntity.notFound().build();

            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

}
