package net.josgonmor.ApiEventos.controllers;

import net.josgonmor.ApiEventos.dto.OfertanteDto;
import net.josgonmor.ApiEventos.services.OfertanteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api/eventos/ofertante")
public class OfertanteController {
    @Autowired
    OfertanteService ofertanteService;

    @PostMapping("/getById")
    public OfertanteDto getById(@RequestBody OfertanteDto ofertanteDto){
        return ofertanteService.getById(ofertanteDto.getId());
    }
}
