package net.josgonmor.ApiEventos.controllers;

import net.josgonmor.ApiEventos.domain.DemandanteDto;
import net.josgonmor.ApiEventos.services.DemandanteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api/eventos/demandante")
public class DemandanteController {
    @Autowired
    DemandanteService demandanteService;
    @PostMapping("/getById")
    public DemandanteDto getById(@RequestBody DemandanteDto demandanteDto){
        return demandanteService.getById(demandanteDto.getId());
    }
}
