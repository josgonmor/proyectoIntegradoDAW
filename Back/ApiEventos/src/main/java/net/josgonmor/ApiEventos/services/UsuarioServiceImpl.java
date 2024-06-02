package net.josgonmor.ApiEventos.services;

import jakarta.transaction.Transactional;
import net.josgonmor.ApiEventos.domain.Ofertante;
import net.josgonmor.ApiEventos.domain.Usuario;
import net.josgonmor.ApiEventos.dto.OfertanteDto;
import net.josgonmor.ApiEventos.dto.UsuarioDto;
import net.josgonmor.ApiEventos.mappers.OfertanteMapper;
import net.josgonmor.ApiEventos.mappers.UsuarioMapper;
import net.josgonmor.ApiEventos.repositories.OfertanteRepository;
import net.josgonmor.ApiEventos.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuarioServiceImpl implements UsuarioService{
    @Autowired
    UsuarioRepository usuarioRepository;
    @Autowired
    private UsuarioMapper usuarioMapper;


    @Override
    public List<UsuarioDto> getUsuarios() {
       return usuarioRepository.findAll().stream().map(usuarioMapper::toDto).collect(Collectors.toList());
    }

    @Transactional
    @Override
    public Usuario createUpdateUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    @Override
    public Usuario findByUsuario(String usuario) {
        return usuarioRepository.findByUsuario(usuario).orElseThrow();
    }

    @Override
    public UsuarioDto findByUsuarioDto(String usuario) {
        return usuarioMapper.toDto(usuarioRepository.findByUsuario(usuario).orElseThrow());
    }

    @Override
    public Usuario getUsuario(Long id) {
        return usuarioRepository.findUsuarioById(id).orElseThrow();
    }

}
