package com.vidracariaCampos.dto;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.UUID;

public record UserDTO(UUID id,
                      @NotBlank(message = "Email cannot be blank")
                      @Email(message = "Email is not valid", regexp = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")
                      String email,
                      @NotBlank(message = "Name cannot be blank")
                      @Size(max = 255, message = "Name must be at most 255 characters")
                      String name,
                      String password,

                      @NotBlank(message = "Role cannot be blank")
                      String role) {
}
