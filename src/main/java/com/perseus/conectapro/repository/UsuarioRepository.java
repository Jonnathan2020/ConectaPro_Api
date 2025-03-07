package com.perseus.conectapro.Repository;

import com.perseus.conectapro.Entity.Prestador;
import com.perseus.conectapro.Entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    List<Usuario> findByName(String nome);
}
