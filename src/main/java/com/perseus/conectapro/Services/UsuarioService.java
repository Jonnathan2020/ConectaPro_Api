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
        // Salvar o Cliente no banco para gerar um ID (se necessário)
        EmpresaCliente clienteSalvo = empresaClienteRepository.save(cliente);

        // Criar e preencher o Endereço
        Endereco endereco = new Endereco();
        endereco.setRua(cliente.getEnderecos().get(0).getRua());  // Pegando do objeto recebido
        endereco.setBairro(cliente.getEnderecos().get(0).getBairro());
        endereco.setComplemento(cliente.getEnderecos().get(0).getComplemento());
        endereco.setNumero(cliente.getEnderecos().get(0).getNumero());
        endereco.setCidade(cliente.getEnderecos().get(0).getCidade());
        endereco.setEstado(cliente.getEnderecos().get(0).getEstado());

        // Associar o endereço ao cliente
        endereco.setUsuario(clienteSalvo);  // Definir o cliente ao endereço
        enderecoRepository.save(endereco); // Salvar endereço no banco

        // Relacionar o cliente ao endereço
        clienteSalvo.getEnderecos().add(endereco);

        // Atualizar o Cliente no banco para garantir consistência
        return empresaClienteRepository.save(clienteSalvo);
    }

}


