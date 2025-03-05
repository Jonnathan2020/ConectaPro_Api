package com.perseus.conectapro.Services;

import com.perseus.conectapro.DTO.ClienteDTO;
import com.perseus.conectapro.DTO.EnderecoDTO;
import com.perseus.conectapro.Entity.EmpresaCliente;
import com.perseus.conectapro.Entity.Endereco;
import com.perseus.conectapro.Entity.Enuns.TipoSegmentoEnum;
import com.perseus.conectapro.Entity.Usuario;
import com.perseus.conectapro.repository.EmpresaClienteRepository;
import com.perseus.conectapro.repository.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    private EmpresaClienteRepository empresaClienteRepository;

    @Autowired
    private EnderecoRepository enderecoRepository;

    public EmpresaCliente cadastrarCliente(EmpresaCliente cliente) {
        // Criar e salvar o cliente (EmpresaCliente)
        cliente.setNome(cliente.getNome());
        cliente.setEmail(cliente.getEmail());
        cliente.setRazaoSocial(cliente.getRazaoSocial());
        cliente.setNomeFantasia(cliente.getNomeFantasia());
        cliente.setCnpj(cliente.getCnpj());
        cliente.setSegmentoEnum(cliente.getSegmentoEnum());
        cliente.setTelefone(cliente.getTelefone());
        cliente.setSenha(cliente.getSenha());

        // Criar e salvar o endereço
        Endereco endereco = new Endereco();
        endereco.setRua(endereco.getRua());  // Acessando corretamente a instância de Endereco
        endereco.setBairro(endereco.getBairro());
        endereco.setComplemento(endereco.getComplemento());
        endereco.setNumero(endereco.getNumero());
        endereco.setCidade(endereco.getCidade());
        endereco.setEstado(endereco.getEstado());


        // Salvar Endereço
        enderecoRepository.save(endereco);

        // Relacionar o cliente com o endereço (1:N)
        cliente.getEnderecos().add(endereco);

        // Salvar o Cliente
        return empresaClienteRepository.save(cliente);
    }
}

