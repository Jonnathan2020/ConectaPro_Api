package com.perseus.conectapro.Repository;

import com.perseus.conectapro.Entity.Orcamento;
import com.perseus.conectapro.Entity.Prestador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface OrcamentoRepository extends JpaRepository<Orcamento, Integer> {
    List<Orcamento> findByIdPrestador(Prestador idPrestador);
}
