package net.josgonmor.ApiEventos.mappers;

import net.josgonmor.ApiEventos.domain.Demanda;
import net.josgonmor.ApiEventos.dto.DemandaDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface DemandaMapper {
    DemandaDto toDto(Demanda demanda);
}