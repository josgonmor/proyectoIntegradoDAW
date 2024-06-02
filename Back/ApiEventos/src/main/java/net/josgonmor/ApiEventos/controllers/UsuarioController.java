package net.josgonmor.ApiEventos.controllers;

import net.josgonmor.ApiEventos.domain.Usuario;
import net.josgonmor.ApiEventos.dto.UsuarioDto;
import net.josgonmor.ApiEventos.dto.utils.UserAuth;
import net.josgonmor.ApiEventos.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/api/eventos/usuario")
public class UsuarioController {
    @Autowired
    UsuarioService usuarioService;
    @Autowired
    PasswordEncoder passwordEncoder;

    @GetMapping("/findAll")
    public List<UsuarioDto> findAll(){
        return usuarioService.getUsuarios();
    }
    @PostMapping("/getByUsuario")
    public UsuarioDto getByUsuario(@RequestBody UsuarioDto usuarioDto){
        return usuarioService.findByUsuarioDto(usuarioDto.getUsuario());
    }
    @PostMapping("/update")
    public ResponseEntity<Map> update(@RequestBody UserAuth usuarioDto){
    Usuario usuario = usuarioService.findByUsuario(usuarioDto.getUsuario());
    usuario.setEmail(usuarioDto.getEmail());
    usuario.setPassword(passwordEncoder.encode(usuarioDto.getPassword()));
    usuario.setNombre(usuarioDto.getNombre());
    usuario.setApellidos(usuarioDto.getApellidos());
    usuarioService.createUpdateUsuario(usuario);
    Map r = new HashMap();
    r.put("status", "OK");
    r.put("mensaje", "Usuario Actualizado");
    return new ResponseEntity<>(r, HttpStatus.OK);
    }
}
