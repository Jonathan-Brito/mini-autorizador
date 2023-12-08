package com.brito.autorizador.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
public class CartaoExistenteException extends RuntimeException{

    private final String numeroCartao;

    private final String senha;

    public CartaoExistenteException(String numeroCartao, String senha){
        super();
        this.numeroCartao = numeroCartao;
        this.senha = senha;
    }
}
