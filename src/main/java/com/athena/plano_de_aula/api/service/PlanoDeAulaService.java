package com.athena.plano_de_aula.api.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.athena.plano_de_aula.api.dto.DescritorDTO;
import com.athena.plano_de_aula.api.dto.FiltroBuscaDTO;
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
		
		PlanoDeAulaDTO planoDTO = new PlanoDeAulaDTO(plano);
		
		return planoDTO;
	}
	
	public List<PlanoDeAulaDTO> findByFiltro(Pageable pageable, FiltroBuscaDTO filtro){
		
		List<PlanoDeAulaDTO> planosDTO = new ArrayList<PlanoDeAulaDTO>();
		
		List<PlanoDeAula> planos =  repository.findByTituloContains(filtro.getTitulo());
		
		for(PlanoDeAula p : planos) {
			planosDTO.add(new PlanoDeAulaDTO(p));
		}
		
		return planosDTO;
	}
}
