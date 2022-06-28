package com.athena.plano_de_aula.api.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.athena.plano_de_aula.api.dto.FiltroDTO;
import com.athena.plano_de_aula.api.dto.PlanoDeAulaDTO;
import com.athena.plano_de_aula.api.dto.PlanoFormulario;
import com.athena.plano_de_aula.api.exceptionhandler.ProductNotFoundException;
import com.athena.plano_de_aula.api.model.Descritor;
import com.athena.plano_de_aula.api.model.Disciplina;
import com.athena.plano_de_aula.api.model.PlanoDeAula;
import com.athena.plano_de_aula.api.model.Recurso;
import com.athena.plano_de_aula.api.model.RecursoId;
import com.athena.plano_de_aula.api.repository.PlanoDeAulaRepository;
import com.athena.plano_de_aula.api.specification.PlanoSpecificationsBuilder;
import com.athena.plano_de_aula.api.specification.SearchCriteria;

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
		plano.setPublico(false);
		
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
		plano.setPublico(false);
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
	
	public List<PlanoDeAula> findByRecursos(String titulo){
		List<Recurso> recursos = recursoService.findByTitulo(titulo);
		if(recursos == null || recursos.isEmpty()) {
			return new ArrayList<PlanoDeAula>();
		}
		else {
			return repository.findByRecursosIn(recursos);
		}
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
	 */
	
	public void updatePublico(Integer id) {
		PlanoDeAula plano = findById(id);
		
		plano.setPublico(plano.getPublico() == true ? false : true);
		
		repository.save(plano);
	}
	  
	public List<PlanoDeAula> findByFiltro(FiltroDTO filtro){
		if(filtro.getDescritorId()!= null) {
			Descritor d = descritorService.findById(filtro.getDescritorId());
			if(filtro.getAno() != null && filtro.getPlataforma() != null) {
				return repository.findByDescritoresAndAnoAndPlataformaLikeAndPublico(d,filtro.getAno(),filtro.getPlataforma(),true);
			}
			else {
				if(filtro.getAno() != null) {
					return repository.findByDescritoresAndAnoAndPublico(d, filtro.getAno(), true);
				}
				if(filtro.getPlataforma() != null) {
					return repository.findByDescritoresAndPlataformaLikeAndPublico(d, filtro.getPlataforma(), true);
				}
				return repository.findByDescritoresAndPublico(d, true);
			}
			
		}
		else {
			PlanoSpecificationsBuilder builder = new PlanoSpecificationsBuilder();

			List<SearchCriteria> criterios = buildCriteria(filtro);

			for(SearchCriteria sc : criterios) {
				builder.with(sc.getKey(),sc.getOperation(),sc.getValue()); 
			}

			Specification<PlanoDeAula> spec = builder.build();

			List<PlanoDeAula> planos = repository.findAll(spec);

			return planos; 
		}
		
	}
	  
	private List<SearchCriteria> buildCriteria(FiltroDTO filtro){
		List<SearchCriteria> criterios = new ArrayList<SearchCriteria>();

		if(filtro.getAno()!=null) { 
			SearchCriteria sc = new SearchCriteria("ano",":",filtro.getAno()); 
			criterios.add(sc); 
		} 
		if(filtro.getPlataforma()!=null) {
			SearchCriteria sc = new SearchCriteria("plataforma",":",filtro.getPlataforma().toUpperCase());
			criterios.add(sc); 
		}
		if(filtro.getDisciplinaId()!=null) { 
			Disciplina d = disciplinaService.findById(filtro.getDisciplinaId()); 
			SearchCriteria sc = new SearchCriteria("disciplina", ":", d); 
			criterios.add(sc); 
		}
		if(filtro.getDescritorId()!=null) { 
			Descritor d = descritorService.findById(filtro.getDescritorId());
			Collection descritores = new ArrayList();
			descritores.add(d);
			SearchCriteria sc = new SearchCriteria("descritores","in",descritores); 
			criterios.add(sc); 
		}

		return criterios; 
	}

	/*
	 * public List<PlanoDeAulaDTO> findByRecurso(String plataforma, Integer recurso,
	 * Pageable pageable) {
	 * 
	 * List<PlanoDeAulaDTO> planosDTO = new ArrayList<PlanoDeAulaDTO>();
	 * List<PlanoDeAula> planos =
	 * repository.findByRecursosAndPlataforma(recurso,nplataforma.toUpperCase(),
	 * pageable);
	 * 
	 * for (PlanoDeAula p : planos) { planosDTO.add(new PlanoDeAulaDTO(p)); }
	 * 
	 * return planosDTO; }
	 */
}
