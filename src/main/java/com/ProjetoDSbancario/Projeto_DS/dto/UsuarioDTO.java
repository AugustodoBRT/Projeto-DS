package com.ProjetoDSbancario.Projeto_DS.dto;

import com.ProjetoDSbancario.Projeto_DS.entities.NivelAcesso;

public class UsuarioDTO {
    
    private Long id;
    private String login;
    private NivelAcesso nivelAcesso;
    private Long pessoaId;

    public UsuarioDTO() {
    }

    public UsuarioDTO(Long id, String login, NivelAcesso nivelAcesso, Long pessoaId) {
        this.id = id;
        this.login = login;
        this.nivelAcesso = nivelAcesso;
        this.pessoaId = pessoaId;
    }

    public Long getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public NivelAcesso getNivelAcesso() {
        return nivelAcesso;
    }

    public Long getPessoaId() {
        return pessoaId;
    }

    

    
}
