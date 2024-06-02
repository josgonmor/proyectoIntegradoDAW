package net.josgonmor.ApiEventos.services;

import net.josgonmor.ApiEventos.domain.Categoria;
import net.josgonmor.ApiEventos.dto.CategoriaDto;
import net.josgonmor.ApiEventos.mappers.CategoriaMapper;
import net.josgonmor.ApiEventos.repositories.CategoriaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoriaServiceImpl implements CategoriaService{

    private final CategoriaRepository categoriaRepository;
    private final CategoriaMapper categoriaMapper;

    public CategoriaServiceImpl(CategoriaRepository categoriaRepository,
                                CategoriaMapper categoriaMapper) {
        this.categoriaRepository = categoriaRepository;
        this.categoriaMapper = categoriaMapper;
    }

    @Override
    public List<CategoriaDto> findAll() {
        return categoriaRepository.findAll().stream().map(categoriaMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public List<CategoriaDto> findByOfertaId(Long id) {
        return categoriaRepository.findByOfertaId(id).stream().map(categoriaMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public List<Categoria> findByOfertaIdCompleto(Long id) {
        return this.categoriaRepository.findByOfertaId(id);
    }
}
