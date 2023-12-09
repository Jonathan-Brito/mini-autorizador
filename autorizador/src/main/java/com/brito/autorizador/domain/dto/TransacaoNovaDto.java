package com.brito.autorizador.domain.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public class TransacaoNovaDto {

    @NotNull
    @NotBlank
    private String numeroCartao;

    @NotNull
    @NotBlank
    private String senhaCartao;

    @NotNull
    private BigDecimal valor;

    public TransacaoNovaDto(){

    }

    public TransacaoNovaDto(String numeroCartao, String senhaCartao, BigDecimal valor){
        this.numeroCartao = numeroCartao;
        this.senhaCartao = senhaCartao;
        this.valor = valor;
    }

    public String getNumeroCartao() {
        return numeroCartao;
    }

    public void setNumeroCartao(String numeroCartao) {
        this.numeroCartao = numeroCartao;
    }

    public String getSenhaCartao() {
        return senhaCartao;
    }

    public void setSenhaCartao(String senhaCartao) {
        this.senhaCartao = senhaCartao;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }
}
