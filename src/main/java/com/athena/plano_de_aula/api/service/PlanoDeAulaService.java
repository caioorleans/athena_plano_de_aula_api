package com.athena.plano_de_aula.api.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.athena.plano_de_aula.api.dto.PlanoFormulario;
import com.athena.plano_de_aula.api.exceptionhandler.ProductNotFoundException;
import com.athena.plano_de_aula.api.model.Descritor;
import com.athena.plano_de_aula.api.model.Disciplina;
import com.athena.plano_de_aula.api.model.PlanoDeAula;
import com.athena.plano_de_aula.api.model.Recurso;
import com.athena.plano_de_aula.api.model.RecursoId;
import com.athena.plano_de_aula.api.repository.PlanoDeAulaRepository;

@Service
public class PlanoDeAulaService {

	@Autowired
	private PlanoDeAulaRepository repository;
	
	@Autowired
	private DisciplinaService disciplinaService;
	
	@Autowired
	private DescritorService descritorService;
	
	@Autowired
	private RecursoService recursoService;
	
	public void save(PlanoFormulario form) {
		Disciplina disciplina = disciplinaService.findById(form.getIdDisciplina());
		
		List<Descritor> descritores = new ArrayList<Descritor>();
		for(String descritorId : form.getIdDescritores()) {
			Descritor novoDescritor = descritorService.findById(descritorId);
			descritores.add(novoDescritor);
		}
		
		List<Recurso> recursos = new ArrayList<Recurso>();
		for(RecursoId recursoId : form.getIdRecursos()) {
			Recurso novoRecurso = recursoService.findById(recursoId);
			recursos.add(novoRecurso);
		}
		
		PlanoDeAula plano = new PlanoDeAula();
		plano.setTitulo(form.getTitulo());
		plano.setConteudo(form.getConteudo());
		plano.setAutor(form.getAutor());
		plano.setAno(form.getAno());
		plano.setDisciplina(disciplina);
		plano.setDescritores(descritores);
		plano.setRecursos(recursos);
		plano.setPlataforma(form.getPlataforma().toUpperCase());
		plano.setEhPublico(false);
		
		repository.save(plano);
	}
	
	public void update(PlanoFormulario form) {
		findById(form.getId());
		
		Disciplina disciplina = disciplinaService.findById(form.getIdDisciplina());
		
		List<Descritor> descritores = new ArrayList<Descritor>();
		for(String descritorId : form.getIdDescritores()) {
			Descritor novoDescritor = descritorService.findById(descritorId);
			descritores.add(novoDescritor);
		}
		
		List<Recurso> recursos = new ArrayList<Recurso>();
		for(RecursoId recursoId : form.getIdRecursos()) {
			Recurso novoRecurso = recursoService.findById(recursoId);
			recursos.add(novoRecurso);
		}
		
		PlanoDeAula plano = new PlanoDeAula();
		plano.setTitulo(form.getTitulo());
		plano.setConteudo(form.getConteudo());
		plano.setAutor(form.getAutor());
		plano.setAno(form.getAno());
		plano.setDisciplina(disciplina);
		plano.setDescritores(descritores);
		plano.setRecursos(recursos);
		plano.setPlataforma(form.getPlataforma().toUpperCase());
		plano.setEhPublico(false);
		plano.setId(form.getId());
		
		repository.save(plano);
		
	}
	
	public PlanoDeAula findById(Integer id) {
		return repository.findById(id).orElseThrow(()-> new ProductNotFoundException());
	}
	
	public List<PlanoDeAula> findAll(){
		return repository.findAll();
	}
	
	public void delete(Integer id) {
		PlanoDeAula plano = findById(id);
		repository.delete(plano);
	}

	/*
	 * public List<PlanoDeAulaDTO> findByFiltro(String search) {
	 * 
	 * List<PlanoDeAulaDTO> planosDTO = new ArrayList<PlanoDeAulaDTO>();
	 * 
	 * PlanoSpecificationsBuilder builder = new PlanoSpecificationsBuilder();
	 * 
	 * Pattern pattern = Pattern.compile("(\\w+?)(:|<|>)(\\w+?),",
	 * Pattern.UNICODE_CHARACTER_CLASS); Matcher matcher = pattern.matcher(search +
	 * ","); while (matcher.find()) { builder.with(matcher.group(1),
	 * matcher.group(2), matcher.group(3)); }
	 * 
	 * Specification<PlanoDeAula> spec = builder.build();
	 * 
	 * List<PlanoDeAula> planos = repository.findAll(spec);
	 * 
	 * for (PlanoDeAula p : planos) { planosDTO.add(new PlanoDeAulaDTO(p)); }
	 * 
	 * return planosDTO; }
	 * 
	 * public List<PlanoDeAulaDTO> findByFiltro(FiltroDTO filtro){
	 * 
	 * List<PlanoDeAulaDTO> planosDTO = new ArrayList<PlanoDeAulaDTO>();
	 * 
	 * PlanoSpecificationsBuilder builder = new PlanoSpecificationsBuilder();
	 * 
	 * List<SearchCriteria> criterios = buildCriteria(filtro);
	 * 
	 * for(SearchCriteria sc : criterios) {
	 * builder.with(sc.getKey(),sc.getOperation(),sc.getValue()); }
	 * 
	 * Specification<PlanoDeAula> spec = builder.build();
	 * 
	 * List<PlanoDeAula> planos = repository.findAll(spec);
	 * 
	 * for(PlanoDeAula p : planos) { planosDTO.add(new PlanoDeAulaDTO(p)); }
	 * 
	 * return planosDTO; }
	 * 
	 * private List<SearchCriteria> buildCriteria(FiltroDTO filtro){
	 * List<SearchCriteria> criterios = new ArrayList<SearchCriteria>();
	 * 
	 * if(filtro.getTitulo()!=null) { SearchCriteria sc = new
	 * SearchCriteria("titulo",":", filtro.getTitulo()); criterios.add(sc); }
	 * if(filtro.getAno()!=null) { SearchCriteria sc = new SearchCriteria("ano",":",
	 * filtro.getAno()); criterios.add(sc); } if(filtro.getPlataforma()!=null) {
	 * SearchCriteria sc = new SearchCriteria("plataforma",":",
	 * filtro.getPlataforma().toUpperCase()); criterios.add(sc); }
	 * if(filtro.getDisciplinaId()!=null) { Disciplina d =
	 * disciplinaRepository.findById(filtro.getDisciplinaId()).get(); SearchCriteria
	 * sc = new SearchCriteria("disciplina", ":", d); criterios.add(sc); }
	 * //if(filtro.getDescritorId()!=null) { //Descritor d =
	 * descritorRepository.findById(filtro.getDescritorId()).get(); //SearchCriteria
	 * sc = new SearchCriteria("descritores",":",d); //criterios.add(sc); //}
	 * 
	 * return criterios; }
	 * 
	 * //public List<PlanoDeAulaDTO> findByRecurso(String plataforma, Integer
	 * recurso, Pageable pageable) {
	 * 
	 * //List<PlanoDeAulaDTO> planosDTO = new ArrayList<PlanoDeAulaDTO>();
	 * //List<PlanoDeAula> planos = repository.findByRecursosAndPlataforma(recurso,
	 * plataforma.toUpperCase(), pageable);
	 * 
	 * //for (PlanoDeAula p : planos) { // planosDTO.add(new PlanoDeAulaDTO(p)); //}
	 * 
	 * //return planosDTO; //}
	 */}
