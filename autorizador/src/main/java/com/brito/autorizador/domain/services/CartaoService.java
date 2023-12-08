package com.brito.autorizador.domain.services;

import com.brito.autorizador.domain.dto.CartaoNovoDto;
import com.brito.autorizador.domain.entities.Cartao;
import com.brito.autorizador.domain.exception.CartaoExistenteException;
import com.brito.autorizador.domain.exception.CartaoInvalidoException;
import com.brito.autorizador.domain.repositories.CartaoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
@Transactional
public class CartaoService {

    @Autowired
    CartaoRepository cartaoRepository;

    public CartaoNovoDto criar(CartaoNovoDto cartaoNovo){
        validarNumeroCartao(cartaoNovo.getNumeroCartao());
        validarCartaoExistente(cartaoNovo.getNumeroCartao());

        Cartao cartao = new Cartao(cartaoNovo);
        cartaoRepository.saveAndFlush(cartao);

        return cartaoNovo;
    }

    public BigDecimal saldoCartao(String numeroCartao){
        Cartao cartao = cartaoRepository.findByNumeroCartao(numeroCartao);

        return (cartao != null) ? cartao.getSaldo() : lancarExcecao();
    }

    private BigDecimal lancarExcecao() {
        throw new CartaoInvalidoException();
    }

    private void validarNumeroCartao(String numeroCartao){
        Optional.of(numeroCartao)
                .filter(nc -> nc.matches("^\\\\d+$"))
                .orElseThrow(CartaoInvalidoException::new);
    }

    private void validarCartaoExistente(String numeroCartao){
        Optional.ofNullable(cartaoRepository.findByNumeroCartao(numeroCartao))
                .ifPresent(cartao -> {
                    throw new CartaoExistenteException(cartao.getNumeroCartao(), cartao.getSenha());
                });
    }

}
