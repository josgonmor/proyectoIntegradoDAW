package net.josgonmor.ApiEventos.controllers;

import net.josgonmor.ApiEventos.domain.*;
import net.josgonmor.ApiEventos.dto.CategoriaDto;
import net.josgonmor.ApiEventos.dto.DemandaDto;
import net.josgonmor.ApiEventos.dto.OfertaDto;
import net.josgonmor.ApiEventos.dto.UsuarioDto;
import net.josgonmor.ApiEventos.dto.utils.DemandaRequest;
import net.josgonmor.ApiEventos.dto.utils.OfertaRequest;
import net.josgonmor.ApiEventos.dto.utils.UserAuth;
import net.josgonmor.ApiEventos.mappers.DemandaMapper;
import net.josgonmor.ApiEventos.repositories.DemandaRepository;
import net.josgonmor.ApiEventos.repositories.DemandanteRepository;
import net.josgonmor.ApiEventos.services.DemadaService;
import net.josgonmor.ApiEventos.services.DemandanteService;
import net.josgonmor.ApiEventos.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@CrossOrigin
@RequestMapping("/api/eventos/demanda")
public class DemandaController {
    @Autowired
    DemadaService demadaService;
    @Autowired
    UsuarioService usuarioService;
    @Autowired
    DemandanteService demandanteService;
    @Autowired
    private DemandanteRepository demandanteRepository;
    @Autowired
    private DemandaRepository demandaRepository;
    @Autowired
    private DemandaMapper demandaMapper;

    @GetMapping ("/findAll")
    public List<DemandaDto> getAll(){
        return demadaService.findALl();
    }
    @PostMapping("/demandante")
    public List<DemandaDto> getByUser(@RequestBody UserAuth userAuth) {
        System.out.println(userAuth);
        return this.demadaService.findByUsuario(userAuth.getUsuario());
    }

    @PostMapping("/categoria")
    public List<DemandaDto> findByCategorias(@RequestBody List<CategoriaDto> categoriaDtos){
//        Set<Categoria> categorias = categoriaDtos.stream().map(categoriaMapper::toEntity).collect(Collectors.toSet());
        Set<Long> categoriasIds = new HashSet<>();
        for(CategoriaDto cat: categoriaDtos){
            categoriasIds.add(cat.getId());
        }
        return demadaService.findByCategoriasIds(categoriasIds);
    }
    @PostMapping("/findById")
    public DemandaDto findById(@RequestBody DemandanteDto demandanteDto){
        return demadaService.findById(demandanteDto.getId());
    }
    @PostMapping("/addUpdate")
    public DemandaDto addUpdate(@RequestBody DemandaRequest demandaDto){
        System.out.println(demandaDto);
        if(demandaDto.getId()!=null){
            Demanda demanda = demadaService.findByIdCompleto(demandaDto.getId());
            demanda.setTitle(demandaDto.getTitle());
            demanda.setDescripcion(demandaDto.getDescripcion());
            return demandaMapper.toDto(demandaRepository.save(demanda));
        }else{
            Demandante demandante = demandanteService.getByIdCompleto(demandaDto.getIdDemandante());
            System.out.println(demandante);
            Demanda demanda = new Demanda();
            demanda.setTitle(demandaDto.getTitle());
            demanda.setDescripcion(demandaDto.getDescripcion());
            demanda.setEstado("Pendiente");
            Set<Demanda> demandas = demadaService.findByIdDemandante(demandante);
            demandas.add(demanda);
            System.out.println(demandas);

            demandante.setDemandas(demandas);

            demanda.setIdDemandante(demandante);

            demandanteRepository.save(demandante);


            return demandaMapper.toDto(demandaRepository.save(demanda));
        }
    }

    @PostMapping("/delete")
    public ResponseEntity<Map> delelte(@RequestBody DemandaRequest demandaRequest){
        System.out.println(demandaRequest);
        Demanda demanda = demadaService.findByIdCompleto(demandaRequest.getId());
        System.out.println(demanda);
        demandaRepository.delete(demanda);
        Map p = new HashMap();
        p.put("status", "OK");
        return new ResponseEntity<>(p, HttpStatus.OK);
    }
}
