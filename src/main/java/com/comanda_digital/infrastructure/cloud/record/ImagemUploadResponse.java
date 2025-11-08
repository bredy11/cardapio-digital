package com.comanda_digital.infrastructure.cloud.record;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ImagemUploadResponse(
        @JsonProperty("nomeUnico") String nomeUnico,
        @JsonProperty("urlAcesso") String urlAcesso
) {
}
