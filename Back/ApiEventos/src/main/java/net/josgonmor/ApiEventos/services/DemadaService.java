package net.josgonmor.ApiEventos.services;

import net.josgonmor.ApiEventos.domain.Demanda;
import net.josgonmor.ApiEventos.domain.Demandante;
import net.josgonmor.ApiEventos.domain.DemandanteDto;
import net.josgonmor.ApiEventos.domain.Usuario;
import net.josgonmor.ApiEventos.dto.DemandaDto;
import org.springframework.data.crossstore.ChangeSetPersister;

import java.util.List;
import java.util.Set;

public interface DemadaService {
    List<DemandaDto> findALl();

    List<DemandaDto> findByUsuario(String usuario);

    List<DemandaDto> findByCategoriasIds(Set<Long> ids);

    DemandaDto findById(Long id);
    Demanda findByIdCompleto(Long id);
    Set<Demanda> findByIdDemandante(Demandante demandante);
}
