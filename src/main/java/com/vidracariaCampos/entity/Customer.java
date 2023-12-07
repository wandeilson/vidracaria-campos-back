package com.vidracariaCampos.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@Table(name="customer")
public class Customer implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;
    private String name; // nome
    @Column(name = "customer_type")
    private String customerType; // tipoCliente/ENUM
    private String cpf_cnpj; // CPF/CNPJ
    private String email; // email
    private String phone; // telefone
    private String address; // logradouro
    @Column(name = "zip_code")
    private String zipCode; // CEP
    private String number; // numero
    private String city; // cidade
    private String state; // estado
    private String landmark; // pontoDeReferencia
    @Column(name = "registration_date")
    private LocalDateTime registrationDate; // dataCriacao

}
