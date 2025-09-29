package com.comanda_digital.domain.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "cardapio")
public class Cardapio implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "comercio_id", nullable = false)
    private Comercio comercio;

    @Column(nullable = false, length = 100)
    private String nome;

    @Column(name = "data_criacao", updatable = false)
    private LocalDateTime dataCriacao;

    @PrePersist
    protected void onCreate() {
        dataCriacao = LocalDateTime.now();
    }
}