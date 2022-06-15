package com.athena.plano_de_aula.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.athena.plano_de_aula.api.dto.PlanoFormulario;
import com.athena.plano_de_aula.api.model.PlanoDeAula;
import com.athena.plano_de_aula.api.service.PlanoDeAulaService;

@RestController
@RequestMapping("/planosDeAula")
public class PlanoDeAulaController {

	@Autowired
	private PlanoDeAulaService service;
	
	@GetMapping
	public List<PlanoDeAula> findAll(){
		return service.findAll();
	}
	
	@PostMapping("salvar")
	public void save(@Valid @RequestBody PlanoFormulario form) {
		service.save(form);
	}
	
	@PutMapping("atualizar")
	public void update(@Valid @RequestBody PlanoFormulario form) {
		service.update(form);
	}
	
	@DeleteMapping("apagar")
	public void update(Integer id) {
		service.delete(id);
	}
}
