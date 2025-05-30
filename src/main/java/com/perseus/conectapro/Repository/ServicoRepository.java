package com.perseus.conectapro.Repository;


import com.perseus.conectapro.Entity.*;
import com.perseus.conectapro.Entity.Enuns.SituacaoServicoEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ServicoRepository extends JpaRepository<Servico, Integer> {
    List<Servico> findBySolicitacaoServicoAndIdServicoNot(SolicitacaoServico solicitacao, Integer idServico);

    // Serviços já prestados pelo prestador
    List<Servico> findByIdPrestadorIdUsuarioAndSituacaoServicoIn(Long idPrestador, List<SituacaoServicoEnum> situacoes);

    // Candidaturas feitas pelo prestador (ORCAMENTO criados por ele)
    List<Servico> findByIdPrestadorIdUsuarioAndSituacaoServico(Long idPrestador, SituacaoServicoEnum situacao);

    // Propostas feitas pela empresa para este prestador
    List<Servico> findByIdPrestadorIdUsuarioAndSituacaoServicoAndIdEmpresaClienteIsNotNull(Long idPrestador, SituacaoServicoEnum situacao);

}

