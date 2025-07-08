package com.ProjetoDSbancario.Projeto_DS.dto;

public class LoginDTO {
	private String login;
	private String senha;
	
	public LoginDTO() {
		
	}
	
	public LoginDTO(String login, String senha) {
		this.login = login;
		this.senha = senha;
	}
	
	public String getLogin() {
		return login;
	}
	
	public String getSenha() {
		return senha;
	}
}
