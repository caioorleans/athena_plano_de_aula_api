package com.athena.plano_de_aula.api.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.athena.plano_de_aula.api.model.Disciplina;
import com.athena.plano_de_aula.api.model.Recurso;
import com.athena.plano_de_aula.api.model.RecursoId;

public interface RecursoRepository extends CrudRepository<Recurso, RecursoId>{
	public List<Recurso> findByTituloIgnoreCaseContaining(String titulo);
	
	public List<Recurso> findByDisciplinaOrderByTituloAsc(Disciplina disciplina);
}
