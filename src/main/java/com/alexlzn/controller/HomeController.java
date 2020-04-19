package com.alexlzn.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.alexlzn.model.Vacante;

@Controller
public class HomeController {
	
	@GetMapping("/")
	public String goHome(Model model) {
		//PAGINA PRINCIPAL
		model.addAttribute("bienvenido", "Bienvenido a jobsAPP");
		return "home/index"; //home es la carpeta donde estaran html del controlador HomeController
	}

	
}
