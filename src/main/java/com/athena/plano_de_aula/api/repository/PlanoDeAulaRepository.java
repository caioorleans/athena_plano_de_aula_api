package com.athena.plano_de_aula.api.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.athena.plano_de_aula.api.model.Descritor;
import com.athena.plano_de_aula.api.model.PlanoDeAula;
import com.athena.plano_de_aula.api.model.Plataforma;
import com.athena.plano_de_aula.api.model.Recurso;

public interface PlanoDeAulaRepository extends JpaRepository<PlanoDeAula, Integer>, JpaSpecificationExecutor<PlanoDeAula>{
	
	public Page<PlanoDeAula> findByRecursosInAndPublico(List<Recurso> recursos, boolean publico, Pageable pageable);
	
	public Page<PlanoDeAula> findByRecursos(Recurso recursos, Pageable pageable);
	
	public Page<PlanoDeAula> findByPublico(boolean publico, Pageable pageable);
	
	public Page<PlanoDeAula> findByDescritoresAndAnoAndPlataformaLikeAndPublico(
			Descritor descritores, Integer ano, Plataforma plataforma, Boolean publico, Pageable pageable);
	
	public Page<PlanoDeAula> findByDescritoresAndPlataformaLikeAndPublico(
			Descritor descritores, Plataforma plataforma, Boolean publico, Pageable pageable);
	
	public Page<PlanoDeAula> findByDescritoresAndAnoAndPublico(
			Descritor descritores, Integer ano, Boolean publico, Pageable pageable);
	
	public Page<PlanoDeAula> findByDescritoresAndPublico(
			Descritor descritores, Boolean publico, Pageable pageable);

	public Optional<PlanoDeAula> findByIdAndPublico(Integer id, Boolean publico);
}
