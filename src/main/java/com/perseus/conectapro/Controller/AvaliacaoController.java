package com.perseus.conectapro.Controller;

import com.perseus.conectapro.DTO.AvaliacaoUpdateDTO;
import com.perseus.conectapro.Entity.Avaliacao;
import com.perseus.conectapro.Service.AvaliacaoService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SecurityRequirement(name = "bearerAuth")
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/avaliacao")
public class AvaliacaoController {

    @Autowired
    private AvaliacaoService avaliacaoService;

    //Listar avaliações
    @GetMapping
    public List<Avaliacao> consultarAvaliacoes() { return avaliacaoService.listarAvaliacoes(); }

    //Cadastrar avaliação
    @PostMapping("/registro")
    public ResponseEntity<Avaliacao> cadastrarAvaliacao(@RequestBody Avaliacao avaliacao) {
        Avaliacao avaliacaoCriada = avaliacaoService.cadastrarAvaliacao(avaliacao);
        return ResponseEntity.status(HttpStatus.CREATED).body(avaliacaoCriada);
    }

    //Alterar avaliação
    @PutMapping("{id}")
    public ResponseEntity<Avaliacao> alterarAvaliacao(@RequestBody AvaliacaoUpdateDTO avaliacaoUpdateDTO, @PathVariable("id") Long id) {
        try
        {
            Avaliacao avaliacaoAlterada = avaliacaoService.alterarAvaliacao(id, avaliacaoUpdateDTO);
            return ResponseEntity.ok(avaliacaoAlterada);
        }
        catch(Exception e)
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    //Deletar avaliação
    @DeleteMapping("{id}")
    public void deletarAvaliacao(@PathVariable("id") Long id) { avaliacaoService.deletarAvaliacao(id); }

    

}
