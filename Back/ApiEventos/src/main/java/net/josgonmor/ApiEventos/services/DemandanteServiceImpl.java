package net.josgonmor.ApiEventos.services;

import net.josgonmor.ApiEventos.domain.Demandante;
import net.josgonmor.ApiEventos.domain.DemandanteDto;
import net.josgonmor.ApiEventos.dto.DemandaDto;
import net.josgonmor.ApiEventos.mappers.DemandanteMapper;
import net.josgonmor.ApiEventos.repositories.DemandanteRepository;
import org.springframework.stereotype.Service;

@Service
public class DemandanteServiceImpl implements DemandanteService{

    private final DemandanteRepository demandanteRepository;
    private final DemandanteMapper demandanteMapper;

    public DemandanteServiceImpl(DemandanteRepository demandanteRepository,
                                 DemandanteMapper demandanteMapper) {
        this.demandanteRepository = demandanteRepository;
        this.demandanteMapper = demandanteMapper;
    }

    @Override
    public DemandanteDto createOrUdateDemandante(Demandante demandante) {
        return demandanteMapper.toDto(demandanteRepository.save(demandante));
    }

    @Override
    public DemandanteDto getById(Long id) {
        return demandanteMapper.toDto(demandanteRepository.getDemandanteById(id));
    }

    @Override
    public Demandante getByIdCompleto(Long id) {
        return this.demandanteRepository.getDemandanteById(id);
    }
}
