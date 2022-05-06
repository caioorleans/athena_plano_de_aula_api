package com.athena.plano_de_aula.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.athena.plano_de_aula.api.dto.NovoPlanoDeAula;
import com.athena.plano_de_aula.api.repository.PlanoDeAulaRepository;

@RestController
@RequestMapping("/planosDeAula")
public class PlanoDeAulaController {

	@Autowired
	private PlanoDeAulaRepository repository;
	
	@PostMapping
	public void save(@RequestBody NovoPlanoDeAula novoPlano) {
		
	}
	
}
