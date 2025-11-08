package com.comanda_digital.infrastructure.rest;


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
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Comercio cadastrado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados de entrada inválidos"),
    })
    @PostMapping(consumes = "multipart/form-data")
    public ResponseEntity<ComercioResponseDTO> cadastrarComercio(@RequestPart("comercio") ComercioRequestDTO comercioRequestDTO,
                                                                 @RequestPart("imagem") MultipartFile imagem) {
        comercioRequestDTO.setImagem(imagem);
        ComercioResponseDTO responseDTO = comercioServicePort.cadastrarComercio(comercioRequestDTO);
        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }

}


