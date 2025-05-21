package com.perseus.conectapro.Service;

import ch.qos.logback.classic.Logger;
import com.perseus.conectapro.DTO.*;
import com.perseus.conectapro.Entity.*;
import com.perseus.conectapro.Entity.Enuns.SituacaoServicoEnum;
import com.perseus.conectapro.Entity.Enuns.TipoUsuarioEnum;
import com.perseus.conectapro.Repository.EmpresaClienteRepository;
import com.perseus.conectapro.Repository.OrcamentoRepository;
import com.perseus.conectapro.Repository.PrestadorRepository;
import com.perseus.conectapro.Repository.ServicoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@Slf4j
@Service
public class ServicoService {
    @Autowired
    private PrestadorRepository prestadorRepository;
    @Autowired
    private EmpresaClienteRepository empresaClienteRepository;
    @Autowired
    private ServicoRepository servicoRepository;
    @Autowired
    private OrcamentoRepository orcamentoRepository;

    //cadastrar servico utilizando jpa repository
    public ServicoDTO cadastrarServico(ServicoCreateDTO servicoCreateDTO){


        Orcamento orcamento = orcamentoRepository.findById(servicoCreateDTO.getIdOrcamento())
                .orElseThrow(() -> new IllegalArgumentException("Orçamento não encontrado"));


        Usuario usuario = (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Servico servico = new Servico();

        if (usuario == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Usuário não encontrado");
        }

        if (usuario instanceof Prestador) {
            Prestador prestador = (Prestador) usuario;
            servico.setIdPrestador(prestador);
            EmpresaCliente cliente = orcamento.getIdEmpresaCliente();
            servico.setIdEmpresaCliente(cliente);
        }
        else if (usuario instanceof EmpresaCliente) {
            EmpresaCliente cliente = (EmpresaCliente) usuario;
            servico.setIdEmpresaCliente(cliente);
            servico.setIdPrestador(orcamento.getIdPrestador());

        }
        else {
            throw new IllegalArgumentException("Tipo de usuário não suportado para criar serviço.");
        }

        servico.setOrcamento(orcamento);
        servico.setSituacaoServico(SituacaoServicoEnum.ORCAMENTO);
        servico.setDescServico(servicoCreateDTO.getDescServico());
        servico.setDataInclusao(LocalDateTime.now());
        servico.setDataAprovacao(servicoCreateDTO.getDataAprovacao());
        servico.setDataExecucao(servicoCreateDTO.getDataExecucao());
        servico.setDataPagamento(servicoCreateDTO.getDataPagamento());
        servico.setIdSegmento(servicoCreateDTO.getIdSegmento());
        servico.setValorContratacao(servicoCreateDTO.getValorContratacao());
        servico.setFormaPagtoEnum(servicoCreateDTO.getFormaPagtoEnum());
        servico.setNvlUrgenciaEnum(servicoCreateDTO.getNvlUrgenciaEnum());
        servico.setTipoCategoriaEnum(servicoCreateDTO.getTipoCategoriaEnum());

        Servico servicoCriado = servicoRepository.save(servico);
        return new ServicoDTO(servicoCriado);
    }

    //consultar servicos
    public List<ServicoDTO> consultarServicos(){
        List<Servico> servicos  =  servicoRepository.findAll();
        if(servicos.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return servicos.stream()
                .map(ServicoDTO::new)
                .collect(Collectors.toList());
    }

    public ServicoDTO consultarServicoPorId(int idServico) {
        Servico servicoEspecifico = servicoRepository.findById(idServico)
                .orElseThrow(() -> new IllegalArgumentException(("Nenhum orçamento encontrado com essas informações,!")));
        return new ServicoDTO(servicoEspecifico);
    }

    //alterar informações do usuario
    public Servico alterarServico(int idServico, ServicoUpdateDTO servicoUpdateDTO){
        Servico servicoExistente = servicoRepository.findById(idServico)
                .orElseThrow(() -> new IllegalArgumentException("Serviço não encontrado!!"));

        if (servicoUpdateDTO.getDescServico() != null){
            servicoExistente.setDescServico(servicoUpdateDTO.getDescServico());
        }
        if(servicoUpdateDTO.getSituacaoServicoEnum() != null){
            servicoExistente.setSituacaoServico(servicoUpdateDTO.getSituacaoServicoEnum());
        }
        //if(servicoUpdateDTO.getDataInclusao() != null){
        //    servicoExistente.setDataInclusao(servicoUpdateDTO.getDataInclusao());
        //}
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
}
