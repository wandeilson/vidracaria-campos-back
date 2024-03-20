package com.vidracariaCampos.model.dto;

import com.vidracariaCampos.model.enums.Category;
import com.vidracariaCampos.model.enums.UnitOfMeasure;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record ProductResponseDTO(
        String name,
        UnitOfMeasure unitOfMeasure,
        Category category,
        float height,
        float width,
        float depth,
        BigDecimal price,
        LocalDateTime registrationDate
) {
}
