package net.josgonmor.ApiEventos.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "Demandante", schema = "eventos")
public class Demandante {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @MapsId
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id", nullable = false)
    private Usuario usuario;

    @OneToMany(mappedBy = "idDemandante")
    private Set<Demanda> demandas = new LinkedHashSet<>();


    @OneToMany(mappedBy = "idDemandante", cascade = CascadeType.ALL)
    private Set<OfertaDemandante> ofertaDemandantes = new LinkedHashSet<>();
}