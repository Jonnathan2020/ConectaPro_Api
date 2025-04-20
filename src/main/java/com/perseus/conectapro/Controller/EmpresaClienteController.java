package com.perseus.conectapro.Controller;

import com.perseus.conectapro.DTO.EmpresaClienteCreateDTO;
import com.perseus.conectapro.DTO.EmpresaClienteUpdateDTO;
import com.perseus.conectapro.Entity.EmpresaCliente;
import com.perseus.conectapro.Entity.Usuario;
import com.perseus.conectapro.Repository.EmpresaClienteRepository;
import com.perseus.conectapro.Service.EmpresaClienteService;
import jakarta.validation.Valid;
import net.kaczmarzyk.spring.data.jpa.domain.Equal;
import net.kaczmarzyk.spring.data.jpa.domain.Like;
import net.kaczmarzyk.spring.data.jpa.web.annotation.And;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/empresaCliente")
public class EmpresaClienteController {

    @Autowired
    private EmpresaClienteService empresaClienteService;
    @Autowired
    private EmpresaClienteRepository empresaClienteRepository;

    //listar empresas
    @GetMapping
    public List<EmpresaCliente> consultarEmpresas(
            @And({
                    @Spec(path = "idUsuario", spec = Equal.class),
                    @Spec(path = "cnpj", spec = Like.class),
                    @Spec(path = "razaoSocial",  spec = Like.class),
                    @Spec(path = "nomeFantasia", spec = Like.class)
            })Specification<EmpresaCliente> spec
            ){

        List<EmpresaCliente> clientes = empresaClienteRepository.findAll(spec);
        if (clientes.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Nenhuma empresa encontrada com os filtros fornecidos.");
        }
        return clientes;

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
    public ResponseEntity<EmpresaCliente> cadastrarEmpresa(@RequestBody @Valid EmpresaClienteCreateDTO empresaClienteDTO){
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
