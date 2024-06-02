package net.josgonmor.ApiEventos.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "Competencia", schema = "eventos")
public class Competencia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name")
    private String name;

    @ManyToMany(mappedBy = "competencias")
    private Set<Categoria> categorias = new LinkedHashSet<>();

    @OneToMany(mappedBy = "competencia")
    private Set<CompetenciaOfertante> competenciaOfertantes = new LinkedHashSet<>();

}