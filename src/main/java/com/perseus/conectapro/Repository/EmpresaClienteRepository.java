package com.perseus.conectapro.Repository;

import com.perseus.conectapro.Entity.EmpresaCliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmpresaClienteRepository extends JpaRepository<EmpresaCliente, Integer>, JpaSpecificationExecutor<EmpresaCliente> {
    List<EmpresaCliente> findByNome(String name);
}
