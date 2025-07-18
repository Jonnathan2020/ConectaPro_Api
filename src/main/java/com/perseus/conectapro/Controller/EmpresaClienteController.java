package com.perseus.conectapro.Controller;

import com.perseus.conectapro.DTO.EmpresaClienteCreateDTO;
import com.perseus.conectapro.DTO.EmpresaClienteDTO;
import com.perseus.conectapro.DTO.EmpresaClienteResumoDTO;
import com.perseus.conectapro.DTO.EmpresaClienteUpdateDTO;
import com.perseus.conectapro.Entity.EmpresaCliente;
import com.perseus.conectapro.Service.EmpresaClienteService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
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

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/empresaCliente")
public class EmpresaClienteController {

    @Autowired
    private EmpresaClienteService empresaClienteService;

    //listar empresas
    @SecurityRequirement(name = "bearerAuth")
    @GetMapping
    public List<EmpresaClienteResumoDTO> consultarEmpresas(
            @And({
                    @Spec(path = "idUsuario", spec = Equal.class),
                    @Spec(path = "cnpj", spec = Like.class),
                    @Spec(path = "razaoSocial",  spec = Like.class),
                    @Spec(path = "nomeFantasia", spec = Like.class),
                    @Spec(path = "nome", spec = Like.class),
                    @Spec(path = "email", spec = Equal.class),
                    @Spec(path = "telefone", spec = Like.class), // Adicionando filtro para telefone
                    @Spec(path = "tipoUsuario", spec = Equal.class), // Filtro para tipo de usuário
                    @Spec(path = "role", spec = Equal.class),
                    @Spec(path = "endereco.cidade", spec = Like.class), // Filtro para o relacionamento com Endereco, caso queira filtrar por algum atributo do endereço
                    @Spec(path = "endereco.uf", spec = Equal.class),
                    @Spec(path = "endereco.cep", spec = Like.class)
            })Specification<EmpresaCliente> spec
            ){
        return empresaClienteService.consultarEmpresas(spec);
    }

    //Buscar empresa por id
    @SecurityRequirement(name = "bearerAuth")
    @GetMapping("/{id}")
    public EmpresaClienteDTO consultarEmpresaEspecifica(@PathVariable int id){
        return empresaClienteService.consultarEmpresaEspecifica(id);
    }

    //Buscar empresa por nome
    @SecurityRequirement(name = "bearerAuth")
    @GetMapping("/nome/{nome}")
    public List<EmpresaClienteDTO> getEmpresaByName(@PathVariable String nome){
        return empresaClienteService.consultarEmpresaPorNome(nome);
    }


    @PostMapping("/registro")
    public ResponseEntity<EmpresaCliente> cadastrarEmpresa(@RequestBody @Valid EmpresaClienteCreateDTO empresaClienteDTO){
        EmpresaCliente empresaCriada = empresaClienteService.cadastrarEmpresaCliente(empresaClienteDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(empresaCriada);
    }

    //Alterar informações da empresa
    @SecurityRequirement(name = "bearerAuth")
    @PutMapping("/{id}")
    public ResponseEntity<EmpresaClienteDTO> alterarEmpresa(@RequestBody EmpresaClienteUpdateDTO empresaClienteUpdateDTO, @PathVariable("id") int id){
            EmpresaClienteDTO empresaAtualizada = empresaClienteService.alterarEmpresaCliente(id, empresaClienteUpdateDTO);
            return ResponseEntity.status(HttpStatus.OK).body(empresaAtualizada);
    }

    @SecurityRequirement(name = "bearerAuth")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id){
        empresaClienteService.delete(id);
    }
}
