package net.josgonmor.ApiEventos.dto;

import lombok.Value;
import net.josgonmor.ApiEventos.domain.Competencia;
import net.josgonmor.ApiEventos.dto.CategoriaDto;

import java.io.Serializable;
import java.util.Set;

/**
 * DTO for {@link Competencia}
 */
@Value
public class CompetenciaDto implements Serializable {
    Long id;
    String name;
    Set<CategoriaDto> categorias;
}