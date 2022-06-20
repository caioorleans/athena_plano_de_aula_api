package com.athena.plano_de_aula.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.athena.plano_de_aula.api.model.Admin;
import com.athena.plano_de_aula.api.repository.AdminRepository;

public class AdminService {
	@Autowired
	private AdminRepository repository;
	
	@Autowired
	private PasswordEncoder encoder;
	
	public void save(Admin admin) {
		String senha = admin.getSenha();
		
		admin.setSenha(encoder.encode(senha));
		
		repository.save(admin);
	}
}
