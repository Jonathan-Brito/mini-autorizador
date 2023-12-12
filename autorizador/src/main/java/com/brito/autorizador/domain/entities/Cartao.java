package com.brito.autorizador.domain.entities;

import com.brito.autorizador.domain.dto.CartaoNovoDto;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;

@Entity
@Table(name = "cartao")
@Getter
@Setter
@NoArgsConstructor
public class Cartao implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String numeroCartao;

    @NotNull
    private String senha;

    @NotNull
    private BigDecimal saldo;

    public Cartao(CartaoNovoDto cartaoNovo) {
        this.numeroCartao = cartaoNovo.numeroCartao();
        this.senha = cartaoNovo.senha();
        this.saldo = BigDecimal.valueOf(500.00).setScale(2, RoundingMode.DOWN);
    }

    public Cartao(String numeroCartao, String senha, BigDecimal saldo) {
        this.numeroCartao = numeroCartao;
        this.senha = senha;
        this.saldo = saldo;
    }

    public void debitar(BigDecimal valor) {
        this.saldo = this.saldo.subtract(valor);
    }

    @Override
    public String toString() {
        return "Cartao [numeroCartao=" + numeroCartao + ", senha=" + senha + ", saldo=" + saldo + "]";
    }
}
