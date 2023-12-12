package com.brito.autorizador.domain.exception;

import com.brito.autorizador.domain.enums.TransacaoEnum;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
public class CartaoInvalidoException extends RuntimeException{

    public CartaoInvalidoException(TransacaoEnum transacaoEnum){
        super(transacaoEnum.toString());
    }
}
