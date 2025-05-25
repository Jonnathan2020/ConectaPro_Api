package com.perseus.conectapro.specification;

import com.perseus.conectapro.DTO.filtro.BuscaSolicitacaoServicoFiltro;
import com.perseus.conectapro.Entity.SolicitacaoServico;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class BuscaSolicitacaoServicoSpecification {

    public static Specification<SolicitacaoServico> comFiltro(BuscaSolicitacaoServicoFiltro filtro) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (filtro.getTermo() != null && !filtro.getTermo().isBlank()) {
                String likeTermo = "%" + filtro.getTermo().toLowerCase() + "%";
                predicates.add(
                        cb.or(
                                cb.like(cb.lower(root.get("tituloSolicitacao")), likeTermo),
                                cb.like(cb.lower(root.get("descSolicitacao")), likeTermo)
                        )
                );
            }

            if (filtro.getStatus() != null && !filtro.getStatus().isBlank()) {
                predicates.add(cb.equal(root.get("statusSolicitacao"), filtro.getStatus()));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
