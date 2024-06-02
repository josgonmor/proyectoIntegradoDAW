package net.josgonmor.ApiEventos.services;

import jakarta.transaction.Transactional;
import net.josgonmor.ApiEventos.domain.Categoria;
import net.josgonmor.ApiEventos.domain.Oferta;
import net.josgonmor.ApiEventos.domain.Ofertante;
import net.josgonmor.ApiEventos.dto.OfertanteDto;
import net.josgonmor.ApiEventos.mappers.OfertanteMapper;
import net.josgonmor.ApiEventos.repositories.OfertaRepository;
import net.josgonmor.ApiEventos.repositories.OfertanteRepository;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class OfertanteServiceImpl implements OfertanteService{

    private final OfertanteRepository ofertanteRepository;
    private final OfertanteMapper ofertanteMapper;
    private final OfertaRepository ofertaRepository;

    public OfertanteServiceImpl(OfertanteRepository ofertanteRepository,
                                OfertanteMapper ofertanteMapper,
                                OfertaRepository ofertaRepository) {
        this.ofertanteRepository = ofertanteRepository;
        this.ofertanteMapper = ofertanteMapper;
        this.ofertaRepository = ofertaRepository;
    }

    @Transactional
    @Override
    public OfertanteDto createUpdateOfertante(Ofertante ofertante) {
        return ofertanteMapper.toDto(ofertanteRepository.save(ofertante));
    }

    @Override
    public OfertanteDto getById(Long id) {
       return ofertanteMapper.toDto(ofertanteRepository.findOfertanteById(id));
    }

    @Override
    public Ofertante getByIdCompleto(Long id) {
        return ofertanteRepository.findOfertanteById(id);
    }

}
