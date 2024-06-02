package net.josgonmor.ApiEventos.dto;

import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link net.josgonmor.ApiEventos.domain.Demanda}
 */
@Value
public class DemandaDto implements Serializable {
    Long id;
    String title;
    String descripcion;
    String estado;
    DemandanteDto idDemandante;

    /**
     * DTO for {@link net.josgonmor.ApiEventos.domain.Demandante}
     */
    @Value
    public static class DemandanteDto implements Serializable {
        Long id;
        UsuarioDto usuario;
    }
}