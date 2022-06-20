package com.athena.plano_de_aula.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.athena.plano_de_aula.api.model.Admin;
import com.athena.plano_de_aula.api.service.AdminService;

@RestController
@RequestMapping("/admins")
public class AdminController {
	@Autowired
	private AdminService service;
	
	@PostMapping("/save")
	public void save(@RequestBody Admin admin) {
		service.save(admin);
	}
}
