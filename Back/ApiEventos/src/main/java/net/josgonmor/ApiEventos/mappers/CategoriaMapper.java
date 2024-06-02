package net.josgonmor.ApiEventos.mappers;

import net.josgonmor.ApiEventos.domain.Categoria;
import net.josgonmor.ApiEventos.dto.CategoriaDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface CategoriaMapper {
    CategoriaDto toDto(Categoria categoria);

    Categoria toEntity(CategoriaDto categoriaDto);
}