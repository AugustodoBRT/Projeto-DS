package com.ProjetoDSbancario.Projeto_DS.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ProjetoDSbancario.Projeto_DS.dto.AcaoDTO;
import com.ProjetoDSbancario.Projeto_DS.dto.LancamentoDTO;
import com.ProjetoDSbancario.Projeto_DS.services.LancamentoService;

@RestController
@RequestMapping("/lancamento")
public class LancamentoController {
	
	@Autowired
	private LancamentoService lancamentoService;
	
	@GetMapping("/{id}")
	public ResponseEntity<List<LancamentoDTO>> findAll(@PathVariable Long id){
		List<LancamentoDTO> lista = lancamentoService.findByClienteId(id);
		return ResponseEntity.ok(lista);
	}
 	
	@PostMapping("/saque")
	public ResponseEntity<LancamentoDTO> realizarSaque(@RequestBody AcaoDTO acaoDTO){
		LancamentoDTO lancamento = lancamentoService.saque(acaoDTO.getConta1(), acaoDTO.getValor());
		return ResponseEntity.status(201).body(lancamento);
	}
	
	@PostMapping("/pix")
	public ResponseEntity<List<LancamentoDTO>> realizarPix(@RequestBody AcaoDTO acaoDTO){
		List<LancamentoDTO> listaLancamento = lancamentoService.pix(acaoDTO.getConta1(), acaoDTO.getConta2(), acaoDTO.getValor());
		return ResponseEntity.status(201).body(listaLancamento);
	}	
	
	@PostMapping("/transferencia")
	public ResponseEntity<List<LancamentoDTO>> realizarTransferencia(@RequestBody AcaoDTO acaoDTO){
		List<LancamentoDTO> listaLancamento = lancamentoService.transferencia(acaoDTO.getConta1(), acaoDTO.getConta2(), acaoDTO.getValor());
		return ResponseEntity.status(201).body(listaLancamento);
	}
	
	@PostMapping("/deposito")
	public ResponseEntity<LancamentoDTO> realizarDeposito(@RequestBody AcaoDTO acaoDTO){
		LancamentoDTO lancamento = lancamentoService.deposito(acaoDTO.getConta1(), acaoDTO.getValor());
		return ResponseEntity.status(201).body(lancamento);
	}
	
	
}
