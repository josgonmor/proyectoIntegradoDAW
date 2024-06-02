package net.josgonmor.ApiEventos.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "Comentario", schema = "eventos")
public class Comentario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "calificacion")
    private Integer calificacion;

    @Lob
    @Column(name = "descripcion")
    private String descripcion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({
            @JoinColumn(name = "id_oferta", referencedColumnName = "id_oferta"),
            @JoinColumn(name = "id_demandante", referencedColumnName = "id_demandante")
    })
    private OfertaDemandante ofertaDemandante;

}