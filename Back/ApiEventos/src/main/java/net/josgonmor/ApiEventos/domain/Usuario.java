package net.josgonmor.ApiEventos.domain;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "Usuario", schema = "eventos")
public class Usuario{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "usuario", nullable = false)
    private String usuario;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "apellidos", nullable = false)
    private String apellidos;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_role")
    private Role role;

    @Column(name = "activo", nullable = false)
    private Boolean activo = false;

    @OneToOne(mappedBy = "usuario")
    private Admin admin;

    @OneToOne(mappedBy = "usuario")
    private Demandante demandante;

    @OneToOne(mappedBy = "usuario")
    private Ofertante ofertante;

    @OneToMany(mappedBy = "idUsuario", fetch = FetchType.EAGER)
    private Set<Post> posts = new LinkedHashSet<>();

}