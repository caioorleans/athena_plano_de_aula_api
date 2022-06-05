package com.athena.plano_de_aula.api.dto;

import java.util.ArrayList;
import java.util.List;

import com.athena.plano_de_aula.api.model.Descritor;
import com.athena.plano_de_aula.api.model.Disciplina;
import com.athena.plano_de_aula.api.model.PlanoDeAula;

public class PlanoDeAulaDTO {
	
	private Integer id;
	
	private String titulo;
	
	private String conteudo;
	
	private String autor;
	
	private Disciplina disciplina;
	
	private List<DescritorDTO> descritores;
	
	private List<Integer> recursos;
	
	private Integer ano;
	
	private String plataforma;
	
	private Boolean ehPublico;
	
	
	
	public PlanoDeAulaDTO() {
		super();
	}

	public PlanoDeAulaDTO(PlanoDeAula plano) {
		this.setId(plano.getId());
		this.setTitulo(plano.getTitulo());
		this.setConteudo(plano.getConteudo());
		this.setAutor(plano.getAutor());
		this.setEhPublico(plano.getEhPublico());
		this.setDisciplina(plano.getDisciplina());
		this.setAno(plano.getAno());
		this.setPlataforma(plano.getPlataforma());
		
		List<DescritorDTO> descritores = new ArrayList<DescritorDTO>();
		for(Descritor d : plano.getDescritores()) {
			DescritorDTO descritor = new DescritorDTO();
			descritor.setId(d.getId());
			descritor.setDescricao(d.getDescricao());
			
			descritores.add(descritor);
		}
		this.setDescritores(descritores);
		
		this.setRecursos(plano.getRecursos());
	}
	
	public List<Integer> getRecursos() {
		return recursos;
	}

	public void setRecursos(List<Integer> recursos) {
		this.recursos = recursos;
	}

	public Integer getAno() {
		return ano;
	}

	public void setAno(Integer ano) {
		this.ano = ano;
	}

	public String getPlataforma() {
		return plataforma;
	}

	public void setPlataforma(String plataforma) {
		this.plataforma = plataforma;
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

	public String getConteudo() {
		return conteudo;
	}

	public void setConteudo(String conteudo) {
		this.conteudo = conteudo;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public Disciplina getDisciplina() {
		return disciplina;
	}

	public void setDisciplina(Disciplina disciplina) {
		this.disciplina = disciplina;
	}

	public List<DescritorDTO> getDescritores() {
		return descritores;
	}

	public void setDescritores(List<DescritorDTO> descritores) {
		this.descritores = descritores;
	}

	public Boolean getEhPublico() {
		return ehPublico;
	}

	public void setEhPublico(Boolean ehPublico) {
		this.ehPublico = ehPublico;
	}
	
}
