package com.athena.plano_de_aula.api.dto;

import java.util.List;

import javax.validation.constraints.NotNull;

import com.athena.plano_de_aula.api.model.RecursoId;

public class PlanoFormulario {
	
	@NotNull
	private Integer id;
	@NotNull
	private String plataforma;
	@NotNull
	private String titulo;
	@NotNull
	private String conteudo;
	@NotNull
	private String autor;
	@NotNull
	private Integer idDisciplina;
	@NotNull
	private List<String> idDescritores;
	@NotNull
	private List<RecursoId> idRecursos;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getPlataforma() {
		return plataforma;
	}
	public void setPlataforma(String plataforma) {
		this.plataforma = plataforma;
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
	public Integer getIdDisciplina() {
		return idDisciplina;
	}
	public void setIdDisciplina(Integer idDisciplina) {
		this.idDisciplina = idDisciplina;
	}
	public List<String> getIdDescritores() {
		return idDescritores;
	}
	public void setIdDescritores(List<String> idDescritores) {
		this.idDescritores = idDescritores;
	}
	public List<RecursoId> getIdRecursos() {
		return idRecursos;
	}
	public void setIdRecursos(List<RecursoId> idRecursos) {
		this.idRecursos = idRecursos;
	}
	
	
}
