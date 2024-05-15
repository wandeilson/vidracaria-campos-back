package com.vidracariaCampos.model.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
@Entity
@Data
@Table(name = "stock")
public class Stock {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;
    @OneToMany(mappedBy = "stock", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProductStock> productStockList = new ArrayList<ProductStock>();

}
