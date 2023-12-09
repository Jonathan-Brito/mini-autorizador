package com.brito.autorizador.domain.entities;

import com.brito.autorizador.domain.dto.TransacaoNovaDto;
import com.brito.autorizador.domain.enums.TransacaoEnum;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

@Entity
@Table(name = "transacao")
public class Transacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String numeroCartao;

    @NotNull
    private String senhaCartao;

    @NotNull
    private BigDecimal valor;

    @Enumerated(EnumType.STRING)
    private TransacaoEnum status;

    public Transacao(){

    }

    public Transacao(TransacaoNovaDto transacaoNova){
        this.numeroCartao = transacaoNova.getNumeroCartao();
        this.senhaCartao = transacaoNova.getSenhaCartao();
        this.valor = transacaoNova.getValor();
    }

    public Transacao(TransacaoNovaDto transacaoNova, TransacaoEnum status){
        this.numeroCartao = transacaoNova.getNumeroCartao();
        this.senhaCartao = transacaoNova.getSenhaCartao();
        this.valor = transacaoNova.getValor();
        this.status = status;
    }

    public Transacao(String numeroCartao, String senhaCartao, BigDecimal valor, TransacaoEnum status){
        this.numeroCartao = numeroCartao;
        this.senhaCartao = senhaCartao;
        this.valor = valor;
        this.status = status;
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

    public TransacaoEnum getStatus() {
        return status;
    }

    public void setStatus(TransacaoEnum status) {
        this.status = status;
    }
}
