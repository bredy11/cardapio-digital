package com.comanda_digital.infrastructure.cloud;

import com.comanda_digital.infrastructure.cloud.record.ImagemUploadRequest;
import com.comanda_digital.infrastructure.cloud.record.ImagemUploadResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "imagem-service", url = "http://localhost:8081/v1/imagem")
public interface ImagemClient {

    @PostMapping(value = "/upload", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ImagemUploadResponse uploadImagem(@RequestBody ImagemUploadRequest imagemUploadRequest);
}
