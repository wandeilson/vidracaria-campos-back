package com.vidracariaCampos.service;

import com.vidracariaCampos.model.dto.ProductPOSTDTO;
import com.vidracariaCampos.model.dto.ProductPUTDTO;
import com.vidracariaCampos.model.entity.Product;
import com.vidracariaCampos.model.repository.ProductRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    public ProductPOSTDTO save(Product productEntity) {
      return convertToProductDTO(productRepository.save(productEntity));
    }

    public ProductPUTDTO update(Product productEntity) {
        return convertToProductPUTDTO(productRepository.save(productEntity));
    }

    public ProductPOSTDTO convertToProductDTO(Product product){
        return new ProductPOSTDTO(product.getName(),
                product.getUnitOfMeasure(),
                product.getCategory());
    }

    public ProductPUTDTO convertToProductPUTDTO(Product product){
        return new ProductPUTDTO(product.getName(),
                product.getUnitOfMeasure(),
                product.getCategory(),
                product.getHeight(),
                product.getWidth(),
                product.getDepth(),
                product.getPrice());
    }

    public Product convertToProduct(Object productPOSTDTO){
       var productEntity = new Product();
       BeanUtils.copyProperties(productPOSTDTO, productEntity);
        return productEntity;
    }

    public boolean existsByName(String name){
        return productRepository.existsByName(name);
    }

    public boolean existsById(UUID id) {
        return productRepository.existsById(id);
    }
}
