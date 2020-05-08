package com.alexlzn.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alexlzn.interfaces.IPerfilService;

@Controller
@RequestMapping("/perfil")
public class PerfilController {

	@Autowired
	IPerfilService perfilService;
	
	@GetMapping("/index")
	public String addPerfiles() {
		perfilService.crearPerfiles();
		
		return "perfiles/ok";
	}
}
