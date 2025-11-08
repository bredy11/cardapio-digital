package com.comanda_digital.application.dto.usuario;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioResponseDTO implements Serializable {

    private Long id;
    private String nome;
    private String email;
    private String telefone;
    private String status;
    private LocalDateTime dataCriacao;
}