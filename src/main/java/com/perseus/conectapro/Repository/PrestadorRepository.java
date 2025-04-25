package com.perseus.conectapro.Repository;

import com.perseus.conectapro.Entity.Prestador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PrestadorRepository extends JpaRepository<Prestador, Integer>, JpaSpecificationExecutor<Prestador> {

    List<Prestador> findByIdUsuario(int idUsuario);
    List<Prestador> findByNomeContainingIgnoreCase(String name);
    List<Prestador> findByEspecialidadesContaining(String especialidade);
    List<Prestador> findByStatusDisponibilidadeContaining(StatusDisponibilidadeEnum statusDisponibilidade);


}
