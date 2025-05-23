package com.perseus.conectapro.specification;

import com.perseus.conectapro.DTO.filtro.BuscaPrestadorFiltro;
import com.perseus.conectapro.Entity.Enuns.TipoCategoriaEnum;
import com.perseus.conectapro.Entity.Prestador;
import com.perseus.conectapro.Entity.Segmento;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class BuscaPrestadorSpecification {

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

    public static Specification<Prestador> comFiltro(BuscaPrestadorFiltro filtro) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (filtro.getTermo() != null && !filtro.getTermo().isBlank()) {
                String termo = filtro.getTermo().toLowerCase();

                // join com a lista tipoCategoria (ElementCollection)
                Join<Prestador, TipoCategoriaEnum> categorias = root.join("tipoCategoria");

                Predicate nomePredicate = cb.like(cb.lower(root.get("nome")), "%" + termo + "%");
                Predicate categoriaPredicate = cb.like(cb.lower(categorias.as(String.class)), "%" + termo + "%");

                // evitar duplicados por causa do join
                query.distinct(true);

                predicates.add(cb.or(nomePredicate, categoriaPredicate));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }


}
