package com.perseus.conectapro.Service;

import com.perseus.conectapro.Entity.Endereco;
import com.perseus.conectapro.Repository.EnderecoRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnderecoService {

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
    public Endereco alterarEndereco(int id){
        Endereco enderecoExistente = enderecoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Endereço não enccontrado no cadastro!"));


        enderecoExistente.setLogradouro(enderecoExistente.getLogradouro());
        enderecoExistente.setNumero(enderecoExistente.getNumero());
        enderecoExistente.setBairro(enderecoExistente.getBairro());
        enderecoExistente.setBairro(enderecoExistente.getBairro());
        enderecoExistente.setCidade(enderecoExistente.getCidade());
        enderecoExistente.setUf(enderecoExistente.getUf());
        enderecoExistente.setComplemento(enderecoExistente.getComplemento());

        //metodo que salva as informaçoes do prestador
        return enderecoRepository.save(enderecoExistente);

    }

    //deletar endereco por id
    public void delete (int id){
        enderecoRepository.deleteById(id);
    }
}
