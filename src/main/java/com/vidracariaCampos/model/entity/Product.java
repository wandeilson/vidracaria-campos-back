package com.vidracariaCampos.model.entity;
import com.vidracariaCampos.model.enums.Category;
import com.vidracariaCampos.model.enums.UnitOfMeasure;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@Table(name="product")
public class Product {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;
    @Column(name = "id_user")
    private UUID idUser;
    private String name;
    private float height;
    private float width;
    private float depth;

    @Enumerated(EnumType.STRING)
    @Column(name = "unit_of_measure")
    private UnitOfMeasure unitOfMeasure;
    private BigDecimal price;
    @Enumerated(EnumType.STRING)
    private Category category;
    @Column(name = "registration_date")
    private LocalDateTime registrationDate;
}