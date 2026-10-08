package com.duoc.rutalimpia.flota.service;

import com.duoc.rutalimpia.flota.dto.CamionRequest;
import com.duoc.rutalimpia.flota.dto.CamionResponse;
import com.duoc.rutalimpia.flota.model.enums.EstadoCamion;
import com.duoc.rutalimpia.flota.model.enums.TipoResiduo;

import java.util.List;

public interface CamionService {

    CamionResponse crear(CamionRequest request);

    List<CamionResponse> listar(EstadoCamion estado);

    CamionResponse obtenerPorId(Long id);

    CamionResponse obtenerPorConductor(Long conductorId);

    CamionResponse actualizar(Long id, CamionRequest request);

    CamionResponse cambiarEstado(Long id, EstadoCamion estado);

    void eliminar(Long id);

    List<CamionResponse> listarDisponibles(TipoResiduo tipoResiduo);
}
