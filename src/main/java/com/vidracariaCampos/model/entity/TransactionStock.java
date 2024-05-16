package com.vidracariaCampos.model.entity;

import com.vidracariaCampos.model.enums.TransactionType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;
@Entity
@Data
@Table(name = "transaction_stock")
public class TransactionStock {

    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;

    @Column(name = "id_product")
    @NotBlank
    private UUID idProduct;

    @Enumerated(EnumType.STRING)
    @NotBlank
    private TransactionType transactionType;

    @Column(name = "transaction_date")
    private LocalDateTime transactionDate;

    @NotBlank
    @Column(name = "movement_quantity")
    private int movementQuantity;

}
