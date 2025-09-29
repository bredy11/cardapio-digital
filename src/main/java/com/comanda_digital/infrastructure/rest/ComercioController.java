package com.comanda_digital.infrastructure.rest;

import com.comanda_digital.application.dto.ComercioRequestDTO;
import com.comanda_digital.application.dto.ComercioResponseDTO;
import com.comanda_digital.application.port.ComercioServicePort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/comercios")
public class ComercioController {

    private final ComercioServicePort comercioService;

    public ComercioController(ComercioServicePort comercioService) {
        this.comercioService = comercioService;
    }

    @PostMapping
    public ResponseEntity<ComercioResponseDTO> createComercio(@RequestBody ComercioRequestDTO requestDTO) {
        ComercioResponseDTO responseDTO = comercioService.createComercio(requestDTO);
        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ComercioResponseDTO> getComercioById(@PathVariable Long id) {
        ComercioResponseDTO responseDTO = comercioService.getComercioById(id);
        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping
    public ResponseEntity<List<ComercioResponseDTO>> getAllComercios() {
        List<ComercioResponseDTO> comercios = comercioService.getAllComercios();
        return ResponseEntity.ok(comercios);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ComercioResponseDTO> updateComercio(@PathVariable Long id, @RequestBody ComercioRequestDTO requestDTO) {
        ComercioResponseDTO updatedComercio = comercioService.updateComercio(id, requestDTO);
        return ResponseEntity.ok(updatedComercio);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteComercio(@PathVariable Long id) {
        comercioService.deleteComercio(id);
        return ResponseEntity.noContent().build();
    }
}