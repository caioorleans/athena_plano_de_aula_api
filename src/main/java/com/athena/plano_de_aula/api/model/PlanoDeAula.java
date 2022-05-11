package com.athena.plano_de_aula.api.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "plano_de_aula")
public class PlanoDeAula {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private Integer idRecurso;
	
	@Column(length = 60)
	private String titulo;
	
	@Column(length = 1000)
	private String conteudo;
	
	@Column(length = 60)
	private String autor;
	
	@ManyToOne
	private Disciplina disciplina;
	
	@ManyToMany
	@JoinTable(name="plano_possui_descritores",joinColumns={@JoinColumn(name="plano_id")}, inverseJoinColumns={@JoinColumn(name="descritor_id")})
	private List<Descritor> descritores;
	
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
	public List<Descritor> getDescritores() {
		return descritores;
	}
	public void setDescritores(List<Descritor> list) {
		this.descritores = list;
	}
	
}
