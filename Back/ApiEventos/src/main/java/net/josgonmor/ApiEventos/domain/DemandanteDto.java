package net.josgonmor.ApiEventos.domain;

import lombok.Value;
import net.josgonmor.ApiEventos.dto.DemandaDto;
import net.josgonmor.ApiEventos.dto.UsuarioDto;

import java.io.Serializable;
import java.util.Set;

/**
 * DTO for {@link Demandante}
 */
@Value
public class DemandanteDto implements Serializable {
    Long id;
    UsuarioDto usuario;
    Set<DemandaDto> demandas;
}