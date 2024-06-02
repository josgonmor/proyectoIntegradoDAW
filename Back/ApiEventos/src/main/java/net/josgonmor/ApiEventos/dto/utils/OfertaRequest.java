package net.josgonmor.ApiEventos.dto.utils;

import lombok.Data;
import net.josgonmor.ApiEventos.dto.CategoriaDto;

import java.time.LocalDate;
import java.util.List;

@Data
public class OfertaRequest {
    Long id;
    String title;
    String descripcion;
    LocalDate date;
    String localizacion;
    Double price;
    Long idOfertante;
    Long idDemandante;
    List<CategoriaDto> categorias;
}
