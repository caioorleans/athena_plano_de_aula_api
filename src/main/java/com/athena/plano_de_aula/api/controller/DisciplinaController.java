package com.athena.plano_de_aula.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.athena.plano_de_aula.api.model.Disciplina;
import com.athena.plano_de_aula.api.repository.DisciplinaRepository;

@RestController
@RequestMapping("/disciplinas")
public class DisciplinaController {
	
	@Autowired
	private DisciplinaRepository repository;
	
	@GetMapping
	public List<Disciplina> findAll(){
		return repository.findAll();
	}
	
}
