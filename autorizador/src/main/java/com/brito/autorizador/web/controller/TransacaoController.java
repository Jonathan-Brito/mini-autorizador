package com.brito.autorizador.web.controller;

import com.brito.autorizador.domain.dto.TransacaoNovaDto;
import com.brito.autorizador.domain.enums.TransacaoEnum;
import com.brito.autorizador.domain.exception.CartaoInvalidoException;
import com.brito.autorizador.domain.exception.SaldoInsuficienteException;
import com.brito.autorizador.domain.exception.SenhaInvalidaException;
import com.brito.autorizador.domain.services.TransacaoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.UNPROCESSABLE_ENTITY;

@RestController
@RequestMapping(value = "/transacoes")
public class TransacaoController {

    @Autowired
    private TransacaoService transacaoService;

    @PostMapping
    public ResponseEntity<TransacaoEnum> transacao(@RequestBody @Valid TransacaoNovaDto transacaoNova){
        try {
            transacaoService.transacao(transacaoNova);
            return new ResponseEntity<>(TransacaoEnum.OK, CREATED);
        } catch (CartaoInvalidoException e){
            return new ResponseEntity<>(TransacaoEnum.CARTAO_INEXISTENTE, UNPROCESSABLE_ENTITY);
        } catch (SenhaInvalidaException e) {
            return new ResponseEntity<>(TransacaoEnum.SENHA_INVALIDA, UNPROCESSABLE_ENTITY);
        } catch (SaldoInsuficienteException e) {
            return new ResponseEntity<>(TransacaoEnum.SALDO_INSUFICIENTE, UNPROCESSABLE_ENTITY);
        }
    }
}
