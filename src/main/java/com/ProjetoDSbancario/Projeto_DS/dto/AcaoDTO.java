package com.ProjetoDSbancario.Projeto_DS.dto;

public class AcaoDTO {
	private String conta1;
	private String conta2;
	private Double valor;
	
	public AcaoDTO() {
		
	}

	public AcaoDTO(String conta1, String conta2, Double valor) {
		this.conta1 = conta1;
		this.conta2 = conta2;
		this.valor = valor;
	}

	public String getConta1() {
		return conta1;
	}

	public void setConta1(String conta1) {
		this.conta1 = conta1;
	}

	public String getConta2() {
		return conta2;
	}

	public void setConta2(String conta2) {
		this.conta2 = conta2;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}
}
