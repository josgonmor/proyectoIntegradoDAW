package net.josgonmor.ApiEventos.services;

import net.josgonmor.ApiEventos.domain.Demandante;
import net.josgonmor.ApiEventos.domain.DemandanteDto;

public interface DemandanteService {
    DemandanteDto createOrUdateDemandante(Demandante demandante);
    DemandanteDto getById(Long id);
    Demandante getByIdCompleto(Long id);
}
