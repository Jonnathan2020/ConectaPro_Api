package com.perseus.conectapro.Controller;

import com.perseus.conectapro.DTO.PrestadorDTO;
import com.perseus.conectapro.DTO.filtro.BuscaPrestadorFiltro;
import com.perseus.conectapro.Entity.Prestador;
import com.perseus.conectapro.Repository.PrestadorRepository;
import com.perseus.conectapro.Service.BuscaPrestadorService;
import com.perseus.conectapro.Service.PrestadorService;
import com.perseus.conectapro.specification.BuscaPrestadorSpecification;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import net.kaczmarzyk.spring.data.jpa.domain.Equal;
import net.kaczmarzyk.spring.data.jpa.domain.Like;
import net.kaczmarzyk.spring.data.jpa.web.annotation.And;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/busca-prestadores")
public class BuscaPrestadorController {

    @Autowired
    private PrestadorRepository prestadorRepository;
    @Autowired
    private BuscaPrestadorService buscaPrestadorService;

    @SecurityRequirement(name = "bearerAuth")
    @GetMapping
    public ResponseEntity<List<PrestadorDTO>> buscar(@ParameterObject BuscaPrestadorFiltro filtro) {
        List<PrestadorDTO> prestadores = buscaPrestadorService.buscar(filtro);
        return ResponseEntity.ok(prestadores);
    }


}
