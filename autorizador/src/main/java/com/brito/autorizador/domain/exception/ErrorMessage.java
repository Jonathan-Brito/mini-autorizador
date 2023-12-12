package com.brito.autorizador.domain.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.validation.FieldError;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record ErrorMessage(String message, String localizedItem, Throwable throwable) {
    public ErrorMessage(FieldError fieldError){
        this(fieldError.getDefaultMessage(), fieldError.getField(), null);
    }
}
