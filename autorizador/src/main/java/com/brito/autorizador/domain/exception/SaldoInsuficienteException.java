package com.brito.autorizador.domain.exception;

import com.brito.autorizador.domain.enums.TransacaoEnum;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
public class SaldoInsuficienteException extends RuntimeException{

    public SaldoInsuficienteException(TransacaoEnum transacaoEnum){
        super(transacaoEnum.toString());
    }
}
