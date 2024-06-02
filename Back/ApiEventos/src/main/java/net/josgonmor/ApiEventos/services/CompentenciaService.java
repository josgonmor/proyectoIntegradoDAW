package net.josgonmor.ApiEventos.services;

import net.josgonmor.ApiEventos.dto.CompetenciaDto;

import java.util.List;

public interface CompentenciaService {
    List<CompetenciaDto> findAll();

    List<CompetenciaDto> findByOfertanteId(Long id);
}
