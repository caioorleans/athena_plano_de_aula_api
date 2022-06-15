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

import com.athena.plano_de_aula.api.dto.RecursoFormulario;
import com.athena.plano_de_aula.api.model.Recurso;
import com.athena.plano_de_aula.api.model.RecursoId;
import com.athena.plano_de_aula.api.service.RecursoService;

@RestController
@RequestMapping("/recursos")
public class RecursoController {
	
	@Autowired
	private RecursoService service;
	
	@GetMapping
	public List<Recurso> findAll(){
		return service.findAll();
	}
	
	@GetMapping("/buscarPorId/{id}")
	public Recurso findById(@PathVariable RecursoId id) {
		return findById(id);
	}
	
	@PostMapping("/salvar")
	public void save(@Valid @RequestBody RecursoFormulario r) {
		service.save(r);
	}
	
	@PutMapping("/atualizar")
	public void update(@Valid @RequestBody RecursoFormulario r) {
		service.update(r);
	}
	
	@DeleteMapping("/apagar")
	public void delete(@Valid @RequestBody RecursoId id) {
		service.delete(id);
	}
}
