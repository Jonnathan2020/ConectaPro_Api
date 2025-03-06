package com.perseus.conectapro.Controller;

import com.perseus.conectapro.Entity.EmpresaCliente;
import com.perseus.conectapro.Service.EmpresaClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    /////////

    //Alterar informações da empresa
    @PutMapping
    public EmpresaCliente alterarEmpresa(@RequestBody EmpresaCliente empresaCliente, @PathVariable("id") int id){
        if (id == empresaCliente.getIdUsuario()){
            return empresaClienteService.alterarEmpresaCliente(empresaCliente.getIdUsuario());
        }
        else
            return null;
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id){
        empresaClienteService.delete(id);
    }
}
