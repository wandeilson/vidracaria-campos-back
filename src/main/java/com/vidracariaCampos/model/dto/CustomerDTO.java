package com.vidracariaCampos.model.dto;

import com.vidracariaCampos.model.entity.Address;
import com.vidracariaCampos.model.enums.CustomerType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.UUID;


public record CustomerDTO(
        UUID id,
        @NotBlank(message = "Name cannot be blank")
        @Size(min = 5, message = "Name must contain at least 5 characters")
        String name,
        @NotNull(message = "Customer type cannot be blank")
        CustomerType customerType,
        @Size(max = 20, message = "CPF/CNPJ not valid")
        String cpfcnpj,
        String phone,
        String email,
        Address address
) {
}
