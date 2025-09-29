package com.comanda_digital.application.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

public class CardapioResponseDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private Long comercioId;
    private String nome;
    private LocalDateTime dataCriacao;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getComercioId() {
        return comercioId;
    }

    public void setComercioId(Long comercioId) {
        this.comercioId = comercioId;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }
}