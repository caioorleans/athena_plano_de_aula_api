package com.athena.plano_de_aula.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.athena.plano_de_aula.api.dto.FiltroDTO;
import com.athena.plano_de_aula.api.dto.PlanoDeAulaDTO;
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
	
	@GetMapping("/buscaPorFiltro")
	public List<PlanoDeAulaDTO> findByFilter(@RequestParam(value = "search") String search){
		return service.findByFiltro(search);
	}
	
	@GetMapping("/buscaPorFiltro2")
	public List<PlanoDeAulaDTO> findByFilter(FiltroDTO filtro){
		return service.findByFiltro(filtro);
	}
	
	@GetMapping("/buscaPorRecurso")
	public List<PlanoDeAulaDTO> findByRecurso(Integer recurso, String plataforma, Pageable pageable){
		return service.findByRecurso(plataforma, recurso, pageable);
	}
}
