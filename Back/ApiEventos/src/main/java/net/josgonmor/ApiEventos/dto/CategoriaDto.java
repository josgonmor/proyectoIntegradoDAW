package net.josgonmor.ApiEventos.dto;

import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link net.josgonmor.ApiEventos.domain.Categoria}
 */
@Value
public class CategoriaDto implements Serializable {
    Long id;
    String name;
}