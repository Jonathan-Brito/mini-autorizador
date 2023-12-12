package com.brito.autorizador.domain.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CartaoNovoDto(
        @NotNull @NotBlank
        @Size(min = 16, message = "O cartão deve conter 16 números")
        @Size(max = 16, message = "O cartão deve conter 16 números")
        String numeroCartao,
        @NotNull @NotBlank
        @Size(min = 4, message = "A senha deve conter 4 números")
        @Size(max = 4, message = "A senha deve conter 4 números")
        String senha) {
}

