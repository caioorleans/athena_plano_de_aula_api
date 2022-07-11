package com.athena.plano_de_aula.api.dto;

import java.util.List;

import com.athena.plano_de_aula.api.model.Disciplina;
import com.athena.plano_de_aula.api.model.PlanoDeAula;

public class PlanoDTO {
	
	private Integer id;
	private String titulo;
	private String autor;
	private Integer ano;
	
	public PlanoDTO() {}

	public PlanoDTO(PlanoDeAula plano) {
		super();
		this.id = plano.getId();
		this.titulo = plano.getTitulo();
		this.autor = plano.getAutor();
		this.ano = plano.getAno();
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

}
