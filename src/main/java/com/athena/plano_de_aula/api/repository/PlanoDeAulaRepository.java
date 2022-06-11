package com.athena.plano_de_aula.api.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.athena.plano_de_aula.api.model.PlanoDeAula;

public interface PlanoDeAulaRepository extends JpaRepository<PlanoDeAula, Integer>, JpaSpecificationExecutor<PlanoDeAula>{

	//public List<PlanoDeAula> findByAnoAndTituloContains(Integer ano,String titulo, Pageable pageable);
	
	//public List<PlanoDeAula> findByRecursosAndPlataforma(Integer recursos, String Plataforma, Pageable pageable);
}
