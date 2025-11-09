package com.comanda_digital.infrastructure.rest;


import com.comanda_digital.application.dto.endereco.AtualizarEnderecoRequestDTO;
import com.comanda_digital.application.dto.restaurante.AtualizarImagemRequestDTO;
import com.comanda_digital.application.dto.restaurante.ComercioRequestDTO;
import com.comanda_digital.application.dto.restaurante.ComercioResponseDTO;
import com.comanda_digital.application.dto.usuario.UsuarioResponseDTO;
import com.comanda_digital.application.port.ComercioServicePort;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@CrossOrigin
@RestController
@RequestMapping("/comercio")
@Tag(name = "Comercio", description = "Operações relacionadas a Comercio")
@AllArgsConstructor
public class ComercioController {

    private ComercioServicePort comercioServicePort;


    @Operation(summary = "Cadastra um novo comercio", description = "Cria um novo comercio no sistema")
    @ApiResponses(value = {@ApiResponse(responseCode = "201", description = "Comercio cadastrado com sucesso"), @ApiResponse(responseCode = "400", description = "Dados de entrada inválidos"),})
    @PostMapping
    public ResponseEntity<ComercioResponseDTO> cadastrarComercio(@RequestBody ComercioRequestDTO comercioRequestDTO) {
        ComercioResponseDTO responseDTO = comercioServicePort.cadastrarComercio(comercioRequestDTO);
        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }

    @Operation(summary = "Atualiza o endereço de um comercio", description = "Atualiza o endereço de um comercio existente")
    @ApiResponses(value = {@ApiResponse(responseCode = "204", description = "Endereço atualizado com sucesso"), @ApiResponse(responseCode = "400", description = "Dados de entrada inválidos"), @ApiResponse(responseCode = "404", description = "Comercio não encontrado"),})
    @PutMapping("/{comercioId}/endereco")
    public ResponseEntity<Void> atualizarEndereco(@PathVariable Long comercioId, @RequestBody AtualizarEnderecoRequestDTO atualizarEnderecoRequestDTO) {
        comercioServicePort.atualizarEndereco(comercioId, atualizarEnderecoRequestDTO);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Lista todos os comercios", description = "Lista todos os comercios com opção de filtro por nome e tipo")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Comercios listados com sucesso"),})
    @GetMapping
    public ResponseEntity<java.util.List<ComercioResponseDTO>> listarComercios(@RequestParam(required = false) String nome, @RequestParam(required = false) String tipo) {
        return ResponseEntity.ok(comercioServicePort.listarComercios(nome, tipo));
    }

    @Operation(summary = "Atualiza a imagem de um comercio", description = "Atualiza a imagem de um comercio existente")
    @ApiResponses(value = {@ApiResponse(responseCode = "204", description = "Imagem atualizada com sucesso"), @ApiResponse(responseCode = "400", description = "Dados de entrada inválidos"), @ApiResponse(responseCode = "404", description = "Comercio não encontrado"),})
    @PatchMapping("/{comercioId}/imagem")
    public ResponseEntity<Void> atualizarImagem(@PathVariable Long comercioId, @RequestBody AtualizarImagemRequestDTO atualizarImagemRequestDTO) {
        comercioServicePort.atualizarImagem(comercioId, atualizarImagemRequestDTO);
        return ResponseEntity.noContent().build();
    }
}


