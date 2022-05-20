package com.athena.plano_de_aula.api.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.athena.plano_de_aula.api.dto.DescritorDTO;
import com.athena.plano_de_aula.api.dto.PlanoDeAulaDTO;
import com.athena.plano_de_aula.api.model.Descritor;
import com.athena.plano_de_aula.api.model.PlanoDeAula;
import com.athena.plano_de_aula.api.repository.PlanoDeAulaRepository;

@Service
public class PlanoDeAulaService {
	
	@Autowired
	private PlanoDeAulaRepository repository;
	
	public void save(PlanoDeAulaDTO novoPlano) {
		
		PlanoDeAula plano = new PlanoDeAula();
		
		plano.setAutor(novoPlano.getAutor());
		plano.setTitulo(novoPlano.getTitulo());
		plano.setConteudo(novoPlano.getConteudo());
		plano.setDisciplina(novoPlano.getDisciplina());
		plano.setPlataforma(novoPlano.getPlataforma());
		plano.setAno(novoPlano.getAno());
		
		plano.setEhPublico(false);
		
		List<Descritor> descritores =  new ArrayList<Descritor>();
		
		for(DescritorDTO d : novoPlano.getDescritores()) {
			Descritor descritor = new Descritor();
			descritor.setId(d.getId());
			
			descritores.add(descritor);
		}
		
		plano.setDescritores(descritores);
		
		plano.setRecursos(novoPlano.getRecursos());
		
		repository.save(plano);
		
	}
	
	public PlanoDeAulaDTO findById(Integer id) {
		PlanoDeAula plano = repository.findById(id).get();
		
		PlanoDeAulaDTO planoDTO = new PlanoDeAulaDTO();
		
		planoDTO.setId(plano.getId());
		planoDTO.setTitulo(plano.getTitulo());
		planoDTO.setConteudo(plano.getConteudo());
		planoDTO.setAutor(plano.getAutor());
		planoDTO.setEhPublico(plano.getEhPublico());
		planoDTO.setDisciplina(plano.getDisciplina());
		planoDTO.setAno(plano.getAno());
		planoDTO.setPlataforma(plano.getPlataforma());
		
		List<DescritorDTO> descritores = new ArrayList<DescritorDTO>();
		for(Descritor d : plano.getDescritores()) {
			DescritorDTO descritor = new DescritorDTO();
			descritor.setId(d.getId());
			descritor.setDescricao(d.getDescricao());
			
			descritores.add(descritor);
		}
		planoDTO.setDescritores(descritores);
		
		planoDTO.setRecursos(plano.getRecursos());
		
		return planoDTO;
	}
}
