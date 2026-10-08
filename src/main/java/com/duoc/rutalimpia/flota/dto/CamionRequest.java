package com.duoc.rutalimpia.flota.dto;

import com.duoc.rutalimpia.flota.model.enums.TipoResiduo;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.util.Set;

public record CamionRequest(

        @NotBlank(message = "es obligatoria")
        @Pattern(regexp = "^[A-Z]{4}\\d{2}$", message = "debe tener 4 letras mayúsculas y 2 números, ej: RLAB12")
        String patente,

        @Size(max = 80, message = "máximo 80 caracteres")
        String modelo,

        @NotNull(message = "es obligatoria")
        @Min(value = 1, message = "debe ser al menos 1")
        Integer capacidadParadas,

        @NotEmpty(message = "debe tener al menos un tipo")
        Set<TipoResiduo> tiposResiduo,

        Long conductorId
) {
}
