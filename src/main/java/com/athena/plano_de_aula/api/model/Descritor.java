package com.athena.plano_de_aula.api.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table
@NamedQuery(name = "Descritor.findByDisciplina", query = "select d from Descritor d where d.disciplina.id = ?1")
@NoArgsConstructor @AllArgsConstructor @Getter @Setter
public class Descritor {
	
	@Id
	private String id;
	
	@Column(length = 500, nullable = false)
	private String descricao;
	
	@ManyToOne
	private Disciplina disciplina;
	
}
