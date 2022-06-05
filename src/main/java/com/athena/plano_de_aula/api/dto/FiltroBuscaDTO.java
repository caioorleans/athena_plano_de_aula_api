package com.athena.plano_de_aula.api.dto;

public class FiltroBuscaDTO {
	private String titulo;
	private Integer disciplina;
	private String plataforma;
	private Integer ano;
	private String descritor;
	
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public Integer getDisciplina() {
		return disciplina;
	}
	public void setDisciplina(Integer disciplina) {
		this.disciplina = disciplina;
	}
	public String getPlataforma() {
		return plataforma;
	}
	public void setPlataforma(String plataforma) {
		this.plataforma = plataforma;
	}
	public Integer getAno() {
		return ano;
	}
	public void setAno(Integer ano) {
		this.ano = ano;
	}
	public String getDescritor() {
		return descritor;
	}
	public void setDescritor(String descritor) {
		this.descritor = descritor;
	}
	
	
}
