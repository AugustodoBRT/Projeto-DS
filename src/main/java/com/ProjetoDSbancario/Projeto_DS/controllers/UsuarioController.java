package com.ProjetoDSbancario.Projeto_DS.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ProjetoDSbancario.Projeto_DS.dto.ClienteDTO;
import com.ProjetoDSbancario.Projeto_DS.entities.Usuario;
import com.ProjetoDSbancario.Projeto_DS.services.UsuarioService;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
	private final UsuarioService usuarioService;
	
	public UsuarioController(UsuarioService usuarioService) {
		this.usuarioService = usuarioService;
	}
	
	@PostMapping
	public ResponseEntity<?> criarUsuario(@RequestBody ClienteDTO clienteDTO){
		try {
			Usuario usuario = usuarioService.criarUsuario(clienteDTO);
			return ResponseEntity.status(HttpStatus.CREATED).body(usuario);
		}catch(IllegalArgumentException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@GetMapping
	public List<Usuario> listarUsuarios(){
		return usuarioService.listarUsuarios();
	}
}
