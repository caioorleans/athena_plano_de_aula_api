package com.athena.plano_de_aula.api.dto;

public class RecursoDTO {
	private Integer id;
	private String plataforma;
	private Integer disciplinaId;
	
	public RecursoDTO() {}

	public RecursoDTO(Integer id, String plataforma, Integer disciplinaId) {
		this.id = id;
		this.plataforma = plataforma;
		this.disciplinaId = disciplinaId;
	}

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

	public Integer getDisciplinaId() {
		return disciplinaId;
	}

	public void setDisciplinaId(Integer disciplinaId) {
		this.disciplinaId = disciplinaId;
	}
	
	
}
