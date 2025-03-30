package com.perseus.conectapro.Service;

import com.perseus.conectapro.Entity.Endereco;
import com.perseus.conectapro.Repository.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnderecoService {

    @Autowired
    private EnderecoRepository enderecoRepository;

    //cadastrar informações de endereço
    public Endereco cadastrarEndereco(Endereco endereco){
        return enderecoRepository.save(endereco);
    }

    //consultar enderecos
    public List<Endereco> consultarEnderecos(){
        return enderecoRepository.findAll();
    }

    //consultar endereco por id
    public Endereco consultarEnderecoPorId(int id){
        Endereco enderecoEspecifico = enderecoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Endereço não enccontrado no cadastro!"));

        return enderecoEspecifico;
    }


    //alterar informações do endereco
    public Endereco alterarEndereco(int id, Endereco endereco ){
        Endereco enderecoExistente = enderecoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Endereço não enccontrado no cadastro!"));

        if(endereco.getLogradouro() != null){
            enderecoExistente.setLogradouro(endereco.getLogradouro());
        }
        if(endereco.getNumero() != null){
            enderecoExistente.setNumero(endereco.getNumero());
        }
        if(endereco.getBairro() != null){
            enderecoExistente.setBairro(endereco.getBairro());
        }
        if(endereco.getCidade() != null){
            enderecoExistente.setCidade(endereco.getCidade());
        }
        if(endereco.getUf() != null){
            enderecoExistente.setUf(endereco.getUf());
        }
        if(endereco.getComplemento() != null){
            enderecoExistente.setComplemento(endereco.getComplemento());
        }

        //metodo que salva as informaçoes do prestador
        return enderecoRepository.save(enderecoExistente);

    }

    //deletar endereco por id
    public void delete (int id){
        enderecoRepository.deleteById(id);
    }
}
