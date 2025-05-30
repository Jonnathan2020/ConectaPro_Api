package com.perseus.conectapro.specification;

import com.perseus.conectapro.DTO.filtro.BuscaPrestadorFiltro;
import com.perseus.conectapro.Entity.Prestador;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class BuscaPrestadorSpecification {

    public static Specification<Prestador> comFiltro(BuscaPrestadorFiltro filtro) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (filtro.getTermo() != null && !filtro.getTermo().isBlank()) {
                String termo = filtro.getTermo().toLowerCase();

                // join na coleção tipoCategoria (enum)
                Join<Object, Object> categorias = root.join("tipoCategoria", JoinType.LEFT);

                Predicate nomePredicate = cb.like(cb.lower(root.get("nome")), termo + "%");
                Predicate categoriaPredicate = cb.like(cb.lower(categorias.as(String.class)),termo + "%");

                // evitar duplicados causados pelo join
                query.distinct(true);

                predicates.add(cb.or(nomePredicate, categoriaPredicate));
            }

            if (filtro.getUf() != null) {
                Join<Object, Object> enderecoJoin = root.join("endereco", JoinType.LEFT);
                predicates.add(cb.equal(enderecoJoin.get("uf"), filtro.getUf()));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }



}
