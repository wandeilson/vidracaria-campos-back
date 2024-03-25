package com.vidracariaCampos.service;

import com.vidracariaCampos.model.dto.ProductCreateDTO;
import com.vidracariaCampos.model.dto.ProductResponseDTO;
import com.vidracariaCampos.model.dto.ProductUpdateDTO;
import com.vidracariaCampos.model.entity.Product;
import com.vidracariaCampos.model.repository.ProductRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    public ProductCreateDTO save(Product productEntity) {
      return convertToProductCreateDTO(productRepository.save(productEntity));
    }

    public ProductResponseDTO update(Product productEntity) {
        return convertToProductResponseDTO(productRepository.save(productEntity));
    }

    public ProductCreateDTO convertToProductCreateDTO(Product product){
        return new ProductCreateDTO(product.getName(),
                product.getUnitOfMeasure(),
                product.getCategory());
    }

    public ProductUpdateDTO convertToProductUpdateDTO(Product product){
        return new ProductUpdateDTO(product.getName(),
                product.getUnitOfMeasure(),
                product.getCategory(),
                product.getHeight(),
                product.getWidth(),
                product.getDepth(),
                product.getPrice());
    }

    public ProductResponseDTO convertToProductResponseDTO(Product product){
        return new ProductResponseDTO(
                product.getId(),
                product.getName(),
                product.getUnitOfMeasure(),
                product.getCategory(),
                product.getHeight(),
                product.getWidth(),
                product.getDepth(),
                product.getPrice(),
                product.getRegistrationDate()
        );
    }

    public Product convertToProduct(Object productDTO){
       var productEntity = new Product();
       BeanUtils.copyProperties(productDTO, productEntity);
        return productEntity;
    }

    public boolean existsByName(String name){
        return productRepository.existsByName(name);
    }

    public boolean existsById(UUID id, UUID idUser) {
        return productRepository.existsByIdAndIdUser(id, idUser);
    }

    public List<ProductResponseDTO> getAllProducts(UUID idUser) {
        var listAllProducts = productRepository.findProductsByUserId(idUser);
        List<ProductResponseDTO> listAllProductsResponseDTO = new ArrayList<>();
        for (Product product: listAllProducts){
            listAllProductsResponseDTO.add(convertToProductResponseDTO(product));
        }
        return listAllProductsResponseDTO;
    }

    public Optional<Product> getById(UUID id) {
       return productRepository.findById(id);
    }

    public void deleteProductById(UUID id, UUID idUser) {
        productRepository.deleteByIdAndIdUser(id, idUser);

    }

}
