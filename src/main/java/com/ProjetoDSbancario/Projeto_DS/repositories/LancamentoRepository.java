package com.ProjetoDSbancario.Projeto_DS.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ProjetoDSbancario.Projeto_DS.entities.Lancamento;

public interface LancamentoRepository extends JpaRepository<Lancamento, Long>{
	List<Lancamento> findByContaId(Long id);
}
