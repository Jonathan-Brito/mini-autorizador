package com.brito.autorizador.domain.services;

import com.brito.autorizador.domain.dto.CartaoNovoDto;
import com.brito.autorizador.domain.entities.Cartao;
import com.brito.autorizador.domain.exception.CartaoExistenteException;
import com.brito.autorizador.domain.exception.CartaoInvalidoException;
import com.brito.autorizador.domain.repositories.CartaoRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

import static com.brito.autorizador.domain.enums.TransacaoEnum.CARTAO_INVALIDO;

@Service
@RequiredArgsConstructor
public class CartaoService {

    private final CartaoRepository cartaoRepository;

    @Transactional
    public CartaoNovoDto criar(CartaoNovoDto cartaoNovo){
        validarNumeroCartao(cartaoNovo.numeroCartao());
        validarCartaoExistente(cartaoNovo.numeroCartao());

        Cartao cartao = new Cartao(cartaoNovo);
        cartaoRepository.saveAndFlush(cartao);

        return cartaoNovo;
    }

    public BigDecimal saldoCartao(String numeroCartao){
        Cartao cartao = cartaoRepository.findByNumeroCartao(numeroCartao);

        return (cartao != null) ? cartao.getSaldo() : lancarExcecao();
    }

    private BigDecimal lancarExcecao() {
        throw new CartaoInvalidoException(CARTAO_INVALIDO);
    }

    private void validarNumeroCartao(String numeroCartao){
        Optional.of(numeroCartao)
                .filter(nc -> nc.matches("^\\d+$"))
                .orElseThrow(() -> new CartaoInvalidoException(CARTAO_INVALIDO));
    }

    private void validarCartaoExistente(String numeroCartao){
        Optional.ofNullable(cartaoRepository.findByNumeroCartao(numeroCartao))
                .ifPresent(cartao -> {
                    throw new CartaoExistenteException(cartao.getNumeroCartao(), cartao.getSenha());
                });
    }

}
