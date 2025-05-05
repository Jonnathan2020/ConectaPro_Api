package com.perseus.conectapro.Controller;

import com.perseus.conectapro.DTO.OrcamentoCreateDTO;
import com.perseus.conectapro.DTO.OrcamentoDTO;
import com.perseus.conectapro.DTO.OrcamentoUpdateDTO;
import com.perseus.conectapro.Entity.Orcamento;
import com.perseus.conectapro.Entity.Prestador;
import com.perseus.conectapro.Repository.OrcamentoRepository;
import com.perseus.conectapro.Service.OrcamentoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/orcamento")
public class OrcamentoController {

    @Autowired
    private OrcamentoService orcamentoService;
    @Autowired
    private OrcamentoRepository orcamentoRepository;

    @PostMapping("/registro")
    public ResponseEntity<Orcamento> cadastrarOrcamento(@RequestBody @Valid OrcamentoCreateDTO orcamento){
        Orcamento orcamentoCriado = orcamentoService.cadastrarOrcamento(orcamento);
        return ResponseEntity.status(HttpStatus.CREATED).body(orcamentoCriado);
    }

    @PutMapping("/{id}")
    public Orcamento alterarOrcamento(@RequestBody OrcamentoUpdateDTO orcamentoUpdateDTO, @PathVariable("id") int id){
        return orcamentoService.alterarOrcamento(id, orcamentoUpdateDTO);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id){
        orcamentoService.delete(id);
    }

    @GetMapping
    public List<Orcamento> consultarOrcamentos(){

        List<Orcamento> orcamentos = orcamentoRepository.findAll();
        if (orcamentos.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return orcamentos;
    }

    @GetMapping("/{id}")
    public Orcamento consultarOrcamentoUnico(@PathVariable int id){
        return orcamentoService.consultarOrcamentoUnico(id);
    }

    @GetMapping("/prestador/{id}")
    public List<OrcamentoDTO> consultarOrcamentoPorPrestador(@PathVariable Prestador id){
        return orcamentoService.consultarOrcamentoPorPrestador(id);
    }
}
