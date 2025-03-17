package com.perseus.conectapro.Controller;

import com.perseus.conectapro.Entity.Servico;
import com.perseus.conectapro.Service.ServicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/servico")
public class ServicoController {

    @Autowired
    private ServicoService servicoService;

    //Cadastar servico
    @PostMapping("/Registro")
    public Servico cadastrarServico(@RequestBody Servico servico){
        return servicoService.cadastrarServico(servico);
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
    public ResponseEntity<Servico> alterarServico(@RequestBody Servico servico, @PathVariable("id") int id){
        if(id == servico.getIdServico()){
            Servico servicoAtualizado = servicoService.alterarServico(servico.idServico);
            return ResponseEntity.ok(servicoAtualizado);
        }
        else {
            return ResponseEntity.badRequest().body(null);
        }

    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id){
        servicoService.delete(id);
    }

}

