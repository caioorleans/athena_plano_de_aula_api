package com.athena.plano_de_aula.api.dto;

public class FiltroDTO {
	private String titulo;
	private Integer ano;
	private Integer disciplinaId;
	private String descritorId;
	private String plataforma;
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public Integer getAno() {
		return ano;
	}
	public void setAno(Integer ano) {
		this.ano = ano;
	}
	public Integer getDisciplinaId() {
		return disciplinaId;
	}
	public void setDisciplinaId(Integer disciplinaId) {
		this.disciplinaId = disciplinaId;
	}
	public String getDescritorId() {
		return descritorId;
	}
	public void setDescritorId(String descritorId) {
		this.descritorId = descritorId;
	}
	public String getPlataforma() {
		return plataforma;
	}
	public void setPlataforma(String plataforma) {
		this.plataforma = plataforma;
	}
	
	
}
