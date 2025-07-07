package com.ProjetoDSbancario.Projeto_DS.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ProjetoDSbancario.Projeto_DS.dto.LancamentoDTO;
import com.ProjetoDSbancario.Projeto_DS.entities.Conta;
import com.ProjetoDSbancario.Projeto_DS.entities.Lancamento;
import com.ProjetoDSbancario.Projeto_DS.entities.Operacao;
import com.ProjetoDSbancario.Projeto_DS.entities.Tipo;
import com.ProjetoDSbancario.Projeto_DS.repositories.ContaRepository;
import com.ProjetoDSbancario.Projeto_DS.repositories.LancamentoRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class LancamentoService {
	
	@Autowired
	private LancamentoRepository lancamentoRepository;
	
	@Autowired
	private ContaRepository contaRepository;
	
	/* Esse método recebe duas chaves pix e um valor e realiza a operação de saque e deposito*/
	public List<LancamentoDTO> pix(String pix1, String pix2, Double valor){
		Conta conta1 = contaRepository.findByChavePix(pix1)
				.orElseThrow(()-> new EntityNotFoundException("Conta não existe " + pix1));
		
		Conta conta2 = contaRepository.findByChavePix(pix2)
				.orElseThrow(()-> new EntityNotFoundException("Conta não existe " + pix2));
		
		if(pix1.equals(pix2))
			throw new IllegalArgumentException("O pix não pode ser o mesmo!");
		
		if(conta1.getSaldo() + conta1.getLimiteCredito() < valor)
			throw new IllegalArgumentException("O valor é maior do que o saldo e o limite de crédito");
		
		LancamentoDTO aux = new LancamentoDTO();
		aux.setContaId(conta1.getId());
		aux.setValor(valor);
		aux.setTipo(Tipo.DEBITO);
		aux.setOperacao(Operacao.PIX);
		
		LancamentoDTO lancamento1 = insert(aux);
		
		aux.setContaId(conta2.getId());
		aux.setValor(valor);
		aux.setTipo(Tipo.CREDITO);
		aux.setOperacao(Operacao.PIX);
		
		LancamentoDTO lancamento2 = insert(aux);
		
		List<LancamentoDTO> listaLancamentos = new ArrayList<>();
		listaLancamentos.add(lancamento1);
		listaLancamentos.add(lancamento2);
		return listaLancamentos;
	}
	
	public List<LancamentoDTO> transferencia(String numero1, String numero2, Double valor){
		Conta conta1 = contaRepository.findByNumero(numero1)
				.orElseThrow(()-> new EntityNotFoundException("Conta não existe " + numero1));
		
		Conta conta2 = contaRepository.findByNumero(numero2)
				.orElseThrow(()-> new EntityNotFoundException("Conta não existe " + numero2));
		
		if(numero1.equals(numero2))
			throw new IllegalArgumentException("As contas não podem ser iguais");
		
		if(valor*1.10 > conta1.getLimiteCredito() + conta1.getSaldo())
			throw new IllegalArgumentException("Saldo insuficiente");
		
		LancamentoDTO aux = new LancamentoDTO();
		aux.setContaId(conta1.getId());
		aux.setValor(valor);
		aux.setTipo(Tipo.DEBITO);
		aux.setOperacao(Operacao.TRANSFERENCIA);
		
		LancamentoDTO lancamento1 = insert(aux);

		aux.setContaId(conta1.getId());
		aux.setValor(valor*0.10);
		aux.setTipo(Tipo.DEBITO);
		aux.setOperacao(Operacao.TAXA);
		
		LancamentoDTO lancamento2 = insert(aux);
		
		aux.setContaId(conta2.getId());
		aux.setValor(valor);
		aux.setTipo(Tipo.CREDITO);
		aux.setOperacao(Operacao.TRANSFERENCIA);
		
		LancamentoDTO lancamento3 = insert(aux);
		List<LancamentoDTO> listaLancamentos = new ArrayList<>();
		listaLancamentos.add(lancamento1);
		listaLancamentos.add(lancamento2);
		listaLancamentos.add(lancamento3);
		
		return listaLancamentos;
	}
	
	public LancamentoDTO saque(String numero, Double valor) {
		Conta conta = contaRepository.findByNumero(numero)
				.orElseThrow(()-> new EntityNotFoundException("Conta não existe " + numero));
		
		if(conta.getSaldo() + conta.getLimiteCredito() < valor)
			throw new IllegalArgumentException("O saldo insuficiente!");
		
		LancamentoDTO aux = new LancamentoDTO();
		aux.setContaId(conta.getId());
		aux.setValor(valor);
		aux.setTipo(Tipo.DEBITO);
		aux.setOperacao(Operacao.SAQUE);
		
		LancamentoDTO lancamento = insert(aux);
		return lancamento;
	}
	
	public LancamentoDTO deposito(String numero, Double valor) {
		Conta conta = contaRepository.findByNumero(numero)
				.orElseThrow(()-> new EntityNotFoundException("Conta não existe " + numero));
		
		List<Conta> listaContas = contaRepository.findAllByClienteId(conta.getCliente().getId());
		
		Double v = 0.0;
		for(Conta c : listaContas)
			v+=c.getSaldo();

		LancamentoDTO aux = new LancamentoDTO();
		aux.setContaId(conta.getId());
		aux.setValor(valor);
		aux.setTipo(Tipo.CREDITO);
		aux.setOperacao(Operacao.DEPOSITO);
		
		LancamentoDTO lancamento = insert(aux);
		if(valor > v) {
			aux.setContaId(conta.getId());
			aux.setValor(valor*0.1);
			aux.setTipo(Tipo.CREDITO);
			aux.setOperacao(Operacao.BONUS);
			insert(aux);
		}
		
		return lancamento;
	}
	
	public LancamentoDTO insert(LancamentoDTO lancamentoDTO) {
		Conta conta = contaRepository.findById(lancamentoDTO.getContaId())
				.orElseThrow(()-> new EntityNotFoundException("Conta não existe " + lancamentoDTO.getContaId()));
		
		Conta contaSalva;
		if(lancamentoDTO.getTipo().equals(Tipo.CREDITO)) {
			conta.setSaldo(conta.getSaldo() + lancamentoDTO.getValor());
			contaSalva = contaRepository.save(conta);
		}else {
			conta.setSaldo(conta.getSaldo() - lancamentoDTO.getValor());
			contaSalva = contaRepository.save(conta);
		}
		
		Lancamento lancamento = new Lancamento();
		lancamento.setConta(contaSalva);
		lancamento.setTipo(lancamentoDTO.getTipo());
		lancamento.setOperacao(lancamentoDTO.getOperacao());
		lancamento.setValor(lancamentoDTO.getValor());
		
		Lancamento lancamentoSalvo = lancamentoRepository.save(lancamento);
		return new LancamentoDTO(lancamentoSalvo);
	}
	
	public List<LancamentoDTO> findByClienteId(Long id){
		List<Lancamento> lista = lancamentoRepository.findByContaId(id);
		return lista.stream().map(LancamentoDTO::new).toList();
	}
}
