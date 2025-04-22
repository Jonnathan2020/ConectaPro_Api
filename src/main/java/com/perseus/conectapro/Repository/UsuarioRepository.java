package com.perseus.conectapro.Repository;

import com.perseus.conectapro.Entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer>, JpaSpecificationExecutor<Usuario> {
    List<Usuario> findByNomeContainingIgnoreCase(String name);
    Usuario findByEmail(String email);

}
