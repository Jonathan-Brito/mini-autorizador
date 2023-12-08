package com.brito.autorizador.web.controller;

import com.brito.autorizador.domain.dto.CartaoNovoDto;
import com.brito.autorizador.domain.exception.CartaoExistenteException;
import com.brito.autorizador.domain.exception.CartaoInvalidoException;
import com.brito.autorizador.domain.services.CartaoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

import static org.springframework.http.HttpStatus.*;

@RestController
@Validated
@RequestMapping(value = "/cartoes")
public class CartaoController {

    @Autowired
    private CartaoService cartaoService;

    @PostMapping
    public ResponseEntity<CartaoNovoDto> criar(@RequestBody @Valid CartaoNovoDto cartaoNovo){
        try {
            return new ResponseEntity<>(cartaoService.criar(cartaoNovo), CREATED);
        } catch (CartaoExistenteException e){
            return new ResponseEntity<>(cartaoNovo, UNPROCESSABLE_ENTITY);
        } catch (CartaoInvalidoException e){
            return new ResponseEntity<>(null, UNPROCESSABLE_ENTITY);
        }
    }

    @GetMapping(value = "/{numeroCartao}")
    public ResponseEntity<BigDecimal> saldoCartao(@PathVariable String numeroCartao){
        try {
            BigDecimal response = cartaoService.saldoCartao(numeroCartao);
            return new ResponseEntity<>(response, OK);
        } catch (CartaoInvalidoException e){
            return new ResponseEntity<>(null, NOT_FOUND);
        }
    }
}
