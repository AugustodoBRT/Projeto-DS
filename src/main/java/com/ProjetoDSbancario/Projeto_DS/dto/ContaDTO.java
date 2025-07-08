package com.ProjetoDSbancario.Projeto_DS.dto;

import com.ProjetoDSbancario.Projeto_DS.entities.Conta;

public class ContaDTO {
    private Long id;
    private String numero;
    private Double saldo;
    private Double limiteCredito;
    private String chavePix;
    private Long clienteId;
    
    public ContaDTO(Conta conta) {
    	this.id = conta.getId();
    	this.numero = conta.getNumero();
    	this.saldo = conta.getSaldo();
    	this.limiteCredito = conta.getLimiteCredito();
    	this.chavePix = conta.getChavePix();
    	this.clienteId = conta.getCliente().getId();
    }
    
	public ContaDTO() {
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public Double getSaldo() {
		return saldo;
	}

	public void setSaldo(Double saldo) {
		this.saldo = saldo;
	}

	public Double getLimiteCredito() {
		return limiteCredito;
	}

	public void setLimiteCredito(Double limiteCredito) {
		this.limiteCredito = limiteCredito;
	}

	public String getChavePix() {
		return chavePix;
	}

	public void setChavePix(String chavePix) {
		this.chavePix = chavePix;
	}

	public Long getClienteId() {
		return clienteId;
	}

	public void setClienteId(Long clienteId) {
		this.clienteId = clienteId;
	}

    
    
}
