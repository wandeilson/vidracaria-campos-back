package com.vidracariaCampos.service;

import com.vidracariaCampos.model.dto.ProductCreateDTO;
import com.vidracariaCampos.model.dto.ProductResponseDTO;
import com.vidracariaCampos.model.dto.ProductUpdateDTO;
import com.vidracariaCampos.model.entity.Product;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public  class ProductConverter {
    public static  ProductCreateDTO convertToProductCreateDTO(Product product){
        return new ProductCreateDTO(product.getName(),
                product.getUnitOfMeasure(),
                product.getCategory(),
                product.getRegistrationDate()
        );
    }

    public static  ProductUpdateDTO convertToProductUpdateDTO(Product product){
        return new ProductUpdateDTO(product.getName(),
                product.getUnitOfMeasure(),
                product.getCategory(),
                product.getHeight(),
                product.getWidth(),
                product.getDepth(),
                product.getPrice());
    }

    public static ProductResponseDTO convertToProductResponseDTO(Product product){
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

    public static Product convertToProduct(Object productDTO){
        var productEntity = new Product();
        BeanUtils.copyProperties(productDTO, productEntity);
        return productEntity;
    }
}
