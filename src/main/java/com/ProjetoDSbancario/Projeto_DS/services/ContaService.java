package com.ProjetoDSbancario.Projeto_DS.services;

import java.security.SecureRandom;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ProjetoDSbancario.Projeto_DS.dto.ContaDTO;
import com.ProjetoDSbancario.Projeto_DS.entities.Cliente;
import com.ProjetoDSbancario.Projeto_DS.entities.Conta;
import com.ProjetoDSbancario.Projeto_DS.repositories.ClienteRepository;
import com.ProjetoDSbancario.Projeto_DS.repositories.ContaRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ContaService {

    @Autowired
    private ContaRepository contaRepository;

    @Autowired
    private ClienteRepository clienteRepository;
    
    // Buscar todos as contas
    public List<ContaDTO> findAll() {
        List<Conta> listaConta = contaRepository.findAll();
        return listaConta.stream().map(ContaDTO::new).toList();
    }

    // Buscar por ID
    public ContaDTO findById(Long id) {
        Conta conta = contaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Conta não encontrada com ID: " + id));
        return new ContaDTO(conta);
    }

	public String gerarNumero() {
		String alfabeto = "ABCDEFGHJKLMNPQRSTUVWXYZ";
		
		SecureRandom random = new SecureRandom();
		StringBuilder senha = new StringBuilder();
		
		for(int i = 0; i < 2; i++) {
			int index = random.nextInt(alfabeto.length());
			senha.append(alfabeto.charAt(index));
		}
		
		for(int i = 0; i < 6; i++) {
			int index = random.nextInt(10);
			senha.append(index);
		}
		
		return senha.toString();
	}
    
    // Inserir Conta
    public ContaDTO insert(ContaDTO contaDTO) {
    	Cliente cliente = clienteRepository.findById(contaDTO.getClienteId()).orElseThrow(()->new EntityNotFoundException("Não existe um cliente com esse id! "));
		
		Conta conta = new Conta();
		String numero = gerarNumero();
		while(contaRepository.existsByNumero(numero))
			numero = gerarNumero();
		
		conta.setChavePix(contaDTO.getChavePix());
		conta.setCliente(cliente);
		conta.setLimiteCredito(0.0);
		conta.setSaldo(0.0);
		conta.setNumero(numero);
		
		Conta contaSalva = contaRepository.save(conta);
		return new ContaDTO(contaSalva);
    }

    /*
    // Atualizar Conta
    public ContaDTO update(Long id, ContaDTO novoContaDTO) {
        Conta conta = contaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Conta não encontrado com ID: " + id));
        conta.setNumero(novoContaDTO.getNumero());
        conta.setSaldo(novoContaDTO.getSaldo());
        conta.setLimiteCredito(novoContaDTO.getLimiteCredito());
        conta.setChavePix(novoContaDTO.getChavePix());
        Conta atualizado = contaRepository.save(conta);
        return new ContaDTO(atualizado);
    }*/

    // Remover por ID
    public void delete(Long id) {
        if (!contaRepository.existsById(id)) {
            throw new EntityNotFoundException("Conta não encontrado com ID: " + id);
        }
        contaRepository.deleteById(id);
    }

	public ContaDTO alterarChavePix(Long id, String chavePix) {
		Conta conta = contaRepository.findById(id).orElseThrow(()->new EntityNotFoundException("Essa conta não existe "+id));
		if(contaRepository.existsByChavePix(chavePix))
			throw new IllegalArgumentException("Essa chave pix já está cadastrada no sistema! " + chavePix);
		
		conta.setChavePix(chavePix);
		Conta contaSalva = contaRepository.save(conta);
		return new ContaDTO(contaSalva);
	}

	public ContaDTO alterarLimiteCredito(Long id, Double novoLimite) {
		Conta conta = contaRepository.findById(id).orElseThrow(()->new EntityNotFoundException("Essa conta não existe "+id));
		
		if(novoLimite <= conta.getLimiteCredito())
			throw new IllegalArgumentException("Não é possível diminuir o limite de crédito ");
		
		conta.setLimiteCredito(novoLimite);
		Conta contaSalva = contaRepository.save(conta);
		return new ContaDTO(contaSalva);
	}
}