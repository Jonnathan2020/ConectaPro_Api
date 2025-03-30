package com.perseus.conectapro.Repository;

import com.perseus.conectapro.Entity.Prestador;
import com.perseus.conectapro.Entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PrestadorRepository extends JpaRepository<Prestador, Integer> {

    List<Prestador> findByNome(String name);
    List<Prestador> findByEspecialidadesContaining(String especialidade);

}
