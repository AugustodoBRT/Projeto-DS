package com.ProjetoDSbancario.Projeto_DS.dto;

import com.ProjetoDSbancario.Projeto_DS.entities.Lancamento;
import com.ProjetoDSbancario.Projeto_DS.entities.Operacao;
import com.ProjetoDSbancario.Projeto_DS.entities.Tipo;

public class LancamentoDTO {
	private Long id;
	private Double valor;
	private Tipo tipo;
	private Operacao operacao;
	private Long contaId;
	
	public LancamentoDTO() {
		
	}
	
	public LancamentoDTO(Lancamento lancamento) {
		this.id = lancamento.getId();
		this.valor = lancamento.getValor();
		this.tipo = lancamento.getTipo();
		this.operacao = lancamento.getOperacao();
		this.contaId = lancamento.getConta().getId();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public Tipo getTipo() {
		return tipo;
	}

	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}

	public Operacao getOperacao() {
		return operacao;
	}

	public void setOperacao(Operacao operacao) {
		this.operacao = operacao;
	}

	public Long getContaId() {
		return contaId;
	}

	public void setContaId(Long contaId) {
		this.contaId = contaId;
	}
	
	
}
