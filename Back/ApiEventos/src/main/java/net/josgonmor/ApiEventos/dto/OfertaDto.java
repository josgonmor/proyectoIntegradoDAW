package net.josgonmor.ApiEventos.dto;

import lombok.Value;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

/**
 * DTO for {@link net.josgonmor.ApiEventos.domain.Oferta}
 */
@Value
public class OfertaDto implements Serializable {
    Long id;
    String title;
    String descripcion;
    LocalDate date;
    String localizacion;
    Double price;
    OfertanteDto idOfertante;
    AdminDto idAdminCheck;
    Set<CategoriaDto> categorias;
}