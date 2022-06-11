package com.athena.plano_de_aula.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.athena.plano_de_aula.api.model.Disciplina;
import com.athena.plano_de_aula.api.service.DisciplinaService;

@RestController
@RequestMapping("/disciplinas")
public class DisciplinaController {
	
	@Autowired
	private DisciplinaService service;
	
	@PostMapping("salvar")
	public void save(@Valid @RequestBody Disciplina disciplina) {
		service.save(disciplina);
	}
	
	@GetMapping()
	public List<Disciplina> findAll(){
		return service.findAll();
	}
	
	@GetMapping("/buscarPorDescricao/{descricao}")
	public Disciplina getByDescricao(@PathVariable("descricao") String descricao) {
		return service.findByDescricao(descricao);
	}
	
	@GetMapping("/buscarPorId/{id}")
	public Disciplina getById(@PathVariable("id") Integer id) {
		return service.findById(id);
	}
	
	@PutMapping("/atualizar")
	public void update(@Valid @RequestBody Disciplina d) {
		service.update(d);
	}
	
	@DeleteMapping("/delete/{id}")
	public void delete(@PathVariable("id") Integer id) {
		service.delete(id);
	}
	
}
