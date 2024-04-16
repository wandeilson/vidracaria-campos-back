package com.vidracariaCampos.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;
import java.util.Set;
import java.util.UUID;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Stock {
    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;

    @OneToOne
    private User userId;

    @OneToMany
    private Set<StockTransation> historicTransation;

    @ElementCollection
    @CollectionTable(name = "stock_products_mapping")
    @MapKeyColumn(name = "product_id")
    // Id do Produto / Quantidade
    private Map<UUID,Integer> myProducts;

    private int currentQtt;
    private int outputQtt;
}
