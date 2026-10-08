package com.duoc.rutalimpia.flota.repository;

import com.duoc.rutalimpia.flota.model.Camion;
import com.duoc.rutalimpia.flota.model.enums.EstadoCamion;
import com.duoc.rutalimpia.flota.model.enums.TipoResiduo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CamionRepository extends JpaRepository<Camion, Long> {

    boolean existsByPatente(String patente);

    Optional<Camion> findByConductorId(Long conductorId);

    List<Camion> findByEstado(EstadoCamion estado);

    @Query("select distinct c from Camion c join c.tiposResiduo t " +
           "where c.estado = :estado and t = :tipo and c.conductorId is not null")
    List<Camion> buscarDisponibles(@Param("estado") EstadoCamion estado, @Param("tipo") TipoResiduo tipo);
}
