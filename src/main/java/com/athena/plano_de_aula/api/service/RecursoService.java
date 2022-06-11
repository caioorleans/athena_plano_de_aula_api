package com.athena.plano_de_aula.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.athena.plano_de_aula.api.dto.RecursoFormulario;
import com.athena.plano_de_aula.api.exceptionhandler.ProductNotFoundException;
import com.athena.plano_de_aula.api.model.Disciplina;
import com.athena.plano_de_aula.api.model.Recurso;
import com.athena.plano_de_aula.api.model.RecursoId;
import com.athena.plano_de_aula.api.repository.RecursoRepository;

@Service
public class RecursoService {
	
	@Autowired
	private RecursoRepository repository;
	
	@Autowired
	private DisciplinaService disciplinaService;
	
	public Recurso findById(RecursoId rId) {
		return repository.findById(rId).orElseThrow(()-> new ProductNotFoundException());
	}
	
	public void save(RecursoFormulario recurso) {
		
		Recurso novoRecurso = new Recurso();
		RecursoId rId = new RecursoId();
		
		rId.setRecursoId(recurso.getId());
		rId.setRecursoPlataforma(recurso.getPlataforma());
		
		novoRecurso.setId(rId);
		novoRecurso.setTitulo(recurso.getTitulo());
		
		Disciplina d = disciplinaService.findById(recurso.getDisciplinaId());
		
		novoRecurso.setDisciplina(d);
		
		repository.save(novoRecurso);
	}
	
	public void update(RecursoFormulario recurso) {
		findById(new RecursoId(recurso.getDisciplinaId(),recurso.getPlataforma()));
		save(recurso);
	}
	
	public void delete(RecursoId id) {
		Recurso r =findById(id);
		repository.delete(r);
	}
}
