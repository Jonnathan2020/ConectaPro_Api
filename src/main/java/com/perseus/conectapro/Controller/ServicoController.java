package com.perseus.conectapro.Controller;

import com.perseus.conectapro.DTO.ServicoUpdateDTO;
import com.perseus.conectapro.Entity.Servico;
import com.perseus.conectapro.Service.ServicoService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SecurityRequirement(name = "bearerAuth")
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/servico")
public class ServicoController {

    @Autowired
    private ServicoService servicoService;

    //Cadastar servico
    @PostMapping("/registro")
    public ResponseEntity<Servico> cadastrarServico(@RequestBody Servico servico){
        Servico servicoCriado = servicoService.cadastrarServico(servico);
        return ResponseEntity.status(HttpStatus.CREATED).body(servicoCriado);
    }

    //consultar servicos
    @GetMapping
    public List<Servico> consultarServicos(){
        return servicoService.consultarServicos();
    }

    @GetMapping("/{id}")
    public Servico consultarServicoPorId(@PathVariable int id){
        return servicoService.consultarServicoPorId(id);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Servico> alterarServico(@PathVariable("id") int id,@RequestBody ServicoUpdateDTO servicoUpdateDTO){
            Servico servicoAtualizado = servicoService.alterarServico(id, servicoUpdateDTO);
            return ResponseEntity.ok(servicoAtualizado);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id){
        servicoService.delete(id);
    }

}

