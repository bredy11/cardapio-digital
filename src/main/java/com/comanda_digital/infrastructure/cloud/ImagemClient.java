package com.comanda_digital.infrastructure.cloud;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

@FeignClient(name = "imagem-service", url = "http://localhost:8081/v1/imagem")
public interface ImagemClient {

    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    ImagemUploadResponse uploadImagem(@RequestPart("arquivo") MultipartFile arquivo, @RequestParam("usuarioId") Long usuarioId);
}
