package com.perseus.conectapro.Controller;

import com.perseus.conectapro.DTO.SolicitacaoServicoCreateDTO;
import com.perseus.conectapro.DTO.SolicitacaoServicoDTO;
import com.perseus.conectapro.DTO.SolicitacaoServicoUpdateDTO;
import com.perseus.conectapro.Entity.SolicitacaoServico;
import com.perseus.conectapro.Entity.Usuario;
import com.perseus.conectapro.Repository.SolicitacaoServicoRepository;
import com.perseus.conectapro.Service.SolicitacaoServicoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/solicitacao")
public class SolicitacaoServicoController {

    @Autowired
    private SolicitacaoServicoService solicitacaoServicoService;
    @Autowired
    private SolicitacaoServicoRepository solicitacaoServicoRepository;

    @PostMapping("/registro")
    public ResponseEntity<SolicitacaoServicoDTO> cadastrarSolicitacao(@RequestBody @Valid SolicitacaoServicoCreateDTO orcamento){
        SolicitacaoServicoDTO solicitacaoCriada = solicitacaoServicoService.cadastrarSolicitacao(orcamento);
        return ResponseEntity.status(HttpStatus.CREATED).body(solicitacaoCriada);
    }

    @PutMapping("/{id}")
    public SolicitacaoServico alterarSolicitacao(@RequestBody SolicitacaoServicoUpdateDTO solicitacaoServicoUpdateDTO, @PathVariable("id") int id){
        return solicitacaoServicoService.alterarSolicitacao(id, solicitacaoServicoUpdateDTO);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id){
        solicitacaoServicoService.delete(id);
    }

    @GetMapping
    public List<SolicitacaoServicoDTO> consultarSolicitacoes(){
        return solicitacaoServicoService.consultarSolicitacoes();
    }

    @GetMapping("/{id}")
    public SolicitacaoServicoDTO consultarSolicitacaoUnica(@PathVariable int id){
        return solicitacaoServicoService.consultarSolicitacaoUnica(id);
    }

    @GetMapping("/usuario/{id}")
    public List<SolicitacaoServicoDTO> consultarSolicitacaoPorUsuario(@PathVariable Usuario id){
        return solicitacaoServicoService.consultarSolicitacaoPorUsuario(id);
    }


}
