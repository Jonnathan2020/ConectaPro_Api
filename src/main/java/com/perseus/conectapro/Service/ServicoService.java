package com.perseus.conectapro.Service;

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
    public Servico alterarServico(int idServico){
        Servico servicoExistente = servicoRepository.findById(idServico).orElseThrow(() -> new IllegalArgumentException("Serviço não encontrado!!"));

        servicoExistente.setIdSituacaoServico(servicoExistente.getIdSituacaoServico());
        servicoExistente.setDataInclusao(servicoExistente.getDataInclusao());
        servicoExistente.setDataAprovacao(servicoExistente.getDataAprovacao());
        servicoExistente.setDataExecucao(servicoExistente.getDataExecucao());
        servicoExistente.setDataPagamento(servicoExistente.getDataPagamento());
        servicoExistente.setValorContratacao(servicoExistente.getValorContratacao());

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
