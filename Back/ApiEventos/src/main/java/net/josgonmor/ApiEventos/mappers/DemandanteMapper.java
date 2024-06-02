package net.josgonmor.ApiEventos.mappers;

import net.josgonmor.ApiEventos.domain.Demandante;
import net.josgonmor.ApiEventos.domain.DemandanteDto;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring", uses = {UsuarioMapper.class})
public interface DemandanteMapper {
    DemandanteDto toDto(Demandante demandante);

    @AfterMapping
    default void linkDemandas(@MappingTarget Demandante demandante) {
        demandante.getDemandas().forEach(demanda -> demanda.setIdDemandante(demandante));
    }
}