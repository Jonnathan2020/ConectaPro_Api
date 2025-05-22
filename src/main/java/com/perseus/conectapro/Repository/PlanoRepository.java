package com.perseus.conectapro.Repository;

import com.perseus.conectapro.Entity.Plano;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlanoRepository extends JpaRepository<Plano, Integer> {
}
