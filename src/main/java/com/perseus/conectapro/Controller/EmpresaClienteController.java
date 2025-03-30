package com.perseus.conectapro.Controller;

import com.perseus.conectapro.DTO.EmpresaClienteCreateDTO;
import com.perseus.conectapro.DTO.EmpresaClienteUpdateDTO;
import com.perseus.conectapro.Entity.EmpresaCliente;
import com.perseus.conectapro.Entity.Prestador;
import com.perseus.conectapro.Service.EmpresaClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/empresaCliente")
public class EmpresaClienteController {

    @Autowired
    private EmpresaClienteService empresaClienteService;

    //listar empresas
    @GetMapping
    public List<EmpresaCliente> consultarEmpresas(){
        return empresaClienteService.consultarEmpresasCliente();
    }

    //Buscar empresa por id
    @GetMapping("/{id}")
    public EmpresaCliente consultarEmpresa(@PathVariable int id){
        return empresaClienteService.consultarEmpresaEspecifica(id);
    }

    //Buscar empresa por nome
    @GetMapping("/nome/{nome}")
    public List<EmpresaCliente> getEmpresaByName(@PathVariable String nome){
        return empresaClienteService.consultarEmpresaPorNome(nome);
    }

    @PostMapping("/registro")
    public ResponseEntity<EmpresaCliente> cadastrarEmpresa(@RequestBody EmpresaClienteCreateDTO empresaClienteDTO){
        EmpresaCliente empresaCriada = empresaClienteService.cadastrarEmpresaCliente(empresaClienteDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(empresaCriada);
    }

    //Alterar informações da empresa
    @PutMapping("/{id}")
    public ResponseEntity<EmpresaCliente> alterarEmpresa(@RequestBody EmpresaClienteUpdateDTO empresaClienteUpdateDTO, @PathVariable("id") int id){
            EmpresaCliente empresaAtualizada = empresaClienteService.alterarEmpresaCliente(id, empresaClienteUpdateDTO);
            return ResponseEntity.status(HttpStatus.OK).body(empresaAtualizada);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id){
        empresaClienteService.delete(id);
    }
}
