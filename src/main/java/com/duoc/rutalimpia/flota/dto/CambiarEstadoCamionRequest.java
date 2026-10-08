package com.duoc.rutalimpia.flota.dto;

import com.duoc.rutalimpia.flota.model.enums.EstadoCamion;
import jakarta.validation.constraints.NotNull;

public record CambiarEstadoCamionRequest(

        @NotNull(message = "es obligatorio")
        EstadoCamion estado
) {
}
