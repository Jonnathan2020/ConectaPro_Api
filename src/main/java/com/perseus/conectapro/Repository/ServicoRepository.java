package com.perseus.conectapro.Repository;


import com.perseus.conectapro.Entity.*;
import com.perseus.conectapro.Entity.Enuns.StatusServicoEnum;
import com.perseus.conectapro.Entity.Enuns.StatusSolicitacaoEnum;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ServicoRepository extends JpaRepository<Servico, Integer> {
    List<Servico> findBySolicitacaoServicoAndIdServicoNot(SolicitacaoServico solicitacaoServico, Integer idServico);

    // Serviços já prestados pelo prestador
    List<Servico> findByIdPrestadorIdUsuarioAndStatusServicoIn(int idPrestador, List<StatusServicoEnum> statusServico);

    // Candidaturas feitas pelo prestador (ORCAMENTO criados por ele)
    List<Servico> findByIdPrestadorIdUsuarioAndStatusServico(int idPrestador, StatusServicoEnum statusServico);

    // Propostas feitas pela empresa para este prestador
    List<Servico> findByIdPrestadorIdUsuarioAndStatusServicoAndIdEmpresaClienteIsNotNull(int idPrestador, StatusServicoEnum statusServico);

    // Propostas recebidas
    List<Servico> findByIdEmpresaClienteIdUsuarioAndIdPrestadorIsNotNullAndStatusServico(int idEmpresaCliente, StatusServicoEnum statusServico);
    
    // Servicos concluidos empresa
    List<Servico> findByIdEmpresaClienteIdUsuarioAndStatusServicoNotIn(int idEmpresaCliente, List<StatusServicoEnum> statusExcluidos);
    
}

