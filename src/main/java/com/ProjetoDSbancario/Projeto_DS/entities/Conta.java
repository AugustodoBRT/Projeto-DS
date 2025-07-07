package com.ProjetoDSbancario.Projeto_DS.entities;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "tbContas")
public class Conta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(unique = true, nullable = false, length = 9)
    private String numero;
    
    @Column(precision = 15)
    private Double saldo = 0.0;
    
    @Column(name = "limite_credito", precision = 15)
    private Double limiteCredito = 0.0;
    
    @Column(name = "chave_pix", unique = true)
    private String chavePix;
    
    @Column(name = "data_criacao")
    private LocalDateTime dataCriacao;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_id", nullable = false)
    @JsonIgnore
    private Cliente cliente;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public Double getSaldo() {
        return saldo;
    }

    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }

    public Double getLimiteCredito() {
        return limiteCredito;
    }

    public void setLimiteCredito(Double limiteCredito) {
        this.limiteCredito = limiteCredito;
    }

    public String getChavePix() {
        return chavePix;
    }

    public void setChavePix(String chavePix) {
        this.chavePix = chavePix;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Conta() {
    }

    public Conta(Long id, String numero, Double saldo, Double limiteCredito, String chavePix, LocalDateTime dataCriacao,
            Cliente cliente) {
        this.id = id;
        this.numero = numero;
        this.saldo = saldo;
        this.limiteCredito = limiteCredito;
        this.chavePix = chavePix;
        this.dataCriacao = dataCriacao;
        this.cliente = cliente;
    }

	@Override
	public int hashCode() {
		return Objects.hash(chavePix, cliente, dataCriacao, id, limiteCredito, numero, saldo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Conta other = (Conta) obj;
		return Objects.equals(chavePix, other.chavePix) && Objects.equals(cliente, other.cliente)
				&& Objects.equals(dataCriacao, other.dataCriacao) && Objects.equals(id, other.id)
				&& Objects.equals(limiteCredito, other.limiteCredito) && Objects.equals(numero, other.numero)
				&& Objects.equals(saldo, other.saldo);
	}

   
}



    
    
    