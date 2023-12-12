package com.brito.autorizador.domain.services;

import com.brito.autorizador.domain.dto.TransacaoNovaDto;
import com.brito.autorizador.domain.entities.Cartao;
import com.brito.autorizador.domain.entities.Transacao;
import com.brito.autorizador.domain.exception.CartaoInvalidoException;
import com.brito.autorizador.domain.exception.SenhaInvalidaException;
import com.brito.autorizador.domain.exception.SaldoInsuficienteException;
import com.brito.autorizador.domain.repositories.CartaoRepository;
import com.brito.autorizador.domain.repositories.TransacaoRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

import static com.brito.autorizador.domain.enums.TransacaoEnum.*;

@Service
@RequiredArgsConstructor
@Transactional(Transactional.TxType.SUPPORTS)
public class TransacaoService {

    private final TransacaoRepository transacaoRepository;

    private final CartaoRepository cartaoRepository;

    public void transacao(TransacaoNovaDto transacaoNova){
        Cartao cartao = cartaoRepository.findByNumeroCartao(transacaoNova.numeroCartao());

        validarCartao(cartao, transacaoNova);
        validarSenhaCartao(cartao.getSenha(), transacaoNova);
        validarSaldoCartao(cartao.getSaldo(), transacaoNova);
        debitarSaldoCartao(cartao, transacaoNova);
    }

    @Transactional(dontRollbackOn = CartaoInvalidoException.class)
    public void validarCartao(Cartao cartao, TransacaoNovaDto transacaoNova){
        Cartao cartaoValidado = (cartao != null) ? cartao : lancarExcecao(transacaoNova);
    }
    private Cartao lancarExcecao(TransacaoNovaDto transacaoNova){
        transacaoRepository.save(new Transacao(transacaoNova, CARTAO_INEXISTENTE));
        throw new CartaoInvalidoException(CARTAO_INEXISTENTE);
    }

    @Transactional(dontRollbackOn = SenhaInvalidaException.class)
    public void validarSenhaCartao(String senhaCartao, TransacaoNovaDto transacaoNova) {
        if (!senhaCartao.equals(transacaoNova.senhaCartao())) {
            transacaoRepository.save(new Transacao(transacaoNova, SENHA_INVALIDA));
            throw new SenhaInvalidaException(SENHA_INVALIDA);
        }
    }

    @Transactional(dontRollbackOn = SaldoInsuficienteException.class)
    public void validarSaldoCartao(BigDecimal saldoCartao, TransacaoNovaDto transacaoNova) {
        if ((saldoCartao.subtract(transacaoNova.valor())).signum() == -1) {
            transacaoRepository.save(new Transacao(transacaoNova, SALDO_INSUFICIENTE));
            throw new SaldoInsuficienteException(SALDO_INSUFICIENTE);
        }
    }

    public synchronized void debitarSaldoCartao(Cartao cartao, TransacaoNovaDto transacaoNova){
        cartao.debitar(transacaoNova.valor());
        cartaoRepository.save(cartao);
        transacaoRepository.save(new Transacao(transacaoNova, OK));
    }
}
