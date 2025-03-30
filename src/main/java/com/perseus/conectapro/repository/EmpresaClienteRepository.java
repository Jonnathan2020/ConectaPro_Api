package com.perseus.conectapro.Repository;

import com.perseus.conectapro.Entity.EmpresaCliente;
import com.perseus.conectapro.Entity.Prestador;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmpresaClienteRepository extends JpaRepository<EmpresaCliente, Integer> {
    List<EmpresaCliente> findByNome(String name);
}
