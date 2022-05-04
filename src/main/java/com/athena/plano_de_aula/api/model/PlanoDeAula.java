package com.athena.plano_de_aula.api.model;

import java.util.ArrayList;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "plano_de_aula")
public class PlanoDeAula {
	
	@Id
	private Integer id;
	
	private Integer idRecurso;
	
	@Column(length = 60)
	private String titulo;
	private String conteudo;
	
	@Column(length = 60)
	private String autor;
	
	@ManyToOne
	private Disciplina disciplina;
	
	
	private ArrayList<Descritor> descritores;
	
	private Boolean ehPublico;
	
	public Boolean getEhPublico() {
		return ehPublico;
	}
	public void setEhPublico(Boolean ehPublico) {
		this.ehPublico = ehPublico;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getIdRecurso() {
		return idRecurso;
	}
	public void setIdRecurso(Integer idRecurso) {
		this.idRecurso = idRecurso;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getConteudo() {
		return conteudo;
	}
	public void setConteudo(String conteudo) {
		this.conteudo = conteudo;
	}
	public String getAutor() {
		return autor;
	}
	public void setAutor(String autor) {
		this.autor = autor;
	}
	public Disciplina getDisciplina() {
		return disciplina;
	}
	public void setDisciplina(Disciplina disciplina) {
		this.disciplina = disciplina;
	}
	public ArrayList<Descritor> getDescritores() {
		return descritores;
	}
	public void setDescritores(ArrayList<Descritor> descritores) {
		this.descritores = descritores;
	}
	
}
