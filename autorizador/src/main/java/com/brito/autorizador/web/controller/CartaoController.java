package com.brito.autorizador.web.controller;

import com.brito.autorizador.domain.dto.CartaoNovoDto;
import com.brito.autorizador.domain.services.CartaoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/cartoes")
public class CartaoController {

    private final CartaoService cartaoService;

    @Operation(
            summary = "Criar novo cartão",
            description = "<ul><li><p>Deve ser inserido o numero do cartão (String) com 16 carateres numéricos.<p></li>" +
                    "<li><p>Deve ser inserida a senha (String) com 4 carateres numéricos</p></li>")
    @ApiResponses({
            @ApiResponse(responseCode = "201", content = {@Content(schema = @Schema(implementation = CartaoNovoDto.class), mediaType = "application/json")}),
            @ApiResponse(responseCode = "422", content = {@Content(schema = @Schema())}),
            @ApiResponse(responseCode = "400", content = {@Content(schema = @Schema())})
    })
    @PostMapping
    @ResponseStatus(CREATED)
    public CartaoNovoDto criar(@RequestBody @Valid CartaoNovoDto cartaoNovo) {
        return cartaoService.criar(cartaoNovo);
    }

    @Operation(
            summary = "Consulta o saldo do cartão",
            description = "<ul><li><p>Consulta o saldo atual do cartão</p></li></ul>" +
                    "<p><strong>numeroCartao:</strong> Deve ser inserido o numero do cartão com 16 caracteres numéricos</p>")
    @GetMapping(value = "/{numeroCartao}")
    @ResponseStatus(OK)
    public BigDecimal saldoCartao(@PathVariable String numeroCartao) {
        return cartaoService.saldoCartao(numeroCartao);
    }

}
