package com.athena.plano_de_aula.api.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.regex.Matcher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.athena.plano_de_aula.api.dto.FiltroDTO;
import com.athena.plano_de_aula.api.dto.PlanoFormulario;
import com.athena.plano_de_aula.api.exceptionhandler.ProductNotFoundException;
import com.athena.plano_de_aula.api.model.Descritor;
import com.athena.plano_de_aula.api.model.Disciplina;
import com.athena.plano_de_aula.api.model.PlanoDeAula;
import com.athena.plano_de_aula.api.model.Plataforma;
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
		for (String descritorId : form.getIdDescritores()) {
			Descritor novoDescritor = descritorService.findById(descritorId);
			descritores.add(novoDescritor);
		}

		List<Recurso> recursos = new ArrayList<Recurso>();
		for (RecursoId recursoId : form.getIdRecursos()) {
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
		plano.setPlataforma(form.getPlataforma());
		plano.setPublico(false);

		repository.save(plano);
	}

	public void update(PlanoFormulario form) {
		findById(form.getId());

		Disciplina disciplina = disciplinaService.findById(form.getIdDisciplina());

		List<Descritor> descritores = new ArrayList<Descritor>();
		for (String descritorId : form.getIdDescritores()) {
			Descritor novoDescritor = descritorService.findById(descritorId);
			descritores.add(novoDescritor);
		}

		List<Recurso> recursos = new ArrayList<Recurso>();
		for (RecursoId recursoId : form.getIdRecursos()) {
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
		plano.setPlataforma(form.getPlataforma());
		plano.setPublico(false);
		plano.setId(form.getId());

		repository.save(plano);

	}

	public PlanoDeAula findById(Integer id) {
		return repository.findById(id).orElseThrow(() -> new ProductNotFoundException());
	}

	public List<PlanoDeAula> findAll() {
		return repository.findAll();
	}

	public void delete(Integer id) {
		PlanoDeAula plano = findById(id);
		repository.delete(plano);
	}

	public Page<PlanoDeAula> findPrivate(Integer pag) {
		Pageable pageable = PageRequest.of(pag, 8, Sort.by("id"));
		return repository.findByPublico(false, pageable);
	}

	public Page<PlanoDeAula> findByRecursos(Integer pag, String titulo) {
		List<Recurso> recursos = recursoService.findByTitulo(titulo);
		Pageable pageable = PageRequest.of(pag, 8, Sort.by("id"));
		if (recursos == null || recursos.isEmpty()) {
			return new PageImpl<>(new ArrayList<PlanoDeAula>());
		} else {
			return repository.findByRecursosInAndPublico(recursos, true, pageable);
		}
	}

	public void updatePublico(Integer id) {
		PlanoDeAula plano = findById(id);

		plano.setPublico(plano.getPublico() == true ? false : true);

		repository.save(plano);
	}

	public Page<PlanoDeAula> findByRecurso(RecursoId rId, Integer pag) {
		Pageable pageable = PageRequest.of(pag, 8, Sort.by("id"));
		return repository.findByRecursos(recursoService.findById(rId), pageable);
	}

	public Page<PlanoDeAula> findByFiltro(Integer pag, Matcher matcher) {
		FiltroDTO filtro = new FiltroDTO();
		Pageable pageable = PageRequest.of(pag, 8, Sort.by("id"));
		
		List<SearchCriteria> criterios = new ArrayList<SearchCriteria>();
		
		while (matcher.find()) {
			SearchCriteria sc = new SearchCriteria(matcher.group(1),matcher.group(2),matcher.group(3));
			criterios.add(sc);
		}

		PlanoSpecificationsBuilder builder = new PlanoSpecificationsBuilder();

		for (SearchCriteria sc : criterios) {
			builder.with(sc.getKey(), sc.getOperation(), sc.getValue());
		}

		Specification<PlanoDeAula> spec = builder.build();

		Page<PlanoDeAula> planos = repository.findAll(spec, pageable);

		return planos;

	}

}
