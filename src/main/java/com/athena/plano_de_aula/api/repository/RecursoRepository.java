package com.athena.plano_de_aula.api.repository;

import org.springframework.data.repository.CrudRepository;

import com.athena.plano_de_aula.api.model.Recurso;
import com.athena.plano_de_aula.api.model.RecursoId;

public interface RecursoRepository extends CrudRepository<Recurso, RecursoId>{

}
