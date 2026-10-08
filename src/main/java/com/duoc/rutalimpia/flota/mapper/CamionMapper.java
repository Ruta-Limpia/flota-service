package com.duoc.rutalimpia.flota.mapper;

import com.duoc.rutalimpia.flota.dto.CamionRequest;
import com.duoc.rutalimpia.flota.dto.CamionResponse;
import com.duoc.rutalimpia.flota.model.Camion;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class CamionMapper {

    // El estado inicial lo pone el service
    public Camion toEntity(CamionRequest request) {
        return Camion.builder()
                .patente(request.patente())
                .modelo(request.modelo())
                .capacidadParadas(request.capacidadParadas())
                .tiposResiduo(new HashSet<>(request.tiposResiduo()))
                .conductorId(request.conductorId())
                .build();
    }

    public CamionResponse toResponse(Camion camion) {
        return new CamionResponse(
                camion.getId(),
                camion.getPatente(),
                camion.getModelo(),
                camion.getCapacidadParadas(),
                Set.copyOf(camion.getTiposResiduo()),
                camion.getConductorId(),
                camion.getEstado()
        );
    }

    public void actualizar(Camion camion, CamionRequest request) {
        camion.setPatente(request.patente());
        camion.setModelo(request.modelo());
        camion.setCapacidadParadas(request.capacidadParadas());
        camion.getTiposResiduo().clear();
        camion.getTiposResiduo().addAll(request.tiposResiduo());
        camion.setConductorId(request.conductorId());
    }
}
