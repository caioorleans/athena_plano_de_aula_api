package com.athena.plano_de_aula.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.athena.plano_de_aula.api.model.Descritor;

public interface DescritorRepository extends JpaRepository<Descritor, String> {
	
	List<Descritor> findByDisciplina(Integer id);
	
}
