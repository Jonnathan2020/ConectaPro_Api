package com.perseus.conectapro.Controller;

import com.perseus.conectapro.Entity.Plano;
import com.perseus.conectapro.Service.PlanoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/plano")
public class PlanoController {

    @Autowired
    PlanoService planoService;

    //Listar planos
    @GetMapping
    public List<Plano> listarPlanos() { return planoService.listarPlanos(); }

    //Buscar plano por id
    @GetMapping("/{id}")
    public Plano buscarPlanoPorId(@PathVariable Long id) { return planoService.buscarPlanoPorId(id); }

    //Cadastrar plano
    @PostMapping("/registro")
    public ResponseEntity<Plano> cadastrarPlano(@RequestBody Plano plano) {
         Plano planoCriado = planoService.cadastrarPlano(plano);
         return ResponseEntity.status(HttpStatus.CREATED).body(planoCriado);
    }

    //Alterar plano
    @PutMapping("/{id}")
    public ResponseEntity<Plano> alterarPlano(@PathVariable Long id, @RequestBody Plano plano) {
        Plano planoCriado = planoService.cadastrarPlano(plano);
        return ResponseEntity.status(HttpStatus.CREATED).body(planoCriado);
    }

    //Deletar plano
    @DeleteMapping("/{id}")
    public void deletarPlano(@PathVariable Long id) { planoService.deletarPlano(id); }

}
