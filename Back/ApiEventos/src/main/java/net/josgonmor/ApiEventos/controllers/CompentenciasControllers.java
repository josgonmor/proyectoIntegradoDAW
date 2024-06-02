package net.josgonmor.ApiEventos.controllers;

import net.josgonmor.ApiEventos.dto.CompetenciaDto;
import net.josgonmor.ApiEventos.dto.UsuarioDto;
import net.josgonmor.ApiEventos.dto.utils.UserAuth;
import net.josgonmor.ApiEventos.services.CompentenciaService;
import net.josgonmor.ApiEventos.services.OfertanteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/eventos/competencia")
public class CompentenciasControllers {
    @Autowired
    CompentenciaService compentenciaService;
    @GetMapping("/getAll")
    public List<CompetenciaDto> getAll(){
        return compentenciaService.findAll();
    }
    @PostMapping("/ofertante")
    public List<CompetenciaDto> getByOfertante(@RequestBody UsuarioDto user){
        return compentenciaService.findByOfertanteId(user.getId());
    }
}
