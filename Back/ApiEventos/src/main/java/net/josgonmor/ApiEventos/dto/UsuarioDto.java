package net.josgonmor.ApiEventos.dto;

import lombok.Data;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link net.josgonmor.ApiEventos.domain.Usuario}
 */
@Value
@Data
public class UsuarioDto implements Serializable {
    Long id;
    String usuario;
//    String password;
    String email;
    String nombre;
    String apellidos;
//    RoleDto idRole;
//    Boolean activo;
}