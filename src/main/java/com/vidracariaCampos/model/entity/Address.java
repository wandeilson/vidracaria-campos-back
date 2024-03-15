package com.vidracariaCampos.model.entity;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class Address {
    private String address; // logradouro
    private String zipCode; // CEP
    private String number;
    private String city;
    private String state;
    private String landmark; // pontoDeReferencia
}
