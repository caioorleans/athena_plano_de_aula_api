package com.athena.plano_de_aula.api.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table
@NoArgsConstructor @AllArgsConstructor @Getter @Setter
public class PlanoDeAula {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(length = 500, nullable = false)
	private String titulo;
	
	@Column(length = 5000, nullable = false)
	private String conteudo;
	
	@Column(length = 100, nullable = false)
	private String autor;
	
	@Enumerated(EnumType.STRING)
	private Plataforma plataforma;
	
	@Column(nullable = false)
	private Integer ano;
	
	@Column(nullable = false)
	private Boolean publico;
	
	@ManyToOne
	private Disciplina disciplina;
	
	@ManyToMany
	@JoinTable(name="plano_possui_descritores",joinColumns={@JoinColumn(name="id_plano")}, inverseJoinColumns={@JoinColumn(name="id_descritor")})
	private List<Descritor> descritores;
	
	@ManyToMany
	@JoinTable(name="plano_possui_recursos",joinColumns={@JoinColumn(name="id_plano")}, inverseJoinColumns={@JoinColumn(name="id_red"), @JoinColumn(name="plataforma_red")})
	private List<Recurso> recursos;
	
}
