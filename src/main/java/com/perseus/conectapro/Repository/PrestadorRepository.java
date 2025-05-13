package com.perseus.conectapro.Repository;

import com.perseus.conectapro.Entity.Enuns.StatusDisponibilidadeEnum;
import com.perseus.conectapro.Entity.Prestador;
import com.perseus.conectapro.Entity.Segmento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PrestadorRepository extends JpaRepository<Prestador, Integer>, JpaSpecificationExecutor<Prestador> {

    List<Prestador> findByIdUsuario(int idUsuario);
    List<Prestador> findByNomeContainingIgnoreCase(String name);
    List<Prestador> findByEspecialidadesContaining(String especialidade);
    List<Prestador> findByStatusDisponibilidade(StatusDisponibilidadeEnum statusDisponibilidadeEnum);
    List<Prestador> findBySegmentosContaining(Segmento segmento);
    boolean existsByEmail(String email);

}
