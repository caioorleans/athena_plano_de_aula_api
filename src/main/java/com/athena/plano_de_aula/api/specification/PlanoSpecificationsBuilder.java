package com.athena.plano_de_aula.api.specification;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.jpa.domain.Specification;

import com.athena.plano_de_aula.api.model.PlanoDeAula;

public class PlanoSpecificationsBuilder {
	private final List<SearchCriteria> params;

    public PlanoSpecificationsBuilder() {
        params = new ArrayList<SearchCriteria>();
    }

    public PlanoSpecificationsBuilder with(String key, String operation, Object value) {
        params.add(new SearchCriteria(key, operation, value));
        return this;
    }

	public Specification<PlanoDeAula> build() {
        if (params.size() == 0) {
            return null;
        }

        List<Specification> specs = params.stream()
          .map(PlanoSpecification::new)
          .collect(Collectors.toList());
        
        Specification result = specs.get(0);

        for (int i = 1; i < params.size(); i++) {
            result = Specification.where(result).and(specs.get(i));
        }       
        return result;
    }
}
