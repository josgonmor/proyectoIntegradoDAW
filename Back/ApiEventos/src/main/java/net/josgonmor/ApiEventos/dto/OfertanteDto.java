package net.josgonmor.ApiEventos.dto;

import lombok.Value;
import net.josgonmor.ApiEventos.domain.Ofertante;

import java.io.Serializable;
import java.util.Set;

/**
 * DTO for {@link Ofertante}
 */
@Value
public class OfertanteDto implements Serializable {
    Long id;
    UsuarioDto usuario;
    Set<CompetenciaOfertanteDto> competenciaOfertantes;
}