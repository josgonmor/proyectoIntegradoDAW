package net.josgonmor.ApiEventos.services;

import net.josgonmor.ApiEventos.domain.Categoria;
import net.josgonmor.ApiEventos.dto.CategoriaDto;

import java.util.List;

public interface CategoriaService {
    List<CategoriaDto> findAll();

    List<CategoriaDto> findByOfertaId(Long id);
    List<Categoria> findByOfertaIdCompleto(Long id);

}
