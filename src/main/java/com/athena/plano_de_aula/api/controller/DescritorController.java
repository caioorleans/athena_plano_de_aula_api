package com.athena.plano_de_aula.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.athena.plano_de_aula.api.model.Descritor;
import com.athena.plano_de_aula.api.model.Disciplina;
import com.athena.plano_de_aula.api.repository.DescritorRepository;

@RestController
@RequestMapping("/descritores")
public class DescritorController {
	
	@Autowired
	private DescritorRepository repository;
	
	@GetMapping
	public List<Descritor> findByDisciplina(Disciplina d){
		return repository.findByDisciplina(d.getId());
	}

}
