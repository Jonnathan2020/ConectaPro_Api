package com.perseus.conectapro.Repository;

import com.perseus.conectapro.Entity.Segmento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SegmentoRepository extends JpaRepository<Segmento, Integer> {
}
