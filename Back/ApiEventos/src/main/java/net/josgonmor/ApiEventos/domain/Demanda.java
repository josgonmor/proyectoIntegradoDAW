package net.josgonmor.ApiEventos.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "Demanda", schema = "eventos")
public class Demanda {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "title")
    private String title;

    @Lob
    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "estado")
    private String estado;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_demandante")
    private Demandante idDemandante;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_admin_check")
    private Admin idAdminCheck;

    @ManyToMany(mappedBy = "demandas")
    private Set<Categoria> categorias = new LinkedHashSet<>();

    @OneToMany(mappedBy = "idDemanda")
    private Set<Post> posts = new LinkedHashSet<>();

}