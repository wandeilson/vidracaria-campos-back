package com.vidracariaCampos.model.dto;

import com.vidracariaCampos.model.enums.Category;
import com.vidracariaCampos.model.enums.UnitOfMeasure;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record ProductCreateDTO(
        @NotNull String name,
        @NotNull UnitOfMeasure unitOfMeasure,
        @NotNull Category category,
        LocalDateTime registrationDate

){

}
