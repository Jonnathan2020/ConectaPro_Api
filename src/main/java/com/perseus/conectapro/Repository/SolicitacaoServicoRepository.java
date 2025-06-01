package com.perseus.conectapro.Repository;

import com.perseus.conectapro.Entity.Servico;
import com.perseus.conectapro.Entity.SolicitacaoServico;
import com.perseus.conectapro.Entity.Usuario;
import com.perseus.conectapro.Entity.Enuns.StatusServicoEnum;
import com.perseus.conectapro.Entity.Enuns.StatusSolicitacaoEnum;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface SolicitacaoServicoRepository extends JpaRepository<SolicitacaoServico, Integer>, JpaSpecificationExecutor<SolicitacaoServico> {

    List<SolicitacaoServico> findByIdUsuario(Usuario idUsuario);

    // Solicitacoes feitas pela empresa
    List<SolicitacaoServico> findByIdEmpresaClienteIdUsuarioAndIdPrestadorIsNullAndStatusSolicitacaoIn(int idEmpresaCliente, List<StatusSolicitacaoEnum> statusSolicitacao);

    // Propostas recebidas
    List<SolicitacaoServico> findByIdEmpresaClienteIdUsuarioAndIdPrestadorIsNotNullAndStatusSolicitacao(int idEmpresaCliente, StatusSolicitacaoEnum statusSolicitacao);

    
}
