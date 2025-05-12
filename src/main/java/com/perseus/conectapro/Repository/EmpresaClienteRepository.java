package com.perseus.conectapro.Repository;

import com.perseus.conectapro.Entity.EmpresaCliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmpresaClienteRepository extends JpaRepository<EmpresaCliente, Integer>, JpaSpecificationExecutor<EmpresaCliente> {
    List<EmpresaCliente> findByNomeContainingIgnoreCase(String name);
    List<EmpresaCliente> findByTipoUsuario(String tipoUsuario);
    boolean existsByEmail(String email);

}
