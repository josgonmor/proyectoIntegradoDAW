package net.josgonmor.ApiEventos.mappers;

import net.josgonmor.ApiEventos.domain.Oferta;
import net.josgonmor.ApiEventos.dto.OfertaDto;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface OfertaMapper {
    Oferta toEntity(OfertaDto ofertaDto);

    OfertaDto toDto(Oferta oferta);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Oferta partialUpdate(OfertaDto ofertaDto, @MappingTarget Oferta oferta);
}