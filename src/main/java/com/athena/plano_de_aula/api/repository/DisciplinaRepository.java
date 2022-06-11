package com.athena.plano_de_aula.api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.athena.plano_de_aula.api.model.Disciplina;

public interface DisciplinaRepository extends JpaRepository<Disciplina, Integer>{
	public Optional<Disciplina> findByDescricao(String descricao);
}
