package com.vidracariaCampos.entity;

import com.vidracariaCampos.enums.CustomerType;
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
    private String name;
    @Column(name = "customer_type")
    private CustomerType customerType;
    private String cpfcnpj;
    private String email;
    private String phone;
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "zipCode", column = @Column(name = "zip_code"))
    })
    private Address address;
    @Column(name = "registration_date")
    private LocalDateTime registrationDate;

}
