package com.perseus.conectapro.Controller;

import com.perseus.conectapro.DTO.SolicitacaoServicoDTO;
import com.perseus.conectapro.DTO.filtro.BuscaSolicitacaoServicoFiltro;
import com.perseus.conectapro.Service.BuscaSolicitacaoServicoService;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/busca-solicitacoes")
public class BuscaSolicitacaoServicoController {

    private final BuscaSolicitacaoServicoService buscaSolicitacaoServicoService;

    public BuscaSolicitacaoServicoController(BuscaSolicitacaoServicoService buscaSolicitacaoServicoService) {
        this.buscaSolicitacaoServicoService = buscaSolicitacaoServicoService;
    }

    @GetMapping
    public ResponseEntity<List<SolicitacaoServicoDTO>> buscar(@ParameterObject BuscaSolicitacaoServicoFiltro filtro) {
        List<SolicitacaoServicoDTO> lista = buscaSolicitacaoServicoService.buscar(filtro);
        return ResponseEntity.ok(lista);
    }
}


