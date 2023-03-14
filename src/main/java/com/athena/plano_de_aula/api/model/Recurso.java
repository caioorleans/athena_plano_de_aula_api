package com.athena.plano_de_aula.api.model;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "red")
@NoArgsConstructor @AllArgsConstructor @Getter @Setter
public class Recurso{
	
	@EmbeddedId
	RecursoId id;
	
	private String titulo;
	
	@ManyToOne
	private Disciplina disciplina;
	
}
