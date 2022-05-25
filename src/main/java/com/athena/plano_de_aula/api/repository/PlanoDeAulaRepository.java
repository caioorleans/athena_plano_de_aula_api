package com.athena.plano_de_aula.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.athena.plano_de_aula.api.model.PlanoDeAula;

public interface PlanoDeAulaRepository extends JpaRepository<PlanoDeAula, Integer>{

	public List<PlanoDeAula> findByTituloContains(String titulo);
}
