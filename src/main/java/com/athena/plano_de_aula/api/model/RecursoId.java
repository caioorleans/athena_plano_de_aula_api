package com.athena.plano_de_aula.api.model;
import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

@Embeddable
public class RecursoId implements Serializable{
	
	@NotNull
	private Integer recursoId;
	@NotNull
	private Plataforma recursoPlataforma;
	
	public RecursoId() {
	}
	
	public RecursoId(Integer recursoId, Plataforma recursoPlataforma) {
		super();
		this.recursoId = recursoId;
		this.recursoPlataforma = recursoPlataforma;
	}

	public Integer getRecursoId() {
		return recursoId;
	}
	public void setRecursoId(Integer recursoId) {
		this.recursoId = recursoId;
	}
	public Plataforma getRecursoPlataforma() {
		return recursoPlataforma;
	}
	public void setRecursoPlataforma(Plataforma recursoPlataforma) {
		this.recursoPlataforma = recursoPlataforma;
	}
	
	
	
}
