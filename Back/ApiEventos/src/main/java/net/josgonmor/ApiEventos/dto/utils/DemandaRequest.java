package net.josgonmor.ApiEventos.dto.utils;

import lombok.Data;

@Data
public class DemandaRequest {
    Long id;
    String title;
    String descripcion;
    String estado;
    Long idDemandante;
}
