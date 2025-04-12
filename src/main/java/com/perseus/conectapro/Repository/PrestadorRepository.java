package com.perseus.conectapro.Repository;

import com.perseus.conectapro.Entity.Prestador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PrestadorRepository extends JpaRepository<Prestador, Integer> {

    List<Prestador> findByNome(String name);
    List<Prestador> findByEspecialidadesContaining(String especialidade);

}
