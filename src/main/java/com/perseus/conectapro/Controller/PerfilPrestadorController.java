package com.perseus.conectapro.Controller;

import com.perseus.conectapro.Entity.Servico;
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
    private ServicoService servicoService;

    @GetMapping("/{idPrestador}/servicos-prestados")
    public ResponseEntity<List<Servico>> getServicosPrestados(@PathVariable Long idPrestador) {
        return ResponseEntity.ok(servicoService.buscarServicosPrestados(idPrestador));
    }

    @GetMapping("/{idPrestador}/candidaturas")
    public ResponseEntity<List<Servico>> getCandidaturas(@PathVariable Long idPrestador) {
        return ResponseEntity.ok(servicoService.buscarCandidaturasDoPrestador(idPrestador));
    }

    @GetMapping("/{idPrestador}/propostas-recebidas")
    public ResponseEntity<List<Servico>> getPropostasRecebidas(@PathVariable Long idPrestador) {
        return ResponseEntity.ok(servicoService.buscarPropostasRecebidas(idPrestador));
    }
}
