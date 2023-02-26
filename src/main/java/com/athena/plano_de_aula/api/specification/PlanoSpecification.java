package com.athena.plano_de_aula.api.specification;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.athena.plano_de_aula.api.model.Descritor;
import com.athena.plano_de_aula.api.model.PlanoDeAula;

public class PlanoSpecification implements Specification<PlanoDeAula>{
	
	private SearchCriteria criteria;
	
	public PlanoSpecification(SearchCriteria criteria) {
		super();
		this.criteria = criteria;
	}

	@Override
    public Predicate toPredicate
      (Root<PlanoDeAula> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
		
        if (criteria.getOperation().equalsIgnoreCase(":")) {
            if (root.get(criteria.getKey()).getJavaType() == String.class) {
            	if(criteria.getKey().equals("descritores")) {
            		return builder.equal(descritoresJoin(root).get(criteria.getKey()), criteria.getValue());
            	}
                return builder.like(root.<String>get(criteria.getKey()), "%" + criteria.getValue() + "%");
            } else {
                return builder.equal(root.get(criteria.getKey()), criteria.getValue());
            }
        }
        return null;
    }
	
	private Join<PlanoDeAula,Descritor> descritoresJoin(Root<PlanoDeAula> root){
		return root.join("descritores");
	}
	
}
