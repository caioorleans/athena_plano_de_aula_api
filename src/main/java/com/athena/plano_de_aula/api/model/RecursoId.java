package com.athena.plano_de_aula.api.model;
import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@NoArgsConstructor @AllArgsConstructor @Getter @Setter
public class RecursoId implements Serializable{
	
	@NotNull
	private Integer recursoId;
	@NotNull
	private Plataforma recursoPlataforma;
	
}
