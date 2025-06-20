package com.perseus.conectapro.Controller;

import com.perseus.conectapro.DTO.PrestadorBuscaDTO;
import com.perseus.conectapro.DTO.filtro.BuscaPrestadorFiltro;
import com.perseus.conectapro.Service.BuscaPrestadorService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/busca-prestadores")
public class BuscaPrestadorController {

    @Autowired
    private BuscaPrestadorService buscaPrestadorService;

    @SecurityRequirement(name = "bearerAuth")
    @GetMapping
    public ResponseEntity<List<PrestadorBuscaDTO>> buscar(@ParameterObject BuscaPrestadorFiltro filtro) {
        List<PrestadorBuscaDTO> prestadores = buscaPrestadorService.buscar(filtro);
        return ResponseEntity.ok(prestadores);
    }


}
