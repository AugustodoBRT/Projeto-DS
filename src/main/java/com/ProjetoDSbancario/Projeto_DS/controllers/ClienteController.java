package com.ProjetoDSbancario.Projeto_DS.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ProjetoDSbancario.Projeto_DS.dto.ClienteDTO;
import com.ProjetoDSbancario.Projeto_DS.services.ClienteService;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping("/{id}")
    public ResponseEntity<ClienteDTO> findById(@PathVariable Long id) {
        ClienteDTO clienteDTO = clienteService.findById(id);
        return ResponseEntity.ok(clienteDTO);
    }

    @GetMapping
    public ResponseEntity<List<ClienteDTO>> findAll() {
        List<ClienteDTO> clienteDTOs = clienteService.findAll();
        return ResponseEntity.ok(clienteDTOs);
    }

    @PostMapping
    public ResponseEntity<ClienteDTO> create(@RequestBody ClienteDTO clienteDTO) {
        ClienteDTO novoCliente = clienteService.insert(clienteDTO);
        return ResponseEntity.status(201).body(novoCliente);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClienteDTO> update(@PathVariable Long id, @RequestBody ClienteDTO clienteDTO) {
        ClienteDTO clienteAtualizado = clienteService.update(id, clienteDTO);
        return ResponseEntity.ok(clienteAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        clienteService.delete(id);
        return ResponseEntity.noContent().build();
    }
    
    
}