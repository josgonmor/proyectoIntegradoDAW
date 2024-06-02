package net.josgonmor.ApiEventos.mappers;

import net.josgonmor.ApiEventos.domain.Ofertante;
import net.josgonmor.ApiEventos.dto.OfertanteDto;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring", uses = {UsuarioMapper.class})public interface OfertanteMapper {
    OfertanteDto toDto(Ofertante ofertante);

    @AfterMapping
    default void linkCompetenciaOfertantes(@MappingTarget Ofertante ofertante) {
        ofertante.getCompetenciaOfertantes().forEach(competenciaOfertante -> competenciaOfertante.setOfertante(ofertante));
    }
}