package com.ProjetoDSbancario.Projeto_DS.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ProjetoDSbancario.Projeto_DS.entities.Conta;

@Repository
public interface ContaRepository extends JpaRepository<Conta, Long> {
	Optional<Conta> findByChavePix(String pix);
	Optional<Conta> findByNumero(String numero);
	List<Conta> findAllByClienteId(Long id);
	Boolean existsByNumero(String numero);
	Boolean existsByChavePix(String chavePix);
}