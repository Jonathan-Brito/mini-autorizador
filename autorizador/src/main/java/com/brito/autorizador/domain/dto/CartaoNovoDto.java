package com.brito.autorizador.domain.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serializable;

public class CartaoNovoDto implements Serializable {

    private static final long serialVersionUID = -6136282345225004572L;

    @NotNull
    @NotBlank
    @Size(min = 16, message = "O cartão deve conter 16 números")
    @Size(max = 16, message = "O cartão deve conter 16 números")
    private String numeroCartao;

    @NotNull
    @NotBlank
    @Size(min = 4, message = "A senha deve conter 4 números")
    @Size(max = 4, message = "A senha deve conter 4 números")
    private String senha;

    public CartaoNovoDto(){

    }

    public CartaoNovoDto(String numeroCartao, String senha) {
        this.numeroCartao = numeroCartao;
        this.senha = senha;
    }

    public String getNumeroCartao() {
        return numeroCartao;
    }

    public void setNumeroCartao(String numeroCartao) {
        this.numeroCartao = numeroCartao;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    @Override
    public String toString() {
        return "Cartao [numeroCartao=" + numeroCartao + ", senha=" + senha + "]";
    }
}
