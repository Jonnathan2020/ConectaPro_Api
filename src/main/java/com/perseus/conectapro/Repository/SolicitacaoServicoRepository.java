package com.perseus.conectapro.Repository;

import com.perseus.conectapro.Entity.SolicitacaoServico;
import com.perseus.conectapro.Entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface SolicitacaoServicoRepository extends JpaRepository<SolicitacaoServico, Integer>, JpaSpecificationExecutor<SolicitacaoServico> {
    List<SolicitacaoServico> findByIdUsuario(Usuario idUsuario);

}
