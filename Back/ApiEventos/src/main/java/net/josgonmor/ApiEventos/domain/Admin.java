package net.josgonmor.ApiEventos.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "Admin", schema = "eventos")
public class Admin {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @MapsId
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id", nullable = false)
    private Usuario usuario;

    @OneToMany(mappedBy = "idAdmin")
    private Set<CompetenciaOfertante> competenciaOfertantes = new LinkedHashSet<>();

    @OneToMany(mappedBy = "idAdminCheck")
    private Set<Demanda> demandas = new LinkedHashSet<>();

    @OneToMany(mappedBy = "idAdminCheck")
    private Set<Oferta> ofertas = new LinkedHashSet<>();

}