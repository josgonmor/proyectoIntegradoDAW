package net.josgonmor.ApiEventos.services;

import net.josgonmor.ApiEventos.domain.Categoria;
import net.josgonmor.ApiEventos.domain.Oferta;
import net.josgonmor.ApiEventos.domain.Ofertante;
import net.josgonmor.ApiEventos.dto.OfertanteDto;

import java.util.Set;

public interface OfertanteService {

    OfertanteDto createUpdateOfertante(Ofertante ofertante);

    OfertanteDto getById(Long id);

    Ofertante getByIdCompleto(Long id);

}
