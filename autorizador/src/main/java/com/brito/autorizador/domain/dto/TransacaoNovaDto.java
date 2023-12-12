package com.brito.autorizador.domain.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record TransacaoNovaDto(
        @NotNull @NotBlank
        String numeroCartao,
        @NotNull @NotBlank
        String senhaCartao,
        @NotNull BigDecimal valor) {
}

