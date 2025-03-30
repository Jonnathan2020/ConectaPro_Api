package com.perseus.conectapro.Service;

import com.perseus.conectapro.DTO.ServicoUpdateDTO;
import com.perseus.conectapro.Entity.Servico;
import com.perseus.conectapro.Repository.ServicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServicoService {

    @Autowired
    private ServicoRepository servicoRepository;

    //cadastrar servico utilizando jpa repository
    public Servico cadastrarServico(Servico servico){
        return servicoRepository.save(servico);
    }

    //consultar servicos
    public List<Servico> consultarServicos(){
        return servicoRepository.findAll();
    }

    //alterar informações do usuario
    public Servico alterarServico(int idServico, ServicoUpdateDTO servicoUpdateDTO){
        Servico servicoExistente = servicoRepository.findById(idServico)
                .orElseThrow(() -> new IllegalArgumentException("Serviço não encontrado!!"));

        if(servicoUpdateDTO.getIdSituacaoServico() != null){
            servicoExistente.setIdSituacaoServico(servicoUpdateDTO.getIdSituacaoServico());
        }
        if(servicoUpdateDTO.getDataInclusao() != null){
            servicoExistente.setDataInclusao(servicoUpdateDTO.getDataInclusao());
        }
        if(servicoUpdateDTO.getDataAprovacao() != null){
            servicoExistente.setDataAprovacao(servicoUpdateDTO.getDataAprovacao());
        }
        if(servicoUpdateDTO.getDataExecucao() != null){
            servicoExistente.setDataExecucao(servicoUpdateDTO.getDataExecucao());
        }
        if(servicoUpdateDTO.getDataPagamento() != null){
            servicoExistente.setDataPagamento(servicoUpdateDTO.getDataPagamento());
        }
        if(servicoUpdateDTO.getValorContratacao() != 0.0){
            servicoExistente.setValorContratacao(servicoUpdateDTO.getValorContratacao());
        }

        //salva as informações no banco `!´
        return servicoRepository.save(servicoExistente);
    }

    //deletar servico
    public void delete(int idServico){
        servicoRepository.deleteById(idServico);
    }

    public Servico consultarServicoPorId(int idServico) {
        Servico servicoEspecifico = servicoRepository.findById(idServico)
                .orElseThrow(() -> new IllegalArgumentException("Servico não encontrado!!"));

        return servicoEspecifico;
    }
}
