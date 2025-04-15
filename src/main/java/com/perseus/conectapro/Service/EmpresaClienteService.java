package com.perseus.conectapro.Service;

import com.perseus.conectapro.DTO.EmpresaClienteCreateDTO;
import com.perseus.conectapro.DTO.EmpresaClienteUpdateDTO;
import com.perseus.conectapro.Entity.EmpresaCliente;
import com.perseus.conectapro.Entity.Endereco;
import com.perseus.conectapro.Repository.EmpresaClienteRepository;
import com.perseus.conectapro.Repository.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmpresaClienteService {

    @Autowired
    private EmpresaClienteRepository empresaClienteRepository;
    @Autowired
    private EnderecoRepository enderecoRepository;

    public List<EmpresaCliente> consultarEmpresaPorNome(String nome) {
        return empresaClienteRepository.findByNome(nome);
    }

    //cadastrar as informaçoes alem do usuario, faltantes para uma empresa cliente
    public EmpresaCliente cadastrarEmpresaCliente(EmpresaClienteCreateDTO empresaClienteCreateDTO) {

        Endereco endereco = new Endereco();
        endereco.setLogradouro(empresaClienteCreateDTO.getLogradouro());
        endereco.setNumero(empresaClienteCreateDTO.getNumero());
        endereco.setBairro(empresaClienteCreateDTO.getBairro());
        endereco.setCidade(empresaClienteCreateDTO.getCidade());
        endereco.setUf(empresaClienteCreateDTO.getUf());
        endereco.setCEP(empresaClienteCreateDTO.getCep());
        endereco.setComplemento(empresaClienteCreateDTO.getComplemento());

        // Persistindo o Endereco
        endereco = enderecoRepository.save(endereco);
        
        EmpresaCliente empresaCliente = new EmpresaCliente();
        empresaCliente.setNome(empresaClienteCreateDTO.getNome());
        empresaCliente.setEmail(empresaClienteCreateDTO.getEmail());
        empresaCliente.setSenha(empresaClienteCreateDTO.getSenha());
        empresaCliente.setTelefone(empresaClienteCreateDTO.getTelefone());
        empresaCliente.setTipoUsuario(empresaClienteCreateDTO.getTipoUsuario());
        empresaCliente.setCaminhoFoto(empresaClienteCreateDTO.getCaminhoFoto());

        empresaCliente.setCnpj(empresaClienteCreateDTO.getCnpj());
        empresaCliente.setRazaoSocial(empresaClienteCreateDTO.getRazaoSocial());
        empresaCliente.setNomeFantasia(empresaClienteCreateDTO.getNomeFantasia());
        empresaCliente.setEndereco(endereco);

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
    public EmpresaCliente alterarEmpresaCliente(int idUsuario, EmpresaClienteUpdateDTO empresaClienteUpdateDTO){
        EmpresaCliente empresaExistente = empresaClienteRepository.findById(idUsuario)
                .orElseThrow(() -> new IllegalArgumentException("Empresa não encontrada!!"));

        if(empresaClienteUpdateDTO.getCnpj() != null){
            empresaExistente.setCnpj(empresaClienteUpdateDTO.getCnpj());
        }
        if(empresaClienteUpdateDTO.getRazaoSocial() != null){
            empresaExistente.setRazaoSocial(empresaClienteUpdateDTO.getRazaoSocial());
        }
        if(empresaClienteUpdateDTO.getNomeFantasia() != null){
            empresaExistente.setNomeFantasia(empresaClienteUpdateDTO.getNomeFantasia());
        }

        //metodo que salva as informaçoes do prestador
        return empresaClienteRepository.save(empresaExistente);
    }

    //deletar usuario pelo id
    public void delete(int idUsuario){
        empresaClienteRepository.deleteById(idUsuario);           //metodo que faz o delete do usuario

    }
}
