package com.vidracariaCampos.service;

import com.vidracariaCampos.model.dto.*;
import com.vidracariaCampos.model.entity.Product;
import com.vidracariaCampos.model.entity.ProductStock;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.List;

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

    public static ProductWithQuantityDTO convertToProductWithQuantityDTO (Product product){
        return new ProductWithQuantityDTO(product.getId(), product.getName(), product.getUnitOfMeasure(),
                product.getCategory(), product.getHeight(), product.getWidth(), product.getDepth(),
                product.getPrice(),0);
    }

    public static List<ProductWithQuantityDTO> setActualQuantity(List<ProductWithQuantityDTO> listProductWithQuantityDTO,
                                                           List<ProductStock> listProductStock){
        for(ProductWithQuantityDTO prodQuantity: listProductWithQuantityDTO){
            for(ProductStock productStock: listProductStock){
                if(prodQuantity.getId().equals(productStock.getIdProduct())){
                    prodQuantity.setActualQuantity(productStock.getActualQuantity());
                }
            }
        }
        return listProductWithQuantityDTO;
    }

    public static ProductOnlyWithNameDTO convertToProductOnlyWithName(ProductResponseDTO productResponseDTO){
        return new ProductOnlyWithNameDTO(productResponseDTO.id(), productResponseDTO.name());
    }

}
