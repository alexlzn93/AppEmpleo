package com.alexlzn.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.alexlzn.interfaces.IVacantesService;
import com.alexlzn.model.Vacante;

@Controller
public class HomeController {
	@Autowired
	IVacantesService vacanteService;
	
	@GetMapping("/")
	public String goHome(Model model) {
		//PAGINA PRINCIPAL
		List<Vacante> listVacantes= vacanteService.findAllVacantes();
		model.addAttribute("vacantes", listVacantes);
		return "home/index"; //home es la carpeta donde estaran html del controlador HomeController
	}

	
}
