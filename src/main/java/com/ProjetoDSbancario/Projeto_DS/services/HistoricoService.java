package com.ProjetoDSbancario.Projeto_DS.services;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ProjetoDSbancario.Projeto_DS.dto.HistoricoDTO;
import com.ProjetoDSbancario.Projeto_DS.entities.Cliente;
import com.ProjetoDSbancario.Projeto_DS.entities.Historico;
import com.ProjetoDSbancario.Projeto_DS.repositories.ClienteRepository;
import com.ProjetoDSbancario.Projeto_DS.repositories.HistoricoRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class HistoricoService {

    @Autowired
    private HistoricoRepository historicoRepository;

    @Autowired
    private ClienteRepository clienteRepository; 
    
    // Buscar todo o histórico
    public List<HistoricoDTO> findAll() {
        List<Historico> listaHistorico = historicoRepository.findAll();
        return listaHistorico.stream().map(HistoricoDTO::new).toList();
    }

    // Buscar por ID
    public HistoricoDTO findById(Long id) {
        Historico historico = historicoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Histórico não encontrado com ID: " + id));
        return new HistoricoDTO(historico);
    }

    // Inserir Histórico
    public HistoricoDTO insert(String ip, String login) {
    	Historico historico = new Historico();
    	historico.setDataAcesso(LocalDateTime.now());
    	historico.setEnderecoIp(ip);
    	
    	Cliente cliente = clienteRepository.findByCpf(login).orElseThrow(() -> new EntityNotFoundException("Cliente não existe"));
    	historico.setCliente(cliente);
    	Historico historicoSalvo = historicoRepository.save(historico);
    	
    	return new HistoricoDTO(historicoSalvo);
    }

    /*
    // Atualizar Histórico
    public HistoricoDTO update(Long id, HistoricoDTO novoHistoricoDTO) {
        Historico historico = historicoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Histórico não encontrado com ID: " + id));
        historico.setDataAcesso(novoHistoricoDTO.getDataAcesso());
        historico.setEnderecoIp(novoHistoricoDTO.getEnderecoIp());
        historico.setCliente(novoHistoricoDTO.getCliente());
        Historico atualizado = historicoRepository.save(historico);
        return new HistoricoDTO(
                atualizado.getId(),
                atualizado.getDataAcesso(),
                atualizado.getEnderecoIp(),
                atualizado.getCliente()
        );
    }
*/
    // Remover por ID
    public void delete(Long id) {
        if (!historicoRepository.existsById(id)) {
            throw new EntityNotFoundException("Histórico não encontrado com ID: " + id);
        }
        historicoRepository.deleteById(id);
    }
    
    public List<HistoricoDTO> findByClienteId(Long id){
    	if(!clienteRepository.existsById(id))
    		throw new EntityNotFoundException("Esse cliente não existe!");
    	List<Historico> lista = historicoRepository.findByClienteId(id);
    	return lista.stream().map(HistoricoDTO::new).toList();
    }
}