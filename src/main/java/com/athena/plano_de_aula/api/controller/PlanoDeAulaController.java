package com.athena.plano_de_aula.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.athena.plano_de_aula.api.dto.PlanoDeAulaDTO;
import com.athena.plano_de_aula.api.model.PlanoDeAula;
import com.athena.plano_de_aula.api.repository.PlanoDeAulaRepository;
import com.athena.plano_de_aula.api.service.PlanoDeAulaService;

@RestController
@RequestMapping("/planosDeAula")
public class PlanoDeAulaController {

	@Autowired
	private PlanoDeAulaService service;
	
	@PostMapping
	public void save(@RequestBody PlanoDeAulaDTO novoPlano) {
		service.save(novoPlano);
	}
	
	@GetMapping(value = "/{id}")
	public PlanoDeAulaDTO findById(@PathVariable Integer id) {
		return service.findById(id);
	}
}
