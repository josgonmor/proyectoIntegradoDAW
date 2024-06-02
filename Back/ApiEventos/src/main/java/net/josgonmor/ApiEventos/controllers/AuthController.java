package net.josgonmor.ApiEventos.controllers;

import jakarta.servlet.http.HttpServletRequest;
import net.josgonmor.ApiEventos.domain.*;
import net.josgonmor.ApiEventos.dto.OfertanteDto;
import net.josgonmor.ApiEventos.dto.UsuarioDto;
import net.josgonmor.ApiEventos.dto.utils.AuthResponse;
import net.josgonmor.ApiEventos.dto.utils.UserAuth;
import net.josgonmor.ApiEventos.repositories.RoleRepository;
import net.josgonmor.ApiEventos.repositories.UsuarioRepository;
import net.josgonmor.ApiEventos.security.JwtTokenBlacklist;
import net.josgonmor.ApiEventos.security.JwtTokenProvider;
import net.josgonmor.ApiEventos.services.DemandanteService;
import net.josgonmor.ApiEventos.services.OfertanteService;
import net.josgonmor.ApiEventos.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/api/auth")
public class AuthController {
    private AuthenticationManager authenticationManager;
    private PasswordEncoder passwordEncoder;
    private RoleRepository roleRepository;
    private UsuarioRepository usuarioRepository;
    private JwtTokenProvider jwtTokenProvider;
    private UsuarioService usuarioService;
    private OfertanteService ofertanteService;
    private JwtTokenBlacklist jwtTokenBlacklist;

    private DemandanteService demandanteService;
    @Autowired
    public AuthController(AuthenticationManager authenticationManager, PasswordEncoder passwordEncoder, RoleRepository roleRepository, UsuarioRepository usuarioRepository, JwtTokenProvider jwtTokenProvider, UsuarioService usuarioService, OfertanteService ofertanteService, JwtTokenBlacklist jwtTokenBlacklist, DemandanteService demandanteService) {
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
        this.usuarioRepository = usuarioRepository;
        this.jwtTokenProvider = jwtTokenProvider;
        this.usuarioService = usuarioService;
        this.ofertanteService = ofertanteService;
        this.jwtTokenBlacklist = jwtTokenBlacklist;
        this.demandanteService = demandanteService;
    }




    @PostMapping("/registro")
    public ResponseEntity<Map> registrar(@RequestBody UserAuth usuarioDto){
        if(usuarioRepository.existsByUsuario(usuarioDto.getUsuario())){
            Map p = new HashMap();
            p.put("estado", "Not Found");
            p.put("mensaje", "El usuario ya existe");
            return new ResponseEntity<>(p, HttpStatus.BAD_REQUEST);
        }
        Usuario usuario = new Usuario();
        usuario.setUsuario(usuarioDto.getUsuario());
        usuario.setNombre(usuarioDto.getNombre());
        usuario.setApellidos(usuarioDto.getApellidos());
        usuario.setEmail(usuarioDto.getEmail());
        usuario.setPassword(passwordEncoder.encode(usuarioDto.getPassword()));
        Role role = roleRepository.findByNombre(usuarioDto.getRole());
        usuario.setRole(role);
        usuario.setActivo(true);
        usuario = usuarioService.createUpdateUsuario(usuario);
        if(usuarioDto.getRole().equals("OFERTANTE")){
            Ofertante ofertante = new Ofertante();
            ofertante.setUsuario(usuario);
            usuario.setOfertante(ofertante);
            OfertanteDto ofertanteDto = ofertanteService.createUpdateOfertante(ofertante);
        }else {
            Demandante demandante = new Demandante();
            demandante.setUsuario(usuario);
            usuario.setDemandante(demandante);
            DemandanteDto demandanteDto = demandanteService.createOrUdateDemandante(demandante);
        }
        usuarioService.createUpdateUsuario(usuario);

        Map s = new HashMap();
        s.put("estado", "OK");
        s.put("mensaje", "Registro exitoso");
        s.put("usuario", usuario.toString());
return new ResponseEntity<>(s, HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<Map> login(@RequestBody UserAuth usuarioDto){
        Authentication authentication = authenticationManager.authenticate( new UsernamePasswordAuthenticationToken(
                usuarioDto.getUsuario(), usuarioDto.getPassword()
        ));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String user = authentication.getName();
        List<? extends GrantedAuthority> roles =authentication.getAuthorities().stream().toList();
        String token = jwtTokenProvider.generarToken(authentication);
        Map response = new HashMap<>();
        response.put("status", "OK");
        response.put("jwt", new AuthResponse(token));
        response.put("rol", roles);
        response.put("usuario", user);
        return  new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/logout")
    public ResponseEntity<Map> logout(HttpServletRequest request){
        String bearerToken = request.getHeader("Authorization");
        Map<String, String> response = new HashMap<>();
        if(StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")){
            this.jwtTokenBlacklist.addToBlacklist(bearerToken.substring(7, bearerToken.length()));
            response.put("status", "OK");
            response.put("mensaje", "Log out Completado");
            return new ResponseEntity<>(response, HttpStatus.OK);
        }else{
            response.put("status", "ERROR");
            response.put("mensaje", "Error al hacer el logOut");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

}
