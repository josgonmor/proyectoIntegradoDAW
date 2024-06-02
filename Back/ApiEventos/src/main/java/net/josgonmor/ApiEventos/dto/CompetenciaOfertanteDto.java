package net.josgonmor.ApiEventos.dto;

import lombok.Value;
import net.josgonmor.ApiEventos.domain.CompetenciaOfertante;
import net.josgonmor.ApiEventos.dto.CompetenciaDto;

import java.io.Serializable;

/**
 * DTO for {@link CompetenciaOfertante}
 */
@Value
public class CompetenciaOfertanteDto implements Serializable {
    CompetenciaDto competencia;
    String estado;
}