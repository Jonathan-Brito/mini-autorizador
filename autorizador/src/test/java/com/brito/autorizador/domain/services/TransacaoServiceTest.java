package com.brito.autorizador.domain.services;

import com.brito.autorizador.domain.repositories.CartaoRepository;
import com.brito.autorizador.domain.repositories.TransacaoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

class TransacaoServiceTest {

    @Mock
    private CartaoRepository cartaoRepository;

    @Mock
    private TransacaoRepository transacaoRepository;

    @Autowired
    @InjectMocks
    private TransacaoService transacaoService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void transacao() {
    }

    @Test
    void validarCartao() {
    }

    @Test
    void validarSenhaCartao() {
    }

    @Test
    void validarSaldoCartao() {
    }

    @Test
    void debitarSaldoCartao() {
    }
}