package com.perseus.conectapro.Controller;

import com.perseus.conectapro.DTO.ServicoDTO;
import com.perseus.conectapro.Entity.Servico;
import com.perseus.conectapro.Service.PerfilPrestadorService;
import com.perseus.conectapro.Service.ServicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/perfil/prestador")
public class PerfilPrestadorController {

    @Autowired
    private PerfilPrestadorService perfilPrestadorService;

    @GetMapping("/{idPrestador}/servicos-prestados")
    public ResponseEntity<List<ServicoDTO>> getServicosPrestados(@PathVariable Integer idPrestador) {
        return ResponseEntity.ok(perfilPrestadorService.buscarServicosPrestados(idPrestador));
    }

    @GetMapping("/{idPrestador}/candidaturas")
    public ResponseEntity<List<ServicoDTO>> getCandidaturas(@PathVariable Integer idPrestador) {
        return ResponseEntity.ok(perfilPrestadorService.buscarCandidaturasDoPrestador(idPrestador));
    }

    @GetMapping("/{idPrestador}/propostas-recebidas")
    public ResponseEntity<List<ServicoDTO>> getPropostasRecebidas(@PathVariable Integer idPrestador) {
        return ResponseEntity.ok(perfilPrestadorService.buscarPropostasRecebidas(idPrestador));
    }
}
