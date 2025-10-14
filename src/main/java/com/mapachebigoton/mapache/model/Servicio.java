package com.mapachebigoton.mapache.model;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "servicios")
public class Servicio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_Servicio", nullable = false, length = 32)
    private Integer idServicio;

    @Column(name = "descripcion", nullable = false, length = 100)
    private String descripcion;

    @Column(name = "costo", nullable = false)
    private Long costo;

    @OneToMany(mappedBy = "servicio", fetch = FetchType.LAZY)
    private Set<Cita> citas = new HashSet<>();
}
