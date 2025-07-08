package com.ProjetoDSbancario.Projeto_DS.dto;

import com.ProjetoDSbancario.Projeto_DS.entities.NivelAcesso;

public class UsuarioCreateDTO {
    
    private Long id;
    private String login;
    private String senha;
    private NivelAcesso nivelAcesso;
    private Long pessoaId;

    public UsuarioCreateDTO() {
    }

    public UsuarioCreateDTO(Long id, String login, String senha, NivelAcesso nivelAcesso, Long pessoaId) {
        this.id = id;
        this.login = login;
        this.senha = senha;
        this.nivelAcesso = nivelAcesso;
        this.pessoaId = pessoaId;
    }

    public Long getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getSenha() {
        return senha;
    }

    public NivelAcesso getNivelAcesso() {
        return nivelAcesso;
    }

    public Long getPessoaId() {
        return pessoaId;
    }

    

    
}
