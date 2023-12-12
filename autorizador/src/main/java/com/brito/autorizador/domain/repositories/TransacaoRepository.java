package com.brito.autorizador.domain.repositories;

import com.brito.autorizador.domain.entities.Transacao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransacaoRepository extends JpaRepository<Transacao, Long> {
}
