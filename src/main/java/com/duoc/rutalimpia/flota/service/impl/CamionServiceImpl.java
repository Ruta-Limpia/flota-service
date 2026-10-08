package com.duoc.rutalimpia.flota.service.impl;

import com.duoc.rutalimpia.flota.dto.CamionRequest;
import com.duoc.rutalimpia.flota.dto.CamionResponse;
import com.duoc.rutalimpia.flota.exception.RecursoNoEncontradoException;
import com.duoc.rutalimpia.flota.exception.ReglaNegocioException;
import com.duoc.rutalimpia.flota.mapper.CamionMapper;
import com.duoc.rutalimpia.flota.model.Camion;
import com.duoc.rutalimpia.flota.model.enums.EstadoCamion;
import com.duoc.rutalimpia.flota.model.enums.TipoResiduo;
import com.duoc.rutalimpia.flota.repository.CamionRepository;
import com.duoc.rutalimpia.flota.service.CamionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CamionServiceImpl implements CamionService {

    private final CamionRepository repository;
    private final CamionMapper mapper;

    @Override
    @Transactional
    public CamionResponse crear(CamionRequest request) {
        if (repository.existsByPatente(request.patente())) {
            throw new ReglaNegocioException("Ya existe un camión con patente " + request.patente());
        }
        Camion camion = mapper.toEntity(request);
        camion.setEstado(EstadoCamion.DISPONIBLE);
        return mapper.toResponse(repository.save(camion));
    }

    @Override
    @Transactional(readOnly = true)
    public List<CamionResponse> listar(EstadoCamion estado) {
        List<Camion> camiones = (estado == null) ? repository.findAll() : repository.findByEstado(estado);
        return camiones.stream().map(mapper::toResponse).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public CamionResponse obtenerPorId(Long id) {
        return mapper.toResponse(buscar(id));
    }

    @Override
    @Transactional(readOnly = true)
    public CamionResponse obtenerPorConductor(Long conductorId) {
        Camion camion = repository.findByConductorId(conductorId)
                .orElseThrow(() -> new RecursoNoEncontradoException("No tienes camión asignado"));
        return mapper.toResponse(camion);
    }

    @Override
    @Transactional
    public CamionResponse actualizar(Long id, CamionRequest request) {
        Camion camion = buscar(id);
        boolean cambiaPatente = !camion.getPatente().equals(request.patente());
        if (cambiaPatente && repository.existsByPatente(request.patente())) {
            throw new ReglaNegocioException("Ya existe un camión con patente " + request.patente());
        }
        mapper.actualizar(camion, request);
        return mapper.toResponse(repository.save(camion));
    }

    @Override
    @Transactional
    public CamionResponse cambiarEstado(Long id, EstadoCamion estado) {
        Camion camion = buscar(id);
        camion.setEstado(estado);
        return mapper.toResponse(repository.save(camion));
    }

    @Override
    @Transactional
    public void eliminar(Long id) {
        repository.delete(buscar(id));
    }

    @Override
    @Transactional(readOnly = true)
    public List<CamionResponse> listarDisponibles(TipoResiduo tipoResiduo) {
        return repository.buscarDisponibles(EstadoCamion.DISPONIBLE, tipoResiduo).stream()
                .map(mapper::toResponse)
                .toList();
    }

    private Camion buscar(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("Camión " + id + " no encontrado"));
    }
}
