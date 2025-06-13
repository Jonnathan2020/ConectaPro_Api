package com.perseus.conectapro.Service;

import com.perseus.conectapro.DTO.*;
import com.perseus.conectapro.Entity.EmpresaCliente;
import com.perseus.conectapro.Entity.Enuns.StatusSolicitacaoEnum;
import com.perseus.conectapro.Entity.SolicitacaoServico;
import com.perseus.conectapro.Entity.Prestador;
import com.perseus.conectapro.Entity.Usuario;
import com.perseus.conectapro.Repository.SolicitacaoServicoRepository;
import com.perseus.conectapro.Repository.PrestadorRepository;
import jakarta.transaction.Transactional;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SolicitacaoServicoService {

    @Autowired
    private ServicoService servicoService;
    @Autowired
    private SolicitacaoServicoRepository solicitacaoServicoRepository;
    @Autowired
    private PrestadorRepository prestadorRepository;

    @Transactional
    public SolicitacaoServicoDTO cadastrarSolicitacao(SolicitacaoServicoCreateDTO solicitacaoServicoCreateDTO)
    {
        SolicitacaoServico solicitacaoServico = new SolicitacaoServico();
        Usuario usuario = (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (usuario == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Usuário não encontrado");
        }


        if (usuario instanceof EmpresaCliente) {
            EmpresaCliente empresaCliente = (EmpresaCliente) usuario;
            solicitacaoServico.setIdEmpresaCliente(empresaCliente);
        }
        else if (usuario instanceof Prestador) {
            Prestador prestador = (Prestador) usuario;
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Tipo de usuário não é permitido para essa ação");
        }
        else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Tipo de usuário inválido.");
        }

        Prestador prestadorOpcional = prestadorRepository.findByIdUsuario(solicitacaoServicoCreateDTO.getIdPrestador());
        if (prestadorOpcional != null){
            solicitacaoServico.setIdPrestador(prestadorOpcional);
        }

        solicitacaoServico.setIdUsuario(usuario); //<- satifaz o a busca por usuario
        solicitacaoServico.setTituloSolicitacao(solicitacaoServicoCreateDTO.getTituloSolicitacao());
        solicitacaoServico.setDescSolicitacao(solicitacaoServicoCreateDTO.getDescSolicitacao());
        solicitacaoServico.setDataInclusao(LocalDateTime.now());
        solicitacaoServico.setPrevisaoInicio(solicitacaoServicoCreateDTO.getPrevisaoInicio());
        solicitacaoServico.setDuracaoServico(solicitacaoServicoCreateDTO.getDuracaoServico());
        solicitacaoServico.setValorProposto(solicitacaoServicoCreateDTO.getValorProposto());
        solicitacaoServico.setFormaPagto(solicitacaoServicoCreateDTO.getFormaPagto());
        solicitacaoServico.setNvlUrgencia(solicitacaoServicoCreateDTO.getNvlUrgencia());
        solicitacaoServico.setTipoCategoria(solicitacaoServicoCreateDTO.getTipoCategoria());
        solicitacaoServico.setStatusSolicitacao(StatusSolicitacaoEnum.ATIVA);

        SolicitacaoServico solicitacaoServicoCriado = solicitacaoServicoRepository.save(solicitacaoServico);
        Prestador prestador = solicitacaoServicoCriado.getIdPrestador();
        if (prestador != null) {
            Hibernate.initialize(prestador.getEspecialidades());
        }
        
        return new SolicitacaoServicoDTO(solicitacaoServicoCriado);
    }

    public List<SolicitacaoServicoDTO> consultarSolicitacoes(){

        List<SolicitacaoServico> solicitacaoServicos = solicitacaoServicoRepository.findAll();
        if (solicitacaoServicos.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        return solicitacaoServicos.stream()
                .map(SolicitacaoServicoDTO::new)
                .collect(Collectors.toList());
    }

    public SolicitacaoServico alterarSolicitacao(int idSolicitacao, SolicitacaoServicoUpdateDTO solicitacaoServicoUpdateDTO){

        SolicitacaoServico solicitacaoServicoExistente = solicitacaoServicoRepository.findById(idSolicitacao)
                .orElseThrow(() -> new IllegalArgumentException("Solicitação não encontrada!!"));

        if(solicitacaoServicoUpdateDTO.getTituloSolicitacao() != null){
            solicitacaoServicoExistente.setTituloSolicitacao(solicitacaoServicoUpdateDTO.getTituloSolicitacao());
        }
        if (solicitacaoServicoUpdateDTO.getDescSolicitacao() != null){
            solicitacaoServicoExistente.setDescSolicitacao(solicitacaoServicoUpdateDTO.getDescSolicitacao());
        }
        if(solicitacaoServicoUpdateDTO.getValorProposto() !=null){
            solicitacaoServicoExistente.setValorProposto(solicitacaoServicoUpdateDTO.getValorProposto());
        }
        if(solicitacaoServicoUpdateDTO.getPrevisaoInicio() !=null){
            solicitacaoServicoExistente.setPrevisaoInicio(solicitacaoServicoUpdateDTO.getPrevisaoInicio());
        }
        if(solicitacaoServicoUpdateDTO.getDuracaoServico() != 0){
            solicitacaoServicoExistente.setDuracaoServico(solicitacaoServicoUpdateDTO.getDuracaoServico());
        }
        if(solicitacaoServicoUpdateDTO.getFormaPagto() !=null){
            solicitacaoServicoExistente.setFormaPagto(solicitacaoServicoUpdateDTO.getFormaPagto());
        }
        if(solicitacaoServicoUpdateDTO.getNvlUrgencia() !=null){
            solicitacaoServicoExistente.setNvlUrgencia(solicitacaoServicoUpdateDTO.getNvlUrgencia());
        }
        if(solicitacaoServicoUpdateDTO.getTipoCategoria() !=null){
            solicitacaoServicoExistente.setTipoCategoria(solicitacaoServicoUpdateDTO.getTipoCategoria());
        }
        return solicitacaoServicoRepository.save(solicitacaoServicoExistente);
    }

    public SolicitacaoServicoDTO consultarSolicitacaoUnica(int id){
        SolicitacaoServico solicitacaoServicoEspecifico = solicitacaoServicoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(("Nenhuma solicitação encontrada com essas informações,!")));
        return new SolicitacaoServicoDTO(solicitacaoServicoEspecifico);
    }

    public List<SolicitacaoServicoDTO> consultarSolicitacaoPorUsuario(Usuario usuario) {
        return solicitacaoServicoRepository.findByIdUsuario(usuario)
                .stream()
                .map(SolicitacaoServicoDTO::new)
                .collect(Collectors.toList());
    }

    public void delete(int idOrcamento) {
        solicitacaoServicoRepository.deleteById(idOrcamento);
    }

    //Criação da
    public ServicoDTO candidaturaSolicitacao(int idSolicitacao, ServicoCreateDTO servicoCreateDTO) {
        SolicitacaoServico solicitacao = solicitacaoServicoRepository.findById(idSolicitacao)
                .orElseThrow(() -> new RuntimeException("Solicitação não encontrada"));

        servicoCreateDTO.setIdSolicitacao(solicitacao.getIdSolicitacao()); // Garante o vínculo

        return servicoService.gerarServico(servicoCreateDTO);

    }

    public SolicitacaoServicoDTO desativarSolicitacao(int idSolicitacao){
        SolicitacaoServico solicitacaoServicoExistente = solicitacaoServicoRepository.findById(idSolicitacao)
                .orElseThrow(() -> new IllegalArgumentException("Solicitação não encontrada!!"));

        if(solicitacaoServicoExistente.getStatusSolicitacao().equals(StatusSolicitacaoEnum.ATIVA)){
            solicitacaoServicoExistente.setStatusSolicitacao(StatusSolicitacaoEnum.INATIVA);
        } else if (solicitacaoServicoExistente.getStatusSolicitacao().equals(StatusSolicitacaoEnum.INATIVA)) {
            throw new IllegalArgumentException("Solicitação já está desativada");
        }
        else {
            throw new RuntimeException("Situação da solicitação diferente do esperado");
        }

        SolicitacaoServico solicitacaoSalva = solicitacaoServicoRepository.save(solicitacaoServicoExistente);
        return new SolicitacaoServicoDTO(solicitacaoSalva);
    }
}
