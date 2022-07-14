package com.athena.plano_de_aula.api.dto;

import javax.validation.constraints.NotNull;

import com.athena.plano_de_aula.api.model.Plataforma;

public class RecursoFormulario {
	
	@NotNull
	private Integer id;
	
	@NotNull
	private Plataforma plataforma;
	
	@NotNull
	private String titulo;
	
	@NotNull
	private Integer disciplinaId;
	
	public Integer getDisciplinaId() {
		return disciplinaId;
	}
	public void setDisciplinaId(Integer disciplinaId) {
		this.disciplinaId = disciplinaId;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Plataforma getPlataforma() {
		return plataforma;
	}
	public void setPlataforma(Plataforma plataforma) {
		this.plataforma = plataforma;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
}
