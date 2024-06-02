package net.josgonmor.ApiEventos.services;

import net.josgonmor.ApiEventos.domain.Categoria;
import net.josgonmor.ApiEventos.domain.Oferta;
import net.josgonmor.ApiEventos.domain.Ofertante;
import net.josgonmor.ApiEventos.dto.CategoriaDto;
import net.josgonmor.ApiEventos.dto.OfertaDto;

import java.util.List;
import java.util.Set;

public interface OfertaService {
    List<OfertaDto> getOfertas();
    List<OfertaDto> getOfertaByOfertante(String user);
    Set<Oferta> getOfertasByOfertante(Ofertante ofertante);
    List<OfertaDto> getOfertaByCategorias(Set<Long> categoriaDtos);
    OfertaDto findById(Long id);

    Oferta findByIdCompleto(Long id);

    OfertaDto saveOrUpdate(Oferta oferta);
    Oferta updateCategoriasOferta(Set<Categoria> categorias, Long id);
    void deleteOferta(Oferta oferta);
    Set<Oferta> getOfertaByDemandanteId(Long id);
}
