package com.athena.plano_de_aula.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.athena.plano_de_aula.api.model.Admin;

public interface AdminRepository  extends JpaRepository<Admin, String>{
	
}
