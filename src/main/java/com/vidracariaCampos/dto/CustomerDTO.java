package com.vidracariaCampos.dto;

import com.vidracariaCampos.entity.Address;
import com.vidracariaCampos.enums.CustomerType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;


public record CustomerDTO(
        @NotBlank(message = "Name cannot be blank")
        @Size(min = 5, message = "Name must contain at least 5 characters")
        String name,
        CustomerType customerType,
        String cpfcnpj,
        @Email(message = "Email is not valid", regexp = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")
        String email,
        @NotBlank
        @Size(min = 10, message = "Phone must contain as least 10 characters")
        String phone,
        Address address

) {
}
