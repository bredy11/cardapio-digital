package com.comanda_digital.application.dto.restaurante;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ComercioRequestDTO implements Serializable {

    @NotNull(message = "O ID do usuário não pode ser nulo.")
    private Long usuarioId;

    @NotBlank(message = "O nome é obrigatório.")
    @Size(max = 100, message = "O nome não pode ter mais de 100 caracteres.")
    private String nome;

    private String tipo;

    private String instagram;

    private String facebook;

    private String whatsapp;


    @NotBlank(message = "O CEP é obrigatório.")
    private String cep;

    @NotBlank(message = "O logradouro é obrigatório.")
    private String logradouro;

    @NotBlank(message = "O bairro é obrigatório.")
    private String bairro;

    @NotBlank(message = "A cidade é obrigatória.")
    private String cidade;

    @NotBlank(message = "O estado é obrigatório.")
    @Size(min = 2, max = 2, message = "O estado deve ter 2 caracteres (sigla).")
    private String estado;

    @NotBlank(message = "O número é obrigatório.")
    @Size(max = 10, message = "O número não pode ter mais de 10 caracteres.")
    private String numero;

    private String complemento;

    private MultipartFile imagem;
}
