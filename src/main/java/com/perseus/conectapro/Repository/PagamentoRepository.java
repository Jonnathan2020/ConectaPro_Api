package com.perseus.conectapro.Repository;

import com.perseus.conectapro.Entity.Pagamento;
import com.perseus.conectapro.Entity.Servico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PagamentoRepository extends JpaRepository<Pagamento, Integer> {

    boolean existsByIdServico(Servico servico);
    Pagamento findByIdPagamento(int idPagamento);
    List<Pagamento> findByRecebedor_IdUsuario(int idUsuario);
}
