package com.athena.plano_de_aula.api.service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.athena.plano_de_aula.api.dto.DescritorDTO;
import com.athena.plano_de_aula.api.dto.FiltroBuscaDTO;
import com.athena.plano_de_aula.api.dto.PlanoDeAulaDTO;
import com.athena.plano_de_aula.api.model.Descritor;
import com.athena.plano_de_aula.api.model.PlanoDeAula;
import com.athena.plano_de_aula.api.repository.PlanoDeAulaRepository;
import com.athena.plano_de_aula.api.specification.PlanoSpecificationsBuilder;
import com.athena.plano_de_aula.api.specification.SearchCriteria;

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
		plano.setPlataforma(novoPlano.getPlataforma().toUpperCase());
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
	
	public List<PlanoDeAulaDTO> findByFiltro(String search){
		
		List<PlanoDeAulaDTO> planosDTO = new ArrayList<PlanoDeAulaDTO>();
		
		PlanoSpecificationsBuilder builder = new PlanoSpecificationsBuilder();
		
		Pattern pattern = Pattern.compile("(\\w+?)(:|<|>)(\\w+?),", Pattern.UNICODE_CHARACTER_CLASS);
        Matcher matcher = pattern.matcher(search + ",");
        while (matcher.find()) {
            builder.with(matcher.group(1), matcher.group(2), matcher.group(3));
        }
		
		Specification<PlanoDeAula> spec = builder.build();
		
		List<PlanoDeAula> planos = repository.findAll(spec);
		
		for(PlanoDeAula p : planos) {
			planosDTO.add(new PlanoDeAulaDTO(p));
		}
		
		return planosDTO;
	}
	
	public List<PlanoDeAulaDTO> findByRecurso(String plataforma, Integer recurso, Pageable pageable){
		
		List<PlanoDeAulaDTO> planosDTO = new ArrayList<PlanoDeAulaDTO>();
		List<PlanoDeAula> planos = repository.findByRecursosAndPlataforma(recurso, plataforma.toUpperCase(), pageable);
		
		for(PlanoDeAula p : planos) {
			planosDTO.add(new PlanoDeAulaDTO(p));
		}
		
		return planosDTO;
	}
}
