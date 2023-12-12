package com.brito.autorizador.web.controller;

import com.brito.autorizador.domain.dto.TransacaoNovaDto;
import com.brito.autorizador.domain.enums.TransacaoEnum;
import com.brito.autorizador.domain.services.TransacaoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping(value = "/transacoes")
@RequiredArgsConstructor
public class TransacaoController {

    private final TransacaoService transacaoService;

    @PostMapping
    @ResponseStatus(CREATED)
    public TransacaoEnum transacao(@RequestBody @Valid TransacaoNovaDto transacaoNova){
        transacaoService.transacao(transacaoNova);
        return TransacaoEnum.OK;
    }
}
