package com.vidracariaCampos.service;

import com.vidracariaCampos.model.dto.*;
import com.vidracariaCampos.model.entity.Product;
import com.vidracariaCampos.model.entity.ProductStock;
import com.vidracariaCampos.repository.ProductRepository;
import com.vidracariaCampos.security.UserTolls;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductStockService productStockService;

    public ProductService(ProductRepository productRepository, ProductStockService productStockService ) {
        this.productRepository = productRepository;
        this.productStockService = productStockService;
    }

    public ProductResponseDTO save(ProductCreateDTO productEntity) throws Exception {
        verifyName(productEntity.name());
        Product product = ProductConverter.convertToProduct(productEntity);
        product.setRegistrationDate(LocalDateTime.now());
        product.setIdUser(UserTolls.getUserContextId());

        ProductResponseDTO productResponseDTO = ProductConverter.convertToProductResponseDTO(productRepository.save(product));

        ProductStock productStock = new ProductStock();
        productStock.setIdProduct(productResponseDTO.id());
        productStockService.create(productStock);
        return productResponseDTO;
    }

    public ProductResponseDTO update(ProductUpdateDTO productEntity, UUID id) throws Exception {
        Product product = getById(id);

        if(!product.getName().equals(productEntity.name()))
            verifyName(productEntity.name());

        BeanUtils.copyProperties(productEntity,product);
        return ProductConverter.convertToProductResponseDTO(productRepository.save(product));
    }

    public List<ProductResponseDTO> getAllProducts(){
        var listAllProducts = productRepository.findProductsByUserId(UserTolls.getUserContextId());
        List<ProductResponseDTO> listAllProductsResponseDTO = new ArrayList<>();
        for (Product product: listAllProducts){
            ProductResponseDTO productResponseDTO = ProductConverter.convertToProductResponseDTO(product);
            listAllProductsResponseDTO.add(productResponseDTO);
        }
        return listAllProductsResponseDTO;
    }

    public List<ProductWithQuantityDTO> getAllProductsWithQuantity(){
        List<Product> listProducts = productRepository.findAll();
        List<ProductStock> listProductsStock = productStockService.getAllProductsStock();
        List<ProductWithQuantityDTO> listProductWithQuantityDTO = new ArrayList<ProductWithQuantityDTO>();

        for (Product product: listProducts){
            listProductWithQuantityDTO.add(ProductConverter.convertToProductWithQuantityDTO(product));
        }
        return ProductConverter.setActualQuantity(listProductWithQuantityDTO, listProductsStock);
    }

    public List<ProductOnlyWithNameDTO> getAllProductsOnlyWithName(){
        List<ProductResponseDTO> listProductResponseDTO = getAllProducts();
        List<ProductOnlyWithNameDTO> listProductOnlyWithNameDTO = new ArrayList<>();
        for (ProductResponseDTO product: listProductResponseDTO){
            listProductOnlyWithNameDTO.add(ProductConverter.convertToProductOnlyWithName(product));
        }
        return listProductOnlyWithNameDTO;
    }

    public Product getById(UUID id) throws Exception{
        return productRepository.findByIdAndIdUser(id, UserTolls.getUserContextId())
                .orElseThrow(() -> new Exception("Product not found"));
    }

    public void deleteProductById(UUID id) throws Exception{
        getById(id);
        productRepository.deleteByIdAndIdUser(id, UserTolls.getUserContextId());

    }

    public void verifyName(String name) throws Exception {
        if (productRepository.existsByNameAndIdUser(name, UserTolls.getUserContextId())) {
            throw new Exception("Conflict: Name is already in use!");
        }
    }

}
