package com.comanda_digital.application.dto.restaurante;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ComercioResponseDTO implements Serializable {


    private Long id;
    private Long usuarioId;
    private String nome;
    private String tipo;
    private String instagram;
    private String facebock;
    private String whatsapp;


}
