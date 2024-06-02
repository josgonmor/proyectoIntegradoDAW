package net.josgonmor.ApiEventos.services;

import net.josgonmor.ApiEventos.domain.Ofertante;
import net.josgonmor.ApiEventos.domain.Usuario;
import net.josgonmor.ApiEventos.dto.OfertanteDto;
import net.josgonmor.ApiEventos.dto.UsuarioDto;

import java.util.List;

public interface UsuarioService {
    List<UsuarioDto> getUsuarios();
    Usuario createUpdateUsuario(Usuario usuario);
    Usuario findByUsuario(String usuario);
    UsuarioDto findByUsuarioDto(String usuario);
    Usuario getUsuario(Long id);
}
