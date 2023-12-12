package com.brito.autorizador.domain.entities;

import com.brito.autorizador.domain.dto.TransacaoNovaDto;
import com.brito.autorizador.domain.enums.TransacaoEnum;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "transacao")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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


    public Transacao(TransacaoNovaDto transacaoNova){
        this.numeroCartao = transacaoNova.numeroCartao();
        this.senhaCartao = transacaoNova.senhaCartao();
        this.valor = transacaoNova.valor();
    }

    public Transacao(TransacaoNovaDto transacaoNova, TransacaoEnum status){
        this.numeroCartao = transacaoNova.numeroCartao();
        this.senhaCartao = transacaoNova.senhaCartao();
        this.valor = transacaoNova.valor();
        this.status = status;
    }
}
