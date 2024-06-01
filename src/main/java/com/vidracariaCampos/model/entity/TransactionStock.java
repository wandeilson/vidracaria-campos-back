package com.vidracariaCampos.model.entity;

import com.vidracariaCampos.model.enums.TransactionType;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
    @NotNull
    private UUID idProduct;

    @Enumerated(EnumType.STRING)
    @NotNull
    @Column(name = "transaction_type")
    private TransactionType transactionType;

    @Column(name = "transaction_date")
    private LocalDateTime transactionDate;

    @Min(1)
    @Max(100)
    @Column(name = "movement_quantity")
    private int movementQuantity;

}
