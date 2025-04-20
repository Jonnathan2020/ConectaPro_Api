package com.perseus.conectapro.Service;

import com.perseus.conectapro.DTO.ViaCepDTO;
import com.perseus.conectapro.Entity.Endereco;
import com.perseus.conectapro.Repository.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnderecoService {

    @Autowired
    private EnderecoRepository enderecoRepository;
    @Autowired
    private ViaCepService viaCepService;

    //cadastrar informações de endereço
    public Endereco cadastrarEndereco(Endereco endereco){
        if (endereco.getCep() != null) {
            ViaCepDTO viaCep = viaCepService.buscarEnderecoPorCep(endereco.getCep());

            Endereco enderecoAdicional = new Endereco();
            // Preencher os campos com as infos da API
            enderecoAdicional.setCep(viaCep.getCep());
            enderecoAdicional.setLogradouro(viaCep.getLogradouro());
            enderecoAdicional.setBairro(viaCep.getBairro());
            enderecoAdicional.setCidade(viaCep.getLocalidade());
            enderecoAdicional.setUf(viaCep.getUf());
            enderecoAdicional.setComplemento(endereco.getComplemento());
            enderecoAdicional.setNumero(endereco.getNumero());

            return enderecoRepository.save(enderecoAdicional);
        }

        // Caso o CEP não seja informado
        throw new IllegalArgumentException("CEP não informado");
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
                .orElseThrow(() -> new IllegalArgumentException("Endereço não encontrado no cadastro!"));

        //Se o cep for informado, fará a atualização do cep e dos outros campos que são obtidos através da api externa
        //ViaCep automaticamente
        if (endereco.getCep() != null && !endereco.getCep().equals(enderecoExistente.getCep())) {
            ViaCepDTO viaCep = viaCepService.buscarEnderecoPorCep(endereco.getCep());

            enderecoExistente.setCep(viaCep.getCep());
            enderecoExistente.setLogradouro(viaCep.getLogradouro());
            enderecoExistente.setBairro(viaCep.getBairro());
            enderecoExistente.setCidade(viaCep.getLocalidade());
            enderecoExistente.setUf(viaCep.getUf());
        }

        //Número e complemento são os únicos campos editáveis manualmente
        if(endereco.getNumero() != null){
            enderecoExistente.setNumero(endereco.getNumero());
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
