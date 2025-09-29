package com.comanda_digital.application.dto;

import java.io.Serializable;

public class CardapioRequestDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long comercioId;
    private String nome;

    // Getters and Setters
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
}