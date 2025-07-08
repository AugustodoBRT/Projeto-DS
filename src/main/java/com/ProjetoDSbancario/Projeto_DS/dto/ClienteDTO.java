package com.ProjetoDSbancario.Projeto_DS.dto;

import com.ProjetoDSbancario.Projeto_DS.entities.Cliente;

public class ClienteDTO {
	private Long id;
    private String nome;
    private String email;
    private String cpf;
    private String telefone;
    private String senha;

    public Long getId() {
    	return this.id;
    }
    
    public void setId(Long id) {
    	this.id = id;
    }
    
    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getCpf() {
        return cpf;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getSenha() {
        return senha;
    }

    public ClienteDTO() {
    }

    public ClienteDTO(Cliente cliente) {
    	this.id = cliente.getId();
        this.nome = cliente.getNome();
        this.email = cliente.getEmail();
        this.cpf = cliente.getCpf();
        this.telefone = cliente.getTelefone();
    }
}