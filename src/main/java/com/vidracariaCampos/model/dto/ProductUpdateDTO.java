package com.vidracariaCampos.model.dto;

import com.vidracariaCampos.model.enums.Category;
import com.vidracariaCampos.model.enums.UnitOfMeasure;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record ProductUpdateDTO(
        @NotNull String name,
        @NotNull UnitOfMeasure unitOfMeasure,
        @NotNull Category category,
        float height,
        float width,
        float depth,
        BigDecimal price
) {
}
