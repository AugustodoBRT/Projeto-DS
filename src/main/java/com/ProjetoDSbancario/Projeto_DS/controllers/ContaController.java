package com.ProjetoDSbancario.Projeto_DS.controllers;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ProjetoDSbancario.Projeto_DS.dto.ContaDTO;
import com.ProjetoDSbancario.Projeto_DS.services.ContaService;

@RestController
@RequestMapping("/api/conta")
@CrossOrigin(origins = "*")
public class ContaController {

    @Autowired
    private ContaService contaService;

    @GetMapping("/listar")
	public ResponseEntity<List<ContaDTO>> findAll(){
		List<ContaDTO> contas = contaService.findAll();
		return ResponseEntity.ok(contas);
	}

    @GetMapping("/listar/{id}")
	public ResponseEntity<ContaDTO> findById(@PathVariable Long id){
		ContaDTO conta = contaService.findById(id);
		return ResponseEntity.ok(conta);
	}

    @PostMapping("/{id}/limite")
    public ResponseEntity<ContaDTO> alterarLimiteCredito(@PathVariable Long id, @RequestParam Double novoLimite) {
        ContaDTO conta = contaService.alterarLimiteCredito(id, novoLimite);
        return ResponseEntity.ok(conta);
    }

    @PostMapping("/{id}/chave-pix")
    public ResponseEntity<ContaDTO> alterarChavePix(@PathVariable Long id, @RequestParam String chavePix) {
        ContaDTO conta = contaService.alterarChavePix(id, chavePix);
        return ResponseEntity.ok(conta);
    }

    @PostMapping
	public ResponseEntity<ContaDTO> create(@RequestBody ContaDTO contaDTO){
		ContaDTO conta = contaService.insert(contaDTO);
		return ResponseEntity.status(201).body(conta);
	}

}