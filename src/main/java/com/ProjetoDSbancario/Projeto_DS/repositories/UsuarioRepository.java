package com.ProjetoDSbancario.Projeto_DS.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ProjetoDSbancario.Projeto_DS.entities.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByLogin(String login);
    boolean existsByLogin(String login);
}
