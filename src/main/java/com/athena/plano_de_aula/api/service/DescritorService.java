package com.athena.plano_de_aula.api.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.athena.plano_de_aula.api.dto.DescritorDTO;
import com.athena.plano_de_aula.api.model.Descritor;
import com.athena.plano_de_aula.api.repository.DescritorRepository;

@Service
public class DescritorService {
	
	@Autowired
	private DescritorRepository repository;
	
	public List<DescritorDTO> findByDisciplina(Integer id) {
		
		List<Descritor> descritores = repository.findByDisciplina(id);
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
