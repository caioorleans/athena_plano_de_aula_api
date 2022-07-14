package com.athena.plano_de_aula.api.dto;

import javax.validation.constraints.NotNull;

public class DescritorFormulario {
	
	@NotNull
	private String id;
	
	@NotNull
	private String descricao;
	
	@NotNull
	private Integer disciplinaId;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public Integer getDisciplinaId() {
		return disciplinaId;
	}
	public void setDisciplinaId(Integer disciplinaId) {
		this.disciplinaId = disciplinaId;
	}
	
	
}
