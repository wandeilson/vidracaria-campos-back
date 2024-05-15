package com.vidracariaCampos.model.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;
@Data
@Entity
@Table(name = "product_stock")
public class ProductStock {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "stock_id")
    private Stock stock;
    @Column(name = "id_product")
    private UUID idProduct;
    @Column(name = "actual_quantity")
    private int actualQuantity;
    @Column(name = "total_entries")
    private int totalEntries;
    @Column(name = "total_exits")
    private int totalExits;
}
