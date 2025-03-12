package com.perseus.conectapro.Service;

import com.perseus.conectapro.Entity.EmpresaCliente;
import com.perseus.conectapro.Repository.EmpresaClienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmpresaClienteService {

    public EmpresaClienteRepository empresaClienteRepository;

    public List<EmpresaCliente> consultarEmpresaPorNome(String nome) {
        return empresaClienteRepository.findByName(nome);
    }

    //cadastrar as informaçoes alem do usuario, faltantes para uma empresa cliente
    public EmpresaCliente cadastrarEmpresaCliente(EmpresaCliente empresaCliente) {

            return empresaClienteRepository.save(empresaCliente);
    }

    //consultar somente empresas clientes
    public List<EmpresaCliente> consultarEmpresasCliente(){
        return empresaClienteRepository.findAll();
    }

    //consultar empresa cliente especifica
    public EmpresaCliente consultarEmpresaEspecifica(int idUsuario){
        EmpresaCliente empresaClienteEspecifica = empresaClienteRepository.findById(idUsuario).orElseThrow(() -> new IllegalArgumentException("Empresa não encontrada!!"));

        return empresaClienteEspecifica;
    }

    //alterar informaçoes somente da empresa
    public EmpresaCliente alterarEmpresaCliente(int idUsuario){
        EmpresaCliente empresaExistente = empresaClienteRepository.findById(idUsuario).orElseThrow(() -> new IllegalArgumentException("Empresa não encontrada!!"));

        //alterando todos atributos da classe
        empresaExistente.setCNPJ(empresaExistente.getCNPJ());
        empresaExistente.setRazaoSocial(empresaExistente.getRazaoSocial());
        empresaExistente.setNomeFantasia(empresaExistente.getNomeFantasia());

        //metodo que salva as informaçoes do prestador
        return empresaClienteRepository.save(empresaExistente);
    }

    //deletar usuario pelo id
    public void delete(int idUsuario){
        empresaClienteRepository.deleteById(idUsuario);           //metodo que faz o delete do usuario

    }
}
