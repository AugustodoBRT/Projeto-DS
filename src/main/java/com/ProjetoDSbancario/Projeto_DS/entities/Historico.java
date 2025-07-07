package com.ProjetoDSbancario.Projeto_DS.entities;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "historico_acessos")
public class Historico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "data_acesso")
    private LocalDateTime dataAcesso;
    
    @Column(name = "endereco_ip")
    private String enderecoIp;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_id", nullable = false)
    @JsonIgnore
    private Cliente cliente;
    
    public Historico() {
        this.dataAcesso = LocalDateTime.now();
    }
    
    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public LocalDateTime getDataAcesso() { return dataAcesso; }
    public void setDataAcesso(LocalDateTime dataAcesso) { this.dataAcesso = dataAcesso; }
    
    public String getEnderecoIp() { return enderecoIp; }
    public void setEnderecoIp(String enderecoIp) { this.enderecoIp = enderecoIp; }
    
    public Cliente getCliente() { return cliente; }
    public void setCliente(Cliente cliente) { this.cliente = cliente; }
}
