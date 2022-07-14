package com.athena.plano_de_aula.api.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.athena.plano_de_aula.api.dto.DescritorDTO;
import com.athena.plano_de_aula.api.dto.DescritorFormulario;
import com.athena.plano_de_aula.api.exceptionhandler.ProductNotFoundException;
import com.athena.plano_de_aula.api.model.Descritor;
import com.athena.plano_de_aula.api.model.Disciplina;
import com.athena.plano_de_aula.api.repository.DescritorRepository;

@Service
public class DescritorService {
	
	@Autowired
	private DescritorRepository repository;
	
	@Autowired
	private DisciplinaService disciplinaService;
	
	public List<DescritorDTO> findAll(){
		return toDTO(repository.findAll());
	}
	
	public Descritor findById(String id) {
		return repository.findById(id.toUpperCase()).orElseThrow(()-> new ProductNotFoundException());
	}
	
	public List<DescritorDTO> findByDisciplina(Integer id) {
		
		List<Descritor> descritores = repository.findByDisciplina(id);
		List<DescritorDTO> descritoresDTO = toDTO(descritores);
		
		return descritoresDTO;
	}
	
	public void save(DescritorFormulario form) {
		Disciplina d = disciplinaService.findById(form.getDisciplinaId());
		Descritor novoDescritor = new Descritor(form.getId().toUpperCase(), form.getDescricao(), d);
		repository.save(novoDescritor);
	}
	
	public void update(DescritorFormulario form) {
		findById(form.getId());
		save(form);
	}
	
	public void delete(String id) {
		Descritor d = findById(id);
		repository.delete(d);
	}
	
	public List<DescritorDTO> toDTO(List<Descritor> descritores) {
		List<DescritorDTO> descritoresDTO = new ArrayList<DescritorDTO>();
		
		for(Descritor d : descritores) {
			DescritorDTO descritor = new DescritorDTO();
			descritor.setId(d.getId());
			descritor.setDescricao(d.getDescricao());
			
			descritoresDTO.add(descritor);
		}
		
		return descritoresDTO;
	}
}
