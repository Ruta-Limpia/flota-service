package com.duoc.rutalimpia.flota.dto;

import com.duoc.rutalimpia.flota.model.enums.EstadoCamion;
import com.duoc.rutalimpia.flota.model.enums.TipoResiduo;

import java.util.Set;

public record CamionResponse(
        Long id,
        String patente,
        String modelo,
        Integer capacidadParadas,
        Set<TipoResiduo> tiposResiduo,
        Long conductorId,
        EstadoCamion estado
) {
}
