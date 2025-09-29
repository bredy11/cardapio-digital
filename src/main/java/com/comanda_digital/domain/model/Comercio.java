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
@Table(name = "comercio")
public class Comercio implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @Column(nullable = false, length = 100)
    private String nome;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tipo_id", nullable = false)
    private TipoComercio tipo;

    @Column(length = 150)
    private String instagram;

    @Column(length = 150)
    private String facebook;

    @Column(length = 50)
    private String whatsapp;

    @Column(name = "data_criacao", updatable = false)
    private LocalDateTime dataCriacao;

    @PrePersist
    protected void onCreate() {
        dataCriacao = LocalDateTime.now();
    }
}