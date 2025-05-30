package com.perseus.conectapro.Service;

import com.perseus.conectapro.DTO.*;
import com.perseus.conectapro.Entity.*;
import com.perseus.conectapro.Entity.Enuns.SituacaoRepasseEnum;
import com.perseus.conectapro.Entity.Enuns.SituacaoServicoEnum;
import com.perseus.conectapro.Entity.Enuns.StatusSolicitacaoEnum;
import com.perseus.conectapro.Repository.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

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
    private SolicitacaoServicoRepository solicitacaoServicoRepository;
    @Autowired
    private PagamentoRepository pagamentoRepository;

    //cadastrar servico utilizando jpa repository
    public ServicoDTO cadastrarServico(ServicoCreateDTO servicoCreateDTO){

        SolicitacaoServico solicitacaoServico = solicitacaoServicoRepository.findById(servicoCreateDTO.getIdSolicitacao())
                .orElseThrow(() -> new IllegalArgumentException("Solicitação não encontrada"));


        Usuario usuario = (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Servico servico = new Servico();

        if (usuario == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Usuário não encontrado");
        }

        if (usuario instanceof Prestador) {
            Prestador prestador = (Prestador) usuario;
            servico.setIdPrestador(prestador);
            EmpresaCliente cliente = solicitacaoServico.getIdEmpresaCliente();
            servico.setIdEmpresaCliente(cliente);
        }
        else if (usuario instanceof EmpresaCliente) {
            EmpresaCliente cliente = (EmpresaCliente) usuario;
            servico.setIdEmpresaCliente(cliente);
            servico.setIdPrestador(solicitacaoServico.getIdPrestador());

        }
        else {
            throw new IllegalArgumentException("Tipo de usuário não suportado para criar serviço.");
        }

        servico.setSolicitacaoServico(solicitacaoServico);
        servico.setSituacaoServico(SituacaoServicoEnum.ORCAMENTO);
        servico.setTituloServico(servicoCreateDTO.getTituloServico());
        servico.setDescServico(servicoCreateDTO.getDescServico());
        servico.setDataInclusao(LocalDateTime.now());
        servico.setDataAprovacao(servicoCreateDTO.getDataAprovacao());
        servico.setDataExecucao(servicoCreateDTO.getDataExecucao());
        servico.setDataFinalizacao(servicoCreateDTO.getDataFinalizacao());
        servico.setDataPagamento(servicoCreateDTO.getDataPagamento());
        servico.setValorContratacao(servicoCreateDTO.getValorContratacao());
        servico.setFormaPagtoEnum(servicoCreateDTO.getFormaPagtoEnum());
        servico.setNvlUrgenciaEnum(servicoCreateDTO.getNvlUrgenciaEnum());
        servico.setTipoCategoriaEnum(servicoCreateDTO.getTipoCategoriaEnum());
        servico.setPrevisaoInicio(servicoCreateDTO.getPrevisaoInicio());
        servico.setDuracaoServico(servicoCreateDTO.getDuracaoServico());
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

        if (servicoUpdateDTO.getTituloServico() != null){
            servicoExistente.setTituloServico(servicoUpdateDTO.getTituloServico());
        }

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
        if(servicoUpdateDTO.getDataFinalizacao() != null){
            servicoExistente.setDataFinalizacao(servicoUpdateDTO.getDataFinalizacao());
        }
        if(servicoUpdateDTO.getDataPagamento() != null){
            servicoExistente.setDataPagamento(servicoUpdateDTO.getDataPagamento());
        }
        if(servicoUpdateDTO.getValorContratacao() != 0.0){
            servicoExistente.setValorContratacao(servicoUpdateDTO.getValorContratacao());
        }
        if(servicoUpdateDTO.getPrevisaoInicio() != null){
            servicoExistente.setPrevisaoInicio(servicoUpdateDTO.getPrevisaoInicio());
        }
        if(servicoUpdateDTO.getDuracaoServico() != 0.0){
            servicoExistente.setDuracaoServico(servicoUpdateDTO.getDuracaoServico());
        }

        //salva as informações no banco `!´
        return servicoRepository.save(servicoExistente);
    }

    //deletar servico
    public void delete(int idServico){
        servicoRepository.deleteById(idServico);
    }

    //gerar serviço pela classe Solicitação
    public ServicoDTO gerarServico(ServicoCreateDTO servicoCreateDTO){
        SolicitacaoServico solicitacaoServico = solicitacaoServicoRepository.findById(servicoCreateDTO.getIdSolicitacao())
                .orElseThrow(() -> new IllegalArgumentException("Solicitação não encontrada"));


        Usuario usuario = (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Servico servico = new Servico();

        if (usuario == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Usuário não encontrado");
        }

        if (usuario instanceof Prestador) {
            Prestador prestador = (Prestador) usuario;
            servico.setIdPrestador(prestador);
            EmpresaCliente cliente = solicitacaoServico.getIdEmpresaCliente();
            servico.setIdEmpresaCliente(cliente);
        }
        else if (usuario instanceof EmpresaCliente) {
            EmpresaCliente cliente = (EmpresaCliente) usuario;
            servico.setIdEmpresaCliente(cliente);
            servico.setIdPrestador(solicitacaoServico.getIdPrestador());

        }
        else {
            throw new IllegalArgumentException("Tipo de usuário não suportado para criar serviço.");
        }

        //Dados da Solicitação
        servico.setSolicitacaoServico(solicitacaoServico);
        servico.setSituacaoServico(SituacaoServicoEnum.ORCAMENTO);
        servico.setTituloServico(solicitacaoServico.getTituloSolicitacao());
        servico.setNvlUrgenciaEnum(solicitacaoServico.getNvlUrgenciaEnum());
        servico.setTipoCategoriaEnum(solicitacaoServico.getTipoCategoriaEnum());


        //Dados da DTO de criação, preenchida pelo prestador
        servico.setDuracaoServico(servicoCreateDTO.getDuracaoServico());
        servico.setPrevisaoInicio(servicoCreateDTO.getPrevisaoInicio());
        servico.setDataInclusao(LocalDateTime.now());
        servico.setDescServico(servicoCreateDTO.getDescServico());
        servico.setValorContratacao(servicoCreateDTO.getValorContratacao());
        servico.setFormaPagtoEnum(servicoCreateDTO.getFormaPagtoEnum());

        Servico servicoSalvo = servicoRepository.save(servico);
        return new ServicoDTO(servicoSalvo);
    }

    public ServicoDTO aprovarServico(int idServico){
        Servico servico = servicoRepository.findById(idServico)
                .orElseThrow(() -> new RuntimeException("Serviço não encontrado!"));

        SolicitacaoServico solicitacao = servico.getSolicitacaoServico();

        Usuario usuario = (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (!servico.getSituacaoServico().equals(SituacaoServicoEnum.ORCAMENTO)){
            throw new IllegalArgumentException("Somente orçamentos podem ser aceitos");
        }

        EmpresaCliente cliente = empresaClienteRepository.findByIdUsuario(servico.getIdEmpresaCliente().getIdUsuario());
        if (cliente.getIdUsuario() != usuario.getIdUsuario()){
            throw new IllegalArgumentException("Somente solicitante pode realizar aprovação do orçamento");
        }

        if (solicitacao.getStatusSolicitacaoEnum() == StatusSolicitacaoEnum.INATIVA) {
            throw new IllegalStateException("Essa solicitação já teve um orçamento aprovado.");
        }

        //Atualização do serviço
        servico.setSituacaoServico(SituacaoServicoEnum.PENDENTE_PAGTO);
        servico.setDataAprovacao(LocalDateTime.now());
        servico.setPrevisaoInicio(solicitacao.getPrevisaoInicio());
        servico.setDuracaoServico(solicitacao.getDuracaoServico());

        //Atualização da solicitação

        solicitacao.setStatusSolicitacaoEnum(StatusSolicitacaoEnum.INATIVA);
        solicitacaoServicoRepository.save(solicitacao);
        servicoRepository.save(servico);

        // Rejeita outras propostas da mesma solicitação (opcional)
        List<Servico> outrosServicos = servicoRepository.findBySolicitacaoServicoAndIdServicoNot(solicitacao, idServico);
        for (Servico s : outrosServicos) {
            if (s.getSituacaoServico() == SituacaoServicoEnum.ORCAMENTO || s.getSituacaoServico() == SituacaoServicoEnum.RECUSADO){
                s.setSituacaoServico(SituacaoServicoEnum.RECUSADO);
                servicoRepository.delete(s);
            }
        }

        return new ServicoDTO(servico);
    }

    public ServicoDTO recusarServico(int idServico){
        Servico servico = servicoRepository.findById(idServico)
                .orElseThrow(() -> new RuntimeException("Serviço não encontrado!"));

        Usuario usuario = (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (!servico.getSituacaoServico().equals(SituacaoServicoEnum.ORCAMENTO)){
            throw new IllegalArgumentException("Somente orçamentos podem ser recusados");
        }

        EmpresaCliente cliente = empresaClienteRepository.findByIdUsuario(servico.getIdEmpresaCliente().getIdUsuario());
        if (cliente.getIdUsuario() != usuario.getIdUsuario()){
            throw new IllegalArgumentException("Somente solicitante pode recusar o orçamento");
        }
        servicoRepository.save(servico);

        return new ServicoDTO(servico);

    }

    public ServicoDTO pagarServico(int idServico){
        Servico servico = servicoRepository.findById(idServico)
                .orElseThrow(() -> new RuntimeException("Serviço não encontrado!"));

        Usuario usuario = (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        EmpresaCliente cliente = empresaClienteRepository.findByIdUsuario(servico.getIdEmpresaCliente().getIdUsuario());
        if (cliente.getIdUsuario() != usuario.getIdUsuario()){
            throw new IllegalArgumentException("Somente solicitante pode realizar essa ação");
        }

        //
        float valorTotal = servico.getValorContratacao();  // Supondo que o serviço tenha valor
        float percentualPlataforma = 0.10f; // 10% de comissão da plataforma (exemplo)

        float valorPlataforma = valorTotal * percentualPlataforma;
        float valorPrestador = valorTotal - valorPlataforma;

        Pagamento pagamento = new Pagamento();
        pagamento.setIdServico(servico);
        pagamento.setRecebedor(servico.getIdPrestador());
        pagamento.setValorPagamento(valorTotal);
        pagamento.setValorPlataforma(valorPlataforma);
        pagamento.setValorPrestador(valorPrestador);
        pagamento.setSituacaoRepasseEnum(SituacaoRepasseEnum.AGUARDANDO_REPASSE);
        pagamentoRepository.save(pagamento);

        servico.setPagamento(pagamento);
        servico.setDataPagamento(LocalDateTime.now());
        servico.setSituacaoServico(SituacaoServicoEnum.PENDENTE_INICIO);
        servicoRepository.save(servico);

        // 6. Retornar DTO atualizado
        return new ServicoDTO(servico);
    }

    public ServicoDTO iniciarServico(int idServico){
        Servico servico = servicoRepository.findById(idServico)
                .orElseThrow(() -> new RuntimeException("Serviço não encontrado!"));

        Usuario usuario = (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Prestador prestador = prestadorRepository.findByIdUsuario(servico.getIdPrestador().getIdUsuario());
        if (prestador.getIdUsuario() != usuario.getIdUsuario()){
            throw new IllegalArgumentException("Somente prestador pode realizar essa ação");
        }
        Pagamento pagamento = pagamentoRepository.findByIdPagamento(servico.getPagamento().getIdPagamento());

        pagamento.setSituacaoRepasseEnum(SituacaoRepasseEnum.EM_ANALISE);
        servico.setSituacaoServico(SituacaoServicoEnum.EM_EXECUCAO);
        servico.setDataExecucao(LocalDateTime.now());
        pagamentoRepository.save(pagamento);
        servicoRepository.save(servico);

        return  new ServicoDTO(servico);
    }

    public ServicoDTO finalizarServico(int idServico){
        Servico servico = servicoRepository.findById(idServico)
                .orElseThrow(() -> new RuntimeException("Serviço não encontrado!"));

        Usuario usuario = (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Prestador prestador = prestadorRepository.findByIdUsuario(servico.getIdPrestador().getIdUsuario());
        if (prestador.getIdUsuario() != usuario.getIdUsuario()){
            throw new IllegalArgumentException("Somente prestador pode realizar essa ação");
        }

        Pagamento pagamento = pagamentoRepository.findByIdPagamento(servico.getPagamento().getIdPagamento());

        pagamento.setSituacaoRepasseEnum(SituacaoRepasseEnum.EM_ANALISE);
        servico.setSituacaoServico(SituacaoServicoEnum.PENDENTE_CONFIRMAR_FINALIZACAO);
        pagamentoRepository.save(pagamento);
        servicoRepository.save(servico);

        return  new ServicoDTO(servico);
    }

    public ServicoDTO confirmarFinalizacao(int idServico){
        Servico servico = servicoRepository.findById(idServico)
                .orElseThrow(() -> new RuntimeException("Serviço não encontrado!"));

        Usuario usuario = (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        EmpresaCliente cliente = empresaClienteRepository.findByIdUsuario(servico.getIdEmpresaCliente().getIdUsuario());
        if (cliente.getIdUsuario() != usuario.getIdUsuario()) {
            throw new IllegalArgumentException("Somente cliente pode realizar essa ação");
        }

        Pagamento pagamento = pagamentoRepository.findByIdPagamento(servico.getPagamento().getIdPagamento());

        //
        //FALTA FAZER METODO PARA ENVIAR PAGAMENTO PARA O PRESTADOR
        //

        System.out.println("Prestador acaba de receber o seu pagamento referente o serviço prestado!");
        pagamento.setSituacaoRepasseEnum(SituacaoRepasseEnum.REPASSADO);
        servico.setSituacaoServico(SituacaoServicoEnum.FINALIZADO);
        servico.setDataFinalizacao(LocalDateTime.now());
        pagamentoRepository.save(pagamento);
        servicoRepository.save(servico);

        return  new ServicoDTO(servico);
    }
}
