package com.vidracariaCampos.model.dto;

import com.vidracariaCampos.model.entity.StockTransation;
import com.vidracariaCampos.model.entity.User;
import jakarta.persistence.*;

import java.util.Map;
import java.util.Set;
import java.util.UUID;

public record StockDTO(
        UUID id,
        UUID userId,
        Set<StockTransation> historicTransation,
        Map<UUID,Integer> myProducts,
        int currentQtt,
        int outputQtt
) {
}
