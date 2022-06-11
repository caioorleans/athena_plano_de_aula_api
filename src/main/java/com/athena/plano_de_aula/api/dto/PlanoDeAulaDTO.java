package com.athena.plano_de_aula.api.dto;

import java.util.List;

import com.athena.plano_de_aula.api.model.Disciplina;
import com.athena.plano_de_aula.api.model.Recurso;

public class PlanoDeAulaDTO {
	
	private Integer id;
	private String titulo;
	private String conteudo;
	private String autor;
	private Integer ano;
	private Disciplina disciplina;
	private List<DescritorDTO> descritores;
	private List<RecursoDTO> recursos;
	
	public PlanoDeAulaDTO() {}

	public PlanoDeAulaDTO(Integer id, String titulo, String conteudo, String autor, Integer ano, Disciplina disciplina,
			List<DescritorDTO> descritores, List<RecursoDTO> recursos) {
		super();
		this.id = id;
		this.titulo = titulo;
		this.conteudo = conteudo;
		this.autor = autor;
		this.ano = ano;
		this.disciplina = disciplina;
		this.descritores = descritores;
		this.recursos = recursos;
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

	public Integer getAno() {
		return ano;
	}

	public void setAno(Integer ano) {
		this.ano = ano;
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

	public List<RecursoDTO> getRecursos() {
		return recursos;
	}

	public void setRecursos(List<RecursoDTO> recursos) {
		this.recursos = recursos;
	}
	
	
}
