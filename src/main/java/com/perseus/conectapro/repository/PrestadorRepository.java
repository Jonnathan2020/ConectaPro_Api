package com.perseus.conectapro.Repository;

import com.perseus.conectapro.Entity.Prestador;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PrestadorRepository extends JpaRepository<Prestador, Integer> {

    List<Prestador> findByNome(String name);
    List<Prestador> findByEspecialidadesContaining(String especialidade);

}
