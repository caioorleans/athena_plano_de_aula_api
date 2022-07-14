package com.athena.plano_de_aula.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.athena.plano_de_aula.api.dto.DescritorDTO;
import com.athena.plano_de_aula.api.dto.DescritorFormulario;
import com.athena.plano_de_aula.api.service.DescritorService;

@RestController
@RequestMapping("/descritores")
public class DescritorController {
	
	@Autowired
	private DescritorService service;
	
	@PreAuthorize("permitAll")
	@GetMapping
	public List<DescritorDTO> findAll(){
		return service.findAll();
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@PostMapping("/salvar")
	public void save(@Valid @RequestBody DescritorFormulario form) {
		service.save(form);
	}
	
	@PreAuthorize("permitAll")
	@GetMapping("buscarPorDisciplina")
	public List<DescritorDTO> findByDisciplina(Integer id){
		return service.findByDisciplina(id);
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@PutMapping("/atualizar")
	public void update(@Valid @RequestBody DescritorFormulario form) {
		service.update(form);
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@DeleteMapping("/apagar/{id}")
	public void delete(@PathVariable String id) {
		service.delete(id);
	}

}
