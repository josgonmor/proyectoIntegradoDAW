package net.josgonmor.ApiEventos.services;

import jakarta.transaction.Transactional;
import net.josgonmor.ApiEventos.domain.Categoria;
import net.josgonmor.ApiEventos.domain.Oferta;
import net.josgonmor.ApiEventos.domain.Ofertante;
import net.josgonmor.ApiEventos.dto.CategoriaDto;
import net.josgonmor.ApiEventos.dto.OfertaDto;
import net.josgonmor.ApiEventos.mappers.OfertaMapper;
import net.josgonmor.ApiEventos.repositories.OfertaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class OfertaServiceImpl implements OfertaService{
    @Autowired
    OfertaRepository ofertaRepository;
    @Autowired
    private OfertaMapper ofertaMapper;

    @Override
    public List<OfertaDto> getOfertas() {
        return ofertaRepository.findAll().stream().map(ofertaMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public List<OfertaDto> getOfertaByOfertante(String usuario) {
        return ofertaRepository.findByIdOfertanteUsuarioUsuario(usuario).orElseThrow().stream().map(ofertaMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public Set<Oferta> getOfertasByOfertante(Ofertante ofertante) {
        return (Set<Oferta>) ofertaRepository.findByIdOfertante(ofertante);
    }

    @Override
    public List<OfertaDto> getOfertaByCategorias(Set<Long> categoriaDtos) {
        return ofertaRepository.findByCategoriaIds(categoriaDtos).stream().map(ofertaMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public OfertaDto findById(Long id) {
        return ofertaMapper.toDto(ofertaRepository.findById(id).orElseThrow());
    }

    @Override
    public Oferta findByIdCompleto(Long id) {
        return ofertaRepository.findById(id).orElseThrow();
    }

    @Override
    public OfertaDto saveOrUpdate(Oferta oferta) {
        return ofertaMapper.toDto(ofertaRepository.save(oferta));
    }
    @Transactional
    @Override
    public Oferta updateCategoriasOferta(Set<Categoria> categorias, Long id) {
        Oferta oferta = ofertaRepository.findByIdCompleto(id);
        if (oferta != null) {
            System.out.println(oferta);
            oferta.setCategorias(categorias);
            oferta = ofertaRepository.save(oferta);
        }else{
            System.out.println("NO ENTRA");
        }
        return oferta;
    }

    @Override
    public void deleteOferta(Oferta oferta) {
        ofertaRepository.delete(oferta);
    }

    @Override
    public Set<Oferta> getOfertaByDemandanteId(Long id) {
        return ofertaRepository.findByIdDemandate(id).orElseThrow();
    }
}
