package com.perseus.conectapro.Service;

import com.perseus.conectapro.DTO.OrcamentoCreateDTO;
import com.perseus.conectapro.DTO.OrcamentoDTO;
import com.perseus.conectapro.DTO.OrcamentoUpdateDTO;
import com.perseus.conectapro.Entity.Orcamento;
import com.perseus.conectapro.Entity.Prestador;
import com.perseus.conectapro.Repository.OrcamentoRepository;
import com.perseus.conectapro.Repository.PrestadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrcamentoService {

    @Autowired
    private OrcamentoRepository orcamentoRepository;
    @Autowired
    private PrestadorRepository prestadorRepository;

    public Orcamento cadastrarOrcamento(OrcamentoCreateDTO orcamentoCreateDTO)
    {
        Prestador prestador = prestadorRepository.findById(orcamentoCreateDTO.getIdPrestador())
                .orElseThrow(() -> new RuntimeException("Prestador não encontrado"));


        Orcamento orcamento = new Orcamento();
        orcamento.setIdPrestador(prestador);
        orcamento.setPrevisaoInicio(orcamentoCreateDTO.getPrevisaoInicio());
        orcamento.setDuracaoServico(orcamentoCreateDTO.getDuracaoServico());
        orcamento.setValorOrcamento(orcamentoCreateDTO.getValorOrcamento());
        orcamento.setFormaPagtoEnum(orcamentoCreateDTO.getFormaPagtoEnum());
        orcamento.setStatusOrcamento(orcamentoCreateDTO.getStatusOrcamento());

        return orcamentoRepository.save(orcamento);
    }

    public Orcamento alterarOrcamento(int idOrcamento, OrcamentoUpdateDTO orcamentoUpdateDTO){

        Orcamento orcamentoExistente = orcamentoRepository.findById(idOrcamento)
                .orElseThrow(() -> new IllegalArgumentException("Orçamento não encontrado!!"));

        if(orcamentoUpdateDTO.getValorOrcamento() !=null){
            orcamentoExistente.setValorOrcamento(orcamentoUpdateDTO.getValorOrcamento());
        }
        if(orcamentoUpdateDTO.getPrevisaoInicio() !=null){
            orcamentoExistente.setPrevisaoInicio(orcamentoUpdateDTO.getPrevisaoInicio());
        }
        if(orcamentoUpdateDTO.getDuracaoServico() != 0){
            orcamentoExistente.setDuracaoServico(orcamentoUpdateDTO.getDuracaoServico());
        }
        if(orcamentoUpdateDTO.getFormaPagtoEnum() !=null){
            orcamentoExistente.setFormaPagtoEnum(orcamentoUpdateDTO.getFormaPagtoEnum());
        }

        if (orcamentoUpdateDTO.getStatusOrcamento() != null) {
            orcamentoExistente.setStatusOrcamento(orcamentoUpdateDTO.getStatusOrcamento());
        }

        return orcamentoRepository.save(orcamentoExistente);
    }

    public Orcamento consultarOrcamentoUnico(int id){
        Orcamento orcamentoEspecifico = orcamentoRepository.findById(id).orElseThrow(() -> new IllegalArgumentException(("Nenhum orçamento encontrado para esse prestador!")));
        return orcamentoEspecifico;
    }

    public List<OrcamentoDTO> consultarOrcamentoPorPrestador(Prestador idPrestador) {
        return orcamentoRepository.findByIdPrestador(idPrestador)
                .stream()
                .map(OrcamentoDTO::new)
                .collect(Collectors.toList());
    }

    public void delete(int idOrcamento) {
        orcamentoRepository.deleteById(idOrcamento);
    }
}
