package com.comanda_digital.infrastructure.specification;

import com.comanda_digital.domain.model.Comercio;
import com.comanda_digital.domain.model.TipoComercio;
import jakarta.persistence.criteria.Join;
import org.springframework.data.jpa.domain.Specification;

public class ComercioSpecification {

    public static Specification<Comercio> likeNome(String nome) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.like(criteriaBuilder.lower(root.get("nome")), "%" + nome.toLowerCase() + "%");
    }

    public static Specification<Comercio> equalTipo(String tipo) {
        return (root, query, criteriaBuilder) -> {
            Join<Comercio, TipoComercio> tipoComercioJoin = root.join("tipo");
            return criteriaBuilder.equal(criteriaBuilder.lower(tipoComercioJoin.get("nome")), tipo.toLowerCase());
        };
    }
}
