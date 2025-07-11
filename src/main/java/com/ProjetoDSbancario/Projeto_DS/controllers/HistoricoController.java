package com.ProjetoDSbancario.Projeto_DS.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ProjetoDSbancario.Projeto_DS.dto.HistoricoDTO;
import com.ProjetoDSbancario.Projeto_DS.services.HistoricoService;

@RestController
@RequestMapping("/historico")
public class HistoricoController {
	
	@Autowired
	private HistoricoService historicoService;
	
	@GetMapping("/{id}")
	public ResponseEntity<List<HistoricoDTO>> findByClienteId(@PathVariable Long id){
		List<HistoricoDTO> lista = historicoService.findByClienteId(id);
		return ResponseEntity.ok(lista);
	}
}
