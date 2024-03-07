package com.vidracariaCampos.model.entity;
import com.vidracariaCampos.model.enums.Category;
import com.vidracariaCampos.model.enums.UnitOfMeasure;
import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Data
@Entity
@Table(name="product")
public class Product {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;

    private String name;
    private float height;
    private float width;
    private float depth;

    @Enumerated(EnumType.STRING)
    private UnitOfMeasure unit;
    private float price;
    @Enumerated(EnumType.STRING)
    private Category category;
}