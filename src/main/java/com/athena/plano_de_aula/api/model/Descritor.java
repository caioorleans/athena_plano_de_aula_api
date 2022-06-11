package com.athena.plano_de_aula.api.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "descritor")
@NamedQuery(name = "Descritor.findByDisciplina",
query = "select d from Descritor d where d.disciplina.id = ?1")
public class Descritor {
	
	@Id
	private String id;
	
	private String descricao;
	
	@ManyToOne
	private Disciplina disciplina;
	
	public Descritor() {
	}
	public Descritor(String id, String descricao, Disciplina disciplina) {
		this.id = id;
		this.descricao = descricao;
		this.disciplina = disciplina;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public Disciplina getDisciplina() {
		return disciplina;
	}
	public void setDisciplina(Disciplina disciplina) {
		this.disciplina = disciplina;
	}
}
