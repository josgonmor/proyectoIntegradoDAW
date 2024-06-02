package net.josgonmor.ApiEventos.services;

import io.jsonwebtoken.ExpiredJwtException;
import net.josgonmor.ApiEventos.domain.Demanda;
import net.josgonmor.ApiEventos.domain.Demandante;
import net.josgonmor.ApiEventos.dto.DemandaDto;
import net.josgonmor.ApiEventos.mappers.DemandaMapper;
import net.josgonmor.ApiEventos.repositories.DemandaRepository;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class DemandaServiceImpl implements DemadaService{

    private final DemandaRepository demandaRepository;
    private final DemandaMapper demandaMapper;

    public DemandaServiceImpl(DemandaRepository demandaRepository,
                              DemandaMapper demandaMapper) {
        this.demandaRepository = demandaRepository;
        this.demandaMapper = demandaMapper;
    }


    @Override
    public List<DemandaDto> findALl() {
        return demandaRepository.findAll().stream().map(demandaMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public List<DemandaDto> findByUsuario(String usuario) {
        return demandaRepository.findByIdDemandanteUsuarioUsuario(usuario).orElseThrow().stream().map(demandaMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public List<DemandaDto> findByCategoriasIds(Set<Long> ids) {
        return demandaRepository.findByCategoriaIds(ids).stream().map(demandaMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public DemandaDto findById(Long id) {
        return demandaMapper.toDto(demandaRepository.findById(id).orElseThrow());
    }

    @Override
    public Demanda findByIdCompleto(Long id) {
        return demandaRepository.findById(id).orElseThrow();
    }

    @Override
    public Set<Demanda> findByIdDemandante(Demandante demandante) {
        return demandaRepository.findByIdDemandante(demandante).orElseThrow();
    }
}
