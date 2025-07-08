package com.ProjetoDSbancario.Projeto_DS.dto;

import java.time.LocalDateTime;

import com.ProjetoDSbancario.Projeto_DS.entities.Cliente;
import com.ProjetoDSbancario.Projeto_DS.entities.Historico;

public class HistoricoDTO {

    private Long id;
    private LocalDateTime dataAcesso;
    private String enderecoIp;
    private Long idCliente;
    
    
    public HistoricoDTO() {
    	
    }
    
    public HistoricoDTO(Historico historico) {
    	this.id = historico.getId();
    	this.dataAcesso = historico.getDataAcesso();
    	this.enderecoIp = historico.getEnderecoIp();
    	this.idCliente = historico.getCliente().getId(); 
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDateTime getDataAcesso() {
		return dataAcesso;
	}

	public void setDataAcesso(LocalDateTime dataAcesso) {
		this.dataAcesso = dataAcesso;
	}

	public String getEnderecoIp() {
		return enderecoIp;
	}

	public void setEnderecoIp(String enderecoIp) {
		this.enderecoIp = enderecoIp;
	}

	public Long getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Long idCliente) {
		this.idCliente = idCliente;
	}
    
    
    
}
