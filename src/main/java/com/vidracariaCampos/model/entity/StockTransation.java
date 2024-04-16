package com.vidracariaCampos.model.entity;

import com.vidracariaCampos.model.enums.TransationType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class StockTransation {

    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;

    private int idProduct;
    private TransationType transationType;
    private int movimentationQtt;

    @Column(name = "date_transation")
    private LocalDateTime date;
}
