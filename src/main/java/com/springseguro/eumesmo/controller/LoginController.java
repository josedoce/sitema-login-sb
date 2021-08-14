package com.springseguro.eumesmo.controller;


import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import com.springseguro.eumesmo.entity.Role;
import com.springseguro.eumesmo.entity.User;
import com.springseguro.eumesmo.repository.UserRepository;

@Controller
public class LoginController {
	UserRepository userRepository;
	
	@Autowired
	public LoginController(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	@GetMapping("/")
	public String inicio(ModelMap model, Principal principal) {
		String name = principal.getName();
		User user = userRepository.findByNome(name);
		
		List<String> roles = new ArrayList<>(user.getRoles().size());
		for(Role role : user.getRoles()) {
			roles.add(role.getRole());
		}
		
		model.addAttribute("usuario",user);
		model.addAttribute("regras",roles);
		return "inicio";
	}
	
	@GetMapping("/entrar")
	public String entrar() {
		return "entrar";
	}
	
	@GetMapping("/projetos")
	public String projetos() {
		return "projetos";
	}
	
	@GetMapping("/relatorio-custos")
	public String relatorioCustos() {
		return "relatorio-custos";
	}
	
	@GetMapping("/relatorio-equipe")
	public String relatorioEquipe() {
		return "relatorio-equipe";
	}
	
}
