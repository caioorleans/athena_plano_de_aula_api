package com.athena.plano_de_aula.api.dto;

import java.util.List;

import com.athena.plano_de_aula.api.model.Disciplina;

public class PlanoDeAulaDTO {
	
	private Integer id;
	
	private String titulo;
	
	private String conteudo;
	
	private String autor;
	
	private Disciplina disciplina;
	
	private List<DescritorDTO> descritores;
	
	private List<RecursoDTO> recursos;
	
	private Integer ano;
	
	private String plataforma;
	
	private Boolean ehPublico;
	
	public List<RecursoDTO> getRecursos() {
		return recursos;
	}

	public void setRecursos(List<RecursoDTO> recursos) {
		this.recursos = recursos;
	}

	public Integer getAno() {
		return ano;
	}

	public void setAno(Integer ano) {
		this.ano = ano;
	}

	public String getPlataforma() {
		return plataforma;
	}

	public void setPlataforma(String plataforma) {
		this.plataforma = plataforma;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public List<DescritorDTO> getDescritores() {
		return descritores;
	}

	public void setDescritores(List<DescritorDTO> descritores) {
		this.descritores = descritores;
	}

	public Boolean getEhPublico() {
		return ehPublico;
	}

	public void setEhPublico(Boolean ehPublico) {
		this.ehPublico = ehPublico;
	}
	
}
