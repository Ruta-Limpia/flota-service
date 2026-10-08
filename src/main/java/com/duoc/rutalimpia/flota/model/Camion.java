package com.duoc.rutalimpia.flota.model;

import com.duoc.rutalimpia.flota.model.enums.EstadoCamion;
import com.duoc.rutalimpia.flota.model.enums.TipoResiduo;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "camiones")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Camion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "patente", nullable = false, unique = true, length = 10)
    private String patente;

    @Column(name = "modelo", length = 80)
    private String modelo;

    @Column(name = "capacidad_paradas", nullable = false)
    private Integer capacidadParadas;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "camion_tipos_residuo", joinColumns = @JoinColumn(name = "camion_id"))
    @Column(name = "tipo_residuo", length = 20)
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private Set<TipoResiduo> tiposResiduo = new HashSet<>();

    @Column(name = "conductor_id")
    private Long conductorId;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado", nullable = false, length = 20)
    private EstadoCamion estado;
}
