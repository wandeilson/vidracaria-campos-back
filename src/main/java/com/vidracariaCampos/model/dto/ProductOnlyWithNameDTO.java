package com.vidracariaCampos.model.dto;

import java.util.UUID;

public record ProductOnlyWithNameDTO(
        UUID id,
        String name
) {
}
