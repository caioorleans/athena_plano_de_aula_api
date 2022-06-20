package com.athena.plano_de_aula.api.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.athena.plano_de_aula.api.dto.Login;
import com.athena.plano_de_aula.api.dto.Sessao;
import com.athena.plano_de_aula.api.model.Admin;
import com.athena.plano_de_aula.api.repository.AdminRepository;
import com.athena.plano_de_aula.api.security.JWTCreator;
import com.athena.plano_de_aula.api.security.JWTObject;
import com.athena.plano_de_aula.api.security.SecurityConfig;

@RestController
public class LoginController {
	@Autowired
	private PasswordEncoder encoder;
	@Autowired
	private SecurityConfig securityConfig;
	@Autowired
	private AdminRepository repository;
	
	@PostMapping("/login")
	public Sessao logar(@RequestBody Login login) {
		Admin admin = repository.findById(login.getUsername()).get();
		if(admin!=null) {
			boolean passwordOk = encoder.matches(login.getPassword(), admin.getSenha());
			if(!passwordOk) {
				throw new RuntimeException("Senha inválida para o login: " + login.getUsername());
			}
			Sessao sessao = new Sessao();
			sessao.setLogin(admin.getUsuario());
			
			JWTObject jwtObject = new JWTObject();
			jwtObject.setIssuedAt(new Date(System.currentTimeMillis()));
			jwtObject.setExpiration((new Date(System.currentTimeMillis() + SecurityConfig.EXPIRATION)));
			List<String> cargos = new ArrayList<String>();
			cargos.add("ADMIN");
			jwtObject.setRoles(cargos);
			sessao.setToken(JWTCreator.create(SecurityConfig.PREFIX, SecurityConfig.KEY, jwtObject));
			return sessao;
		}
		else {
			throw new RuntimeException("Erro ao tentar fazer login");
		}
	}
}
