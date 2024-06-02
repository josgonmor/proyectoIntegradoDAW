package net.josgonmor.ApiEventos.controllers;

import net.josgonmor.ApiEventos.domain.*;
import net.josgonmor.ApiEventos.dto.CategoriaDto;
import net.josgonmor.ApiEventos.dto.OfertaDto;
import net.josgonmor.ApiEventos.dto.UsuarioDto;
import net.josgonmor.ApiEventos.dto.utils.OfertaRequest;
import net.josgonmor.ApiEventos.mappers.CategoriaMapper;
import net.josgonmor.ApiEventos.repositories.DemandanteRepository;
import net.josgonmor.ApiEventos.repositories.OfertaDemandanteRepository;
import net.josgonmor.ApiEventos.repositories.OfertaRepository;
import net.josgonmor.ApiEventos.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
@RequestMapping("/api/eventos/oferta")
public class OfertaController {
    @Autowired
    OfertaService ofertaService;
    @Autowired
    UsuarioService usuarioService;

    @Autowired
    OfertanteService ofertanteService;
    @Autowired
    private CategoriaMapper categoriaMapper;
    @Autowired
    private DemandanteService demandanteService;
    @Autowired
    private OfertaRepository ofertaRepository;
    @Autowired
    private DemandanteRepository demandanteRepository;
    @Autowired
    private OfertaDemandanteRepository ofertaDemandanteRepository;

    @GetMapping("/findAll")
    public List<OfertaDto> findAll(){
        return ofertaService.getOfertas();
    }

    @PostMapping("/ofertante")
    public List<OfertaDto> findByUsuario(@RequestBody UsuarioDto usuarioDto){
        return this.ofertaService.getOfertaByOfertante(usuarioDto.getUsuario());
    }
    @PostMapping("categoria")
    public List<OfertaDto> findByCategorias(@RequestBody List<CategoriaDto> categoriaDtos){
//        Set<Categoria> categorias = categoriaDtos.stream().map(categoriaMapper::toEntity).collect(Collectors.toSet());
        Set<Long> categoriasIds = new HashSet<>();
        for(CategoriaDto cat: categoriaDtos){
            categoriasIds.add(cat.getId());
        }
        return ofertaService.getOfertaByCategorias(categoriasIds);
    }
    @PostMapping("/findById")
    public OfertaDto findById(@RequestBody OfertaDto demandanteDto){
        return ofertaService.findById(demandanteDto.getId());
    }

    @PostMapping("/addUpdate")
    public OfertaDto addUpdate(@RequestBody OfertaRequest ofertaRequest){
        System.out.println(ofertaRequest);
        if(ofertaRequest.getId()!=null){
            Oferta oferta = ofertaService.findByIdCompleto(ofertaRequest.getId());
            oferta.setTitle(ofertaRequest.getTitle());
            oferta.setDescripcion(ofertaRequest.getDescripcion());
            oferta.setDate(ofertaRequest.getDate());
            oferta.setPrice(ofertaRequest.getPrice());
            oferta.setLocalizacion(ofertaRequest.getLocalizacion());
            return ofertaService.saveOrUpdate(oferta);
        }else{
            Ofertante ofertante = ofertanteService.getByIdCompleto(ofertaRequest.getIdOfertante());
            System.out.println(ofertante);
            System.out.println(ofertaRequest.getIdOfertante());
            Oferta oferta = new Oferta();
            oferta.setIdOfertante(ofertante);
            oferta.setTitle(ofertaRequest.getTitle());
            oferta.setDescripcion(ofertaRequest.getDescripcion());
            oferta.setDate(ofertaRequest.getDate());
            oferta.setLocalizacion(ofertaRequest.getLocalizacion());
            oferta.setPrice(ofertaRequest.getPrice());
            oferta.setEstado("Activo");

            Set<Oferta> ofertas = ofertaService.getOfertasByOfertante(ofertante);
            ofertas.add(oferta);

            ofertante.setOfertas(ofertas);

            ofertanteService.createUpdateOfertante(ofertante);
            return ofertaService.saveOrUpdate(oferta);
        }
    }
    @PostMapping("/addUpdateCategorias")
    public ResponseEntity<Map> categoria(@RequestBody OfertaRequest ofertaRequest){
        System.out.println(ofertaRequest);
        Set<Categoria> categorias = new HashSet<>();
        for(CategoriaDto cat : ofertaRequest.getCategorias()){
            categorias.add(categoriaMapper.toEntity(cat));
            System.out.println(cat);
        }
        ofertaService.updateCategoriasOferta(categorias, ofertaRequest.getId());
        Map r = new HashMap<>();
        r.put("estado", "OK");
        r.put("mensaje", "Categorias Actualizadas");
        return new ResponseEntity<>(r, HttpStatus.OK);
    }
    @PostMapping("/delete")
    public ResponseEntity<Map> delelte(@RequestBody OfertaRequest ofertaRequest){
        Oferta oferta = ofertaService.findByIdCompleto(ofertaRequest.getId());
        ofertaService.deleteOferta(oferta);
        Map p = new HashMap();
        p.put("status", "OK");
        return new ResponseEntity<>(p, HttpStatus.OK);
    }
    @PostMapping("apuntarOferta")
    public ResponseEntity<Map> apuntar(@RequestBody OfertaRequest ofertaRequest){
        System.out.println(ofertaRequest);
        Oferta oferta = ofertaService.findByIdCompleto(ofertaRequest.getId());
        System.out.println(oferta);
        Demandante demandante = demandanteService.getByIdCompleto(ofertaRequest.getIdDemandante());
        System.out.println(demandante);
        OfertaDemandante ofertaDemandante = new OfertaDemandante();
        ofertaDemandante.setIdOferta(oferta);
        ofertaDemandante.setIdDemandante(demandante);

        ofertaDemandanteRepository.save(ofertaDemandante);

        Map p = new HashMap();
        p.put("OK", "status");
        return new ResponseEntity<>(p, HttpStatus.OK);
    }
}
