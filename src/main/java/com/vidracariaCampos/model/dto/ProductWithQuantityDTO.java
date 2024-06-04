package com.vidracariaCampos.model.dto;

import com.vidracariaCampos.model.enums.Category;
import com.vidracariaCampos.model.enums.UnitOfMeasure;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductWithQuantityDTO {
    private UUID id;
    private String name;
    private UnitOfMeasure unitOfMeasure;
    private Category category;
    private float height;
    private float width;
    private float depth;
    private BigDecimal price;
    private int actualQuantity;
}
