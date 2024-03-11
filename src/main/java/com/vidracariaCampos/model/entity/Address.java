package com.vidracariaCampos.model.entity;

import jakarta.persistence.Embeddable;
import lombok.Data;

@Data
@Embeddable
public class Address {
    private String address; // logradouro
    private String zipCode; // CEP
    private String number;
    private String city;
    private String state;
    private String landmark; // pontoDeReferencia
}
