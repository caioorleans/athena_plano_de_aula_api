package com.athena.plano_de_aula.api.specification;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
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
                return builder.like(
                  root.<String>get(criteria.getKey()), "%" + criteria.getValue() + "%");
            } else {
                return builder.equal(root.get(criteria.getKey()), criteria.getValue());
            }
        }
        else if(criteria.getOperation().equalsIgnoreCase("in")) {
        	return builder.in(root.get(criteria.getKey())).value(criteria.getValue());
        }
        return null;
    }
	
	private Object castToRequiredType(Class fieldType, String value) {
		  if(fieldType.isAssignableFrom(Double.class)) {
		    return Double.valueOf(value);
		  } else if(fieldType.isAssignableFrom(Integer.class)) {
		    return Integer.valueOf(value);
		  } else if(Enum.class.isAssignableFrom(fieldType)) {
		    return Enum.valueOf(fieldType, value);
		  }
		  return null;
		}
	
	private Object castToRequiredType(Class fieldType, List<String> value) {
		  List<Object> lists = new ArrayList<>();
		  for (String s : value) {
		    lists.add(castToRequiredType(fieldType, s));
		  }
		  return lists;
	}
}
