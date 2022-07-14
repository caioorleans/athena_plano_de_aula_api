package com.athena.plano_de_aula.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.athena.plano_de_aula.api.exceptionhandler.ProductNotFoundException;
import com.athena.plano_de_aula.api.model.Disciplina;
import com.athena.plano_de_aula.api.repository.DisciplinaRepository;

@Service
public class DisciplinaService {
	@Autowired
	private DisciplinaRepository repository;
	
	public void save(Disciplina d) {
		repository.save(d);
	}
	
	public List<Disciplina> findAll(){
		return repository.findAll();
	}
	
	public Disciplina findById(Integer id) {
		return repository.findById(id).orElseThrow(()-> new ProductNotFoundException());
	}
	
	public Disciplina findByDescricao(String descricao) {
		return repository.findByDescricao(descricao).orElseThrow(()-> new ProductNotFoundException());
	}
	
	public void update(Disciplina d) {
		findById(d.getId());
		repository.save(d);
	}
	
	public void delete(Integer id) {
		//obs: tratar erro org.postgresql.util.PSQLException:
		Disciplina d = findById(id);
		repository.delete(d);
	}
}
