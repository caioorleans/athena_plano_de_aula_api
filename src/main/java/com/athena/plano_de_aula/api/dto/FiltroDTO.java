package com.athena.plano_de_aula.api.dto;

import com.athena.plano_de_aula.api.model.Plataforma;

public class FiltroDTO {
	private Integer ano;
	private Integer disciplinaId;
	private String descritorId;
	private Plataforma plataforma;
	
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
	public Plataforma getPlataforma() {
		return plataforma;
	}
	public void setPlataforma(Plataforma plataforma) {
		this.plataforma = plataforma;
	}
	
	
}
