package com.perseus.conectapro.Repository;

import com.perseus.conectapro.Entity.Enuns.StatusDisponibilidadeEnum;
import com.perseus.conectapro.Entity.Prestador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface PrestadorRepository extends JpaRepository<Prestador, Integer>, JpaSpecificationExecutor<Prestador> {

    @Query("select p from Prestador p left join fetch p.especialidades where p.idUsuario = :idUsuario")
    Optional<Prestador> findByIdUsuarioWithEspecialidades(@Param("idUsuario") Integer idUsuario);
    Prestador findByIdUsuario(int idUsuario);
    List<Prestador> findByNomeContainingIgnoreCase(String name);
    List<Prestador> findByEspecialidadesContaining(String especialidade);
    List<Prestador> findByStatusDisponibilidade(StatusDisponibilidadeEnum statusDisponibilidadeEnum);

}
