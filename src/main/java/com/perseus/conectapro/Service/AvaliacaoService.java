package com.perseus.conectapro.Service;

import com.perseus.conectapro.DTO.AvaliacaoUpdateDTO;
import com.perseus.conectapro.Entity.Avaliacao;
import com.perseus.conectapro.Repository.AvaliacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AvaliacaoService {

    @Autowired
    private AvaliacaoRepository avaliacaoRepository;

    //Cadastrar avaliação
    public Avaliacao cadastrarAvaliacao(Avaliacao avaliacao) { return avaliacaoRepository.save(avaliacao); }

    //Consultar avaliações
    public List<Avaliacao> listarAvaliacoes() {
        return avaliacaoRepository.findAll();
    }

    //Alterar avaliação
    public Avaliacao alterarAvaliacao(Long idAvaliacao, AvaliacaoUpdateDTO avaliacaoUpdateDTO) {
        Avaliacao avaliacaoExistente = avaliacaoRepository.findById(idAvaliacao)
                .orElseThrow(() -> new IllegalArgumentException("Avaliação não encontrada!!"));

        if (avaliacaoUpdateDTO.getDescricao() != null) {
            avaliacaoExistente.setDescricao(avaliacaoUpdateDTO.getDescricao());
        }

        if (avaliacaoUpdateDTO.getNvlSatisfacao() != null) {
            avaliacaoExistente.setNvlSatisfacao(avaliacaoUpdateDTO.getNvlSatisfacao());
        }

        return avaliacaoRepository.save(avaliacaoExistente);

    }

    //Deletar avaliação
    public void deletarAvaliacao(Long idAvaliacao) { avaliacaoRepository.deleteById(idAvaliacao); }

}
