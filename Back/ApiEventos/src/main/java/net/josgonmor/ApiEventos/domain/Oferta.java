package net.josgonmor.ApiEventos.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "Oferta")
public class Oferta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "title")
    private String title;

    @Lob
    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "date")
    private LocalDate date;

    @Column(name = "localizacion")
    private String localizacion;

    @Column(name = "price")
    private Double price;

    @Column(name = "estado")
    private String estado;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_ofertante")
    private Ofertante idOfertante;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_admin_check")
    private Admin idAdminCheck;

    @ManyToMany(mappedBy = "ofertas", cascade = { CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
    private Set<Categoria> categorias = new LinkedHashSet<>();

    @OneToMany(mappedBy = "idOferta")
    private Set<Post> posts = new LinkedHashSet<>();

    @OneToMany(mappedBy = "idOferta", cascade = CascadeType.ALL)
    private Set<OfertaDemandante> ofertaDemandantes = new LinkedHashSet<>();
}