package net.josgonmor.ApiEventos.dto;

import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link net.josgonmor.ApiEventos.domain.Role}
 */
@Value
public class RoleDto implements Serializable {
    Long id;
    String nombre;
}