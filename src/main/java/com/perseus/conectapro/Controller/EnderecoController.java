package com.perseus.conectapro.Controller;

import com.perseus.conectapro.Entity.Endereco;
import com.perseus.conectapro.Service.EnderecoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/endereco")
public class EnderecoController {

    @Autowired
    private EnderecoService enderecoService;

    //Listar endereço
    @GetMapping
    public List<Endereco> listarEndereco() { return enderecoService.consultarEnderecos();}

    //Consultar endereço por id
    @GetMapping("/{id}")
    public Endereco buscarEndereco(@PathVariable int id) { return enderecoService.consultarEnderecoPorId(id); }

    //Alterar endereço
    @PutMapping("/{id}")
    public Endereco alterarEndereco(@RequestBody Endereco endereco, @PathVariable("id") int id){
        return enderecoService.alterarEndereco(id, endereco);
    }

    //Cadastrar endereço
    @PostMapping("/registro")
    public ResponseEntity<Endereco> cadastrarEndereco(@RequestBody Endereco endereco) {
        Endereco enderecoCriado = enderecoService.cadastrarEndereco(endereco);
        return ResponseEntity.status(HttpStatus.CREATED).body(enderecoCriado);
    }

    //Deletar endereço
    @DeleteMapping("/{id}")
    public void deletarEndereco(@PathVariable int id) { enderecoService.delete(id);}

}
