package com.vidracariaCampos.service;

import com.vidracariaCampos.model.dto.ProductCreateDTO;
import com.vidracariaCampos.model.dto.ProductResponseDTO;
import com.vidracariaCampos.model.dto.ProductUpdateDTO;
import com.vidracariaCampos.model.entity.Product;
import com.vidracariaCampos.repository.ProductRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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
        productEntity.setRegistrationDate(LocalDateTime.now());
      return ProductConverter.convertToProductCreateDTO(productRepository.save(productEntity));
    }

    public ProductResponseDTO update(Product productEntity) {
        return ProductConverter.convertToProductResponseDTO(productRepository.save(productEntity));
    }



    public boolean existsByName(String name, UUID idUser){
        return productRepository.existsByNameAndIdUser(name, idUser);
    }

    public boolean existsById(UUID id, UUID idUser) {
        return productRepository.existsByIdAndIdUser(id, idUser);
    }

    public List<ProductResponseDTO> getAllProducts(UUID idUser) {
        var listAllProducts = productRepository.findProductsByUserId(idUser);
        List<ProductResponseDTO> listAllProductsResponseDTO = new ArrayList<>();
        for (Product product: listAllProducts){
            listAllProductsResponseDTO.add(ProductConverter.convertToProductResponseDTO(product));
        }
        return listAllProductsResponseDTO;
    }

    public Optional<Product> getById(UUID id, UUID idUser) {
       return productRepository.findByIdAndIdUser(id, idUser);
    }

    public void deleteProductById(UUID id, UUID idUser) {
        productRepository.deleteByIdAndIdUser(id, idUser);

    }

}
