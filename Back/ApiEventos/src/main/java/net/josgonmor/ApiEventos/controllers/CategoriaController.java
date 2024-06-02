package net.josgonmor.ApiEventos.controllers;

import net.josgonmor.ApiEventos.dto.CategoriaDto;
import net.josgonmor.ApiEventos.dto.OfertanteDto;
import net.josgonmor.ApiEventos.services.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("api/eventos/categorias")
public class CategoriaController {
    @Autowired
    CategoriaService categoriaService;

    @GetMapping("/findAll")
    public List<CategoriaDto> findAll(){
        return this.categoriaService.findAll();
    }

    @PostMapping("/oferta")
    public List<CategoriaDto> findByOferta(@RequestBody OfertanteDto ofertanteDto){
        return categoriaService.findByOfertaId(ofertanteDto.getId());
    }
}
