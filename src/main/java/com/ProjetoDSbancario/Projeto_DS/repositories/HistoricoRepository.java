package com.ProjetoDSbancario.Projeto_DS.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ProjetoDSbancario.Projeto_DS.entities.Historico;


@Repository
public interface HistoricoRepository extends JpaRepository<Historico, Long> {
    List<Historico> findByClienteId(Long id);
}