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
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity<Object> createProduct(@RequestBody @Valid ProductCreateDTO productCreateDTO){
        UUID idUser = AuthenticationController.getUserLogged().getId();
        if(productService.existsByName(productCreateDTO.name(),idUser))
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict: Name is already in use!");

        var productEntity = ProductConverter.convertToProduct(productCreateDTO);
        productEntity.setIdUser(AuthenticationController.getUserLogged().getId());
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.save(productEntity));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateProduct(@PathVariable (value = "id") UUID id, @RequestBody @Valid
    ProductUpdateDTO productUpdateDTO){
        UUID idUser = AuthenticationController.getUserLogged().getId();
        Optional<Product> productOptional;
        if(!productService.existsById(id, idUser)){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found");
        }else {
            productOptional = productService.getById(id, idUser);
            if (productService.existsByName(productUpdateDTO.name(),idUser)){
                if(!productUpdateDTO.name().equals(productOptional.get().getName())){
                    return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict: Name is already in use!");
                }
            }
        }
        Product productEntity = ProductConverter.convertToProduct(productUpdateDTO);
        productEntity.setRegistrationDate(productOptional.get().getRegistrationDate());
        productEntity.setId(productOptional.get().getId());
        productEntity.setIdUser(productOptional.get().getIdUser());
        return  ResponseEntity.ok(productService.update(productEntity));
        
    }


    @GetMapping
    public ResponseEntity<List<ProductResponseDTO>>  getAllProducts(){
        return ResponseEntity.ok(productService.getAllProducts(AuthenticationController.getUserLogged().getId()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object>  getProductById(@PathVariable (value = "id") UUID id){
        if(!productService.existsById(id, AuthenticationController.getUserLogged().getId()))
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found.");
        return ResponseEntity.status(HttpStatus.OK).body(productService.getById(id, AuthenticationController.getUserLogged().getId()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteProductById (@PathVariable (value = "id") UUID id){
        if(!productService.existsById(id, AuthenticationController.getUserLogged().getId()))
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found.");

        productService.deleteProductById(id, AuthenticationController.getUserLogged().getId());
        return ResponseEntity.status(HttpStatus.OK).body("Product deleted successfully.");

    }

}
