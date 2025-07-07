package com.ProjetoDSbancario.Projeto_DS.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ProjetoDSbancario.Projeto_DS.dto.ClienteDTO;
import com.ProjetoDSbancario.Projeto_DS.entities.Cliente;
import com.ProjetoDSbancario.Projeto_DS.repositories.ClienteRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    // Buscar todos os clientes
    public List<ClienteDTO> findAll() {
        List<Cliente> listaCliente = clienteRepository.findAll();
        return listaCliente.stream().map(ClienteDTO::new).toList();
    }

    // Buscar por ID
    public ClienteDTO findById(Long id) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Cliente não encontrado com ID: " + id));
        return new ClienteDTO(cliente);
    }

    // Inserir Cliente
    public ClienteDTO insert(ClienteDTO clienteDTO) {
        Cliente cliente = new Cliente();
        cliente.setNome(clienteDTO.getNome());
        cliente.setEmail(clienteDTO.getEmail());
        cliente.setCpf(clienteDTO.getCpf());
        cliente.setTelefone(clienteDTO.getTelefone());
        Cliente clienteSalvo = clienteRepository.save(cliente);
        return new ClienteDTO(clienteSalvo);
    }

    // Atualizar Cliente
    public ClienteDTO update(Long id, ClienteDTO novoClienteDTO) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Cliente não encontrado com ID: " + id));
        cliente.setNome(novoClienteDTO.getNome());
        cliente.setEmail(novoClienteDTO.getEmail());
        cliente.setCpf(novoClienteDTO.getCpf());
        cliente.setTelefone(novoClienteDTO.getTelefone());
        Cliente atualizado = clienteRepository.save(cliente);
        return new ClienteDTO(atualizado);
    }

    // Remover por ID
    public void delete(Long id) {
        if (!clienteRepository.existsById(id)) {
            throw new EntityNotFoundException("Cliente não encontrado com ID: " + id);
        }
        clienteRepository.deleteById(id);
    
    }
}