package com.vidracariaCampos.dto;

import com.vidracariaCampos.entity.Address;
import com.vidracariaCampos.enums.CustomerType;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public record CustomerDTO(
        @NotBlank(message = "Name cannot be blank")
        @Size(min = 5, message = "Name must contain at least 5 characters")
        String name,
        CustomerType customerType,
        String cpfcnpj,
        @Email(message = "Email is not valid", regexp = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")
        String email,
        String phone,
        Address address

) {
}
