package com.perseus.conectapro.Service;

import com.perseus.conectapro.Entity.Prestador;
import com.perseus.conectapro.Repository.AvaliacaoRepository;
import com.perseus.conectapro.Repository.PrestadorRepository;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PrestadorService {


    private PrestadorRepository prestadorRepository;

    private AvaliacaoRepository avaliacaoRepository;

    //cadastrar as informaçoes alem do usuario, faltantes para um prestador
    public Prestador cadastrarPrestador(Prestador prestador) {
            return prestadorRepository.save(prestador);
    }

    //consultar somente prestadores de servico
    public List<Prestador> consultarPrestadores() {
        return prestadorRepository.findAll(); //precisa ser exibido somente usuarios que são prestadores de serviço
    }

    //consultar prestador especifico
    public Prestador consultarPrestadorUnico(int idUsuario) {
        Prestador prestadorEspecifico = prestadorRepository.findById(idUsuario)
                .orElseThrow(() -> new IllegalArgumentException("Prestador não encontrado!!"));

        return prestadorEspecifico;
    }

    //consultar pelo nome
    public List<Prestador> consultarPrestadorPorNome(String nome){
        return prestadorRepository.findByName(nome);
    }

    //consultar pela especialidade
    public List<Prestador> consultarPrestadorPorEspecialidade(String especialidade) {
        return prestadorRepository.findByEspecialidadesContaining(especialidade);
    }

    //alterar informaçoes somente do prestador
    public Prestador alterarPrestador(int idUsuario) {
        Prestador prestadorExistente = prestadorRepository.findById(idUsuario).orElseThrow(() -> new IllegalArgumentException("Prestador não encontrado!!"));

        //alterando todos atributos da classe
        prestadorExistente.setNome(prestadorExistente.getNome());
        prestadorExistente.setEmail(prestadorExistente.getEmail());
        prestadorExistente.setTelefone(prestadorExistente.getTelefone());
        prestadorExistente.setDescPrestador(prestadorExistente.getDescPrestador());
        prestadorExistente.setEspecialidades(prestadorExistente.getEspecialidades());
        prestadorExistente.setStatusDisponibilidade(prestadorExistente.getStatusDisponibilidade());

        //metodo que salva as informaçoes do prestador
        return prestadorRepository.save(prestadorExistente);
    }

    //deletar usuario pelo id
    public void delete(int idUsuario){
        prestadorRepository.deleteById(idUsuario);           //metodo que faz o delete do usuario
    }

}
