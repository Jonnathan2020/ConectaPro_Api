package com.perseus.conectapro.Repository;

import com.perseus.conectapro.Entity.Prestador;
import com.perseus.conectapro.Entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PrestadorRepository extends JpaRepository<Prestador, Integer> {

    List<Prestador> findByEspecialidadesContaining(String especialidade);
    List<Prestador> findByNome(String nome);


    /*
    List<Prestador> findByHabilidadesContaining(String habilidade);
     */
}
