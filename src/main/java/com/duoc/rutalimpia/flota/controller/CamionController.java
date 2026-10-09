package com.duoc.rutalimpia.flota.controller;

import com.duoc.rutalimpia.flota.dto.CambiarEstadoCamionRequest;
import com.duoc.rutalimpia.flota.dto.CamionRequest;
import com.duoc.rutalimpia.flota.dto.CamionResponse;
import com.duoc.rutalimpia.flota.model.enums.EstadoCamion;
import com.duoc.rutalimpia.flota.model.enums.TipoResiduo;
import com.duoc.rutalimpia.flota.service.CamionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class CamionController {

    private final CamionService service;

    @PostMapping("/camiones")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CamionResponse> crear(@Valid @RequestBody CamionRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.crear(request));
    }

    @GetMapping("/camiones")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<CamionResponse>> listar(@RequestParam(required = false) EstadoCamion estado) {
        return ResponseEntity.ok(service.listar(estado));
    }

    // Ruta fija: va antes que /camiones/{id}
    @GetMapping("/camiones/mi-camion")
    @PreAuthorize("hasRole('CONDUCTOR')")
    public ResponseEntity<CamionResponse> obtenerMiCamion(Authentication authentication) {
        Long conductorId = (Long) authentication.getPrincipal();
        return ResponseEntity.ok(service.obtenerPorConductor(conductorId));
    }

    @GetMapping("/camiones/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CamionResponse> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.obtenerPorId(id));
    }

    @PutMapping("/camiones/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CamionResponse> actualizar(@PathVariable Long id, @Valid @RequestBody CamionRequest request) {
        return ResponseEntity.ok(service.actualizar(id, request));
    }

    @PatchMapping("/camiones/{id}/estado")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CamionResponse> cambiarEstado(@PathVariable Long id,
                                                        @Valid @RequestBody CambiarEstadoCamionRequest request) {
        return ResponseEntity.ok(service.cambiarEstado(id, request.estado()));
    }

    @DeleteMapping("/camiones/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/internal/camiones/disponibles")
    public ResponseEntity<List<CamionResponse>> listarDisponibles(@RequestParam TipoResiduo tipoResiduo) {
        return ResponseEntity.ok(service.listarDisponibles(tipoResiduo));
    }
}
