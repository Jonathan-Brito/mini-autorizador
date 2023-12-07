package com.brito.autorizador.domain.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "cartao")
public class Cartao implements Serializable {

    private static final long serialVersionUID = 7636172071782978562L;

    @Id
    @Column(unique = true)
    private String numeroCartao;

    @NotNull
    private String senha;

    @NotNull
    private BigDecimal saldo;

    public Cartao(){

    }

    public Cartao(String numeroCartao, String senha, BigDecimal saldo){
        this.numeroCartao = numeroCartao;
        this.senha = senha;
        this.saldo = saldo;
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

    public BigDecimal getSaldo() {
        return saldo;
    }

    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }

    public void debitar(BigDecimal valor){
        this.saldo = this.saldo.subtract(valor);
    }

    @Override
    public String toString() {
        return "Cartao{" +
                "numeroCartao='" + numeroCartao + '\'' +
                ", senha='" + senha + '\'' +
                ", saldo=" + saldo +
                '}';
    }
}
