package com.brito.autorizador.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity tratarErro400(MethodArgumentNotValidException ex) {
        return ResponseEntity
                .badRequest()
                .body(ex.getFieldErrors()
                        .stream()
                        .map(ErrorMessage::new)
                        .toList());
    }

    @ExceptionHandler(CartaoExistenteException.class)
    public ResponseEntity cartaoExistente(CartaoExistenteException ex) {
        return ResponseEntity
                .notFound()
                .build();
    }

    @ExceptionHandler(CartaoInvalidoException.class)
    public ResponseEntity cartaoInvalido(CartaoInvalidoException ex) {
        return ResponseEntity
                .status(HttpStatus.UNPROCESSABLE_ENTITY)
                .body(new ErrorMessage(ex.getMessage(), null, ex.getCause()));
    }

    @ExceptionHandler(SenhaInvalidaException.class)
    public ResponseEntity senhaInvalida(SenhaInvalidaException ex) {
        return ResponseEntity
                .status(HttpStatus.UNPROCESSABLE_ENTITY)
                .body(new ErrorMessage(ex.getMessage(), null, ex.getCause()));
    }

    @ExceptionHandler(SaldoInsuficienteException.class)
    public ResponseEntity saldoInsuficiente(SaldoInsuficienteException ex) {
        return ResponseEntity
                .status(HttpStatus.UNPROCESSABLE_ENTITY)
                .body(new ErrorMessage(ex.getMessage(), null, ex.getCause()));
    }
}
