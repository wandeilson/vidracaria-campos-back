package com.vidracariaCampos.model.dto;
import com.vidracariaCampos.model.enums.TransationType;
import java.time.LocalDateTime;
import java.util.UUID;

public record StockTransationDTO(
        UUID id,
        int idProduct,
        TransationType transationType,
        int movimentationQtt,
        LocalDateTime date
){}
