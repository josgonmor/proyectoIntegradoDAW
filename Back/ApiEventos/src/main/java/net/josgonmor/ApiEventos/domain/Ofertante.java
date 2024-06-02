package net.josgonmor.ApiEventos.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "Ofertante", schema = "eventos")
public class Ofertante {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @MapsId
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id", nullable = false)
    private Usuario usuario;

    @OneToMany(mappedBy = "ofertante")
    private Set<CompetenciaOfertante> competenciaOfertantes = new LinkedHashSet<>();

    @OneToMany(mappedBy = "idOfertante")
    private Set<Oferta> ofertas = new LinkedHashSet<>();

}