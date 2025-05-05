package com.perseus.conectapro.Controller;

import com.perseus.conectapro.DTO.OrcamentoCreateDTO;
import com.perseus.conectapro.DTO.OrcamentoDTO;
import com.perseus.conectapro.DTO.OrcamentoUpdateDTO;
import com.perseus.conectapro.Entity.Orcamento;
import com.perseus.conectapro.Entity.Prestador;
import com.perseus.conectapro.Entity.Usuario;
import com.perseus.conectapro.Repository.OrcamentoRepository;
import com.perseus.conectapro.Service.OrcamentoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/orcamento")
public class OrcamentoController {

    @Autowired
    private OrcamentoService orcamentoService;
    @Autowired
    private OrcamentoRepository orcamentoRepository;

    @PostMapping("/registro")
    public ResponseEntity<OrcamentoDTO> cadastrarOrcamento(@RequestBody @Valid OrcamentoCreateDTO orcamento){
        OrcamentoDTO orcamentoCriado = orcamentoService.cadastrarOrcamento(orcamento);
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
    public List<OrcamentoDTO> consultarOrcamentos(){
        return orcamentoService.consultarOrcamentos();
    }

    @GetMapping("/{id}")
    public OrcamentoDTO consultarOrcamentoUnico(@PathVariable int id){
        return orcamentoService.consultarOrcamentoUnico(id);
    }

    @GetMapping("/usuario/{id}")
    public List<OrcamentoDTO> consultarOrcamentoPorUsuario(@PathVariable Usuario id){
        return orcamentoService.consultarOrcamentoPorIdUsuario(id);
    }
}
