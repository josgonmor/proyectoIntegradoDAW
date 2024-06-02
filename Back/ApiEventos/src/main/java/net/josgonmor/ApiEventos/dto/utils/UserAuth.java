package net.josgonmor.ApiEventos.dto.utils;

import lombok.Data;
import net.josgonmor.ApiEventos.dto.RoleDto;

@Data
public class UserAuth {
    private String usuario;
    private String password;
    private String email;
    private String nombre;
    private String apellidos;
    private String role;
}
