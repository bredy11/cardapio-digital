package com.comanda_digital.infrastructure.cloud.record;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ImagemUploadRequest(
        @JsonProperty("imagemBase64") String imagemBase64,
        @JsonProperty("usuarioId") Long usuarioId
) {
}


