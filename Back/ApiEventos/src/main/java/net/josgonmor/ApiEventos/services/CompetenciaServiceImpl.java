package net.josgonmor.ApiEventos.services;

import net.josgonmor.ApiEventos.dto.CompetenciaDto;
import net.josgonmor.ApiEventos.mappers.CompetenciaMapper;
import net.josgonmor.ApiEventos.repositories.CompetenciaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CompetenciaServiceImpl implements CompentenciaService{

    private final CompetenciaRepository competenciaRepository;
    private final CompetenciaMapper competenciaMapper;

    public CompetenciaServiceImpl(CompetenciaRepository competenciaRepository,
                                  CompetenciaMapper competenciaMapper) {
        this.competenciaRepository = competenciaRepository;
        this.competenciaMapper = competenciaMapper;
    }

    @Override
    public List<CompetenciaDto> findAll() {
        return competenciaRepository.findAll().stream().map(competenciaMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public List<CompetenciaDto> findByOfertanteId(Long id) {
        return competenciaRepository.findByOfertanteId(id).stream().map(competenciaMapper::toDto).collect(Collectors.toList());
    }
}
