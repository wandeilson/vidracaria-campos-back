package com.vidracariaCampos.controller;
import com.vidracariaCampos.model.dto.ProductCreateDTO;
import com.vidracariaCampos.model.dto.ProductUpdateDTO;
import com.vidracariaCampos.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<Object> createProduct(@RequestBody @Valid ProductCreateDTO productCreateDTO){
        return ResponseEntity.created(null).body(productService.save(productCreateDTO));
    }
    @PutMapping("/{id}")
    public ResponseEntity<Object> updateProduct(@PathVariable (value = "id") UUID id, @RequestBody @Valid
    ProductUpdateDTO productUpdateDTO){
        return ResponseEntity.ok().body(productService.update(productUpdateDTO,id));
    }
    @GetMapping
    public ResponseEntity<Object> getAllProducts(   @RequestParam(value = "page", defaultValue = "0") int page,
                                                    @RequestParam(value = "size", defaultValue = "15") int size){
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(productService.getAllProducts(pageable));
    }
    @GetMapping("/productsWithQuantity")
    public ResponseEntity<Object> getAllProductsWithQuantity(){
        return ResponseEntity.ok(productService.getAllProductsWithQuantity());
    }
    @GetMapping("/productsOnlyWithName")
    public ResponseEntity<Object> getAllProductsOnlyWithName(){
        return ResponseEntity.ok(productService.getAllProductsOnlyWithName());
    }
    @GetMapping("/{id}")
    public ResponseEntity<Object> getProductById(@PathVariable (value = "id") UUID id){
        return ResponseEntity.ok(productService.getById(id));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteProductById (@PathVariable (value = "id") UUID id){
        productService.deleteProductById(id);
        return ResponseEntity.ok("");
    }
}
