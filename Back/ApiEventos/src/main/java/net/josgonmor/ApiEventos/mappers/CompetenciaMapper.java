package net.josgonmor.ApiEventos.mappers;

import net.josgonmor.ApiEventos.domain.Competencia;
import net.josgonmor.ApiEventos.dto.CompetenciaDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface CompetenciaMapper {
    CompetenciaDto toDto(Competencia competencia);
}