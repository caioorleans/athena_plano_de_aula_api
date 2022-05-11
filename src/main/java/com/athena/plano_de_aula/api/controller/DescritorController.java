package com.athena.plano_de_aula.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.athena.plano_de_aula.api.dto.DescritorDTO;
import com.athena.plano_de_aula.api.service.DescritorService;

@RestController
@RequestMapping("/descritores")
public class DescritorController {
	
	@Autowired
	private DescritorService service;
	
	@GetMapping
	public List<DescritorDTO> findByDisciplina(Integer id){
		return service.findByDisciplina(id);
	}

}
