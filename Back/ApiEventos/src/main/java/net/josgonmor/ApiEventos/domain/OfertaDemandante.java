package net.josgonmor.ApiEventos.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "Oferta_Demandante", schema = "eventos")
public class OfertaDemandante {
    @EmbeddedId
    private OfertaDemandanteId id;

    @MapsId("idOferta")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_oferta", nullable = false)
    private Oferta idOferta;

    @MapsId("idDemandante")
    @ManyToOne(fetch = FetchType.LAZY, optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_demandante", nullable = false)
    private Demandante idDemandante;

    @OneToMany(mappedBy = "ofertaDemandante")
    private Set<Comentario> comentarios = new LinkedHashSet<>();

}