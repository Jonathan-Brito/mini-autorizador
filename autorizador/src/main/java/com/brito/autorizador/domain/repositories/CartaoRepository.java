package com.brito.autorizador.domain.repositories;

import com.brito.autorizador.domain.entities.Cartao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartaoRepository extends JpaRepository<Cartao, Long> {

    Cartao findByNumeroCartao(String numeroCartao);
}
