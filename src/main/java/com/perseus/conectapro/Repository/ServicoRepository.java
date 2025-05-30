package com.perseus.conectapro.Repository;


import com.perseus.conectapro.Entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ServicoRepository extends JpaRepository<Servico, Integer> {
    List<Servico> findBySolicitacaoServicoAndIdServicoNot(SolicitacaoServico solicitacao, Integer idServico);
}

