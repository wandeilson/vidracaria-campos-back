package com.vidracariaCampos.model.dto;

import com.vidracariaCampos.model.enums.Category;
import com.vidracariaCampos.model.enums.UnitOfMeasure;
import jakarta.validation.constraints.NotNull;

public record ProductPOSTDTO(
        @NotNull String name,
        @NotNull UnitOfMeasure unitOfMeasure,
        @NotNull Category category

){

}
