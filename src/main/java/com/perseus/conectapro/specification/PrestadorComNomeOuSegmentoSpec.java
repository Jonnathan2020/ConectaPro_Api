package com.perseus.conectapro.specification;

import com.perseus.conectapro.Entity.Prestador;
import com.perseus.conectapro.Entity.Segmento;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

public class PrestadorComNomeOuSegmentoSpec {

    public static Specification<Prestador> nomeOuSegmentoContem(String termo) {
        return (root, query, builder) -> {
            // evitar duplicados por join ManyToMany
            query.distinct(true);

            String likePattern = termo.toLowerCase() + "%";

            // join com segmentos
            Join<Prestador, Segmento> segmentos = root.join("segmentos", JoinType.LEFT);

            Predicate nomePredicate = builder.like(builder.lower(root.get("nome")), likePattern);
            Predicate segmentoPredicate = builder.like(builder.lower(segmentos.get("descSegmento")), likePattern);

            return builder.or(nomePredicate, segmentoPredicate);
        };
    }

}
