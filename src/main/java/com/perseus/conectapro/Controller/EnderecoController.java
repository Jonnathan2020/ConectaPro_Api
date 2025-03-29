package com.perseus.conectapro.Controller;

import com.perseus.conectapro.Entity.Endereco;
import com.perseus.conectapro.Service.EnderecoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/endereco")
public class EnderecoController {

    @Autowired
    private EnderecoService enderecoService;

    //Listar endereço
    @GetMapping
    public List<Endereco> listarEndereco() { return enderecoService.consultarEnderecos();}


    //Alterar endereço
    @PutMapping("{id}")
    public Endereco alterarEndereco(@RequestBody Endereco endereco, @PathVariable("id") int id){
        if(id == endereco.getIdEndereco()){
            return enderecoService.alterarEndereco(endereco.getIdEndereco());
        }
        else
            return null;
    }

    //Cadastrar endereço
    @PostMapping("/Registro")
    public Endereco cadastrarEndereco(@RequestBody Endereco endereco){ return enderecoService.cadastrarEndereco(endereco);}

    //Deletar endereço
    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) { enderecoService.delete(id);}

}
