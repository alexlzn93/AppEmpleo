package com.alexlzn.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.alexlzn.interfaces.ICategoriaService;
import com.alexlzn.interfaces.IVacantesService;
import com.alexlzn.model.Vacante;

@Controller
public class HomeController {
	@Autowired
	IVacantesService vacanteService;
	@Autowired
	ICategoriaService categoriasService;
	@GetMapping("/")
	public String goHome(Model model,Vacante vacante) {
		//PAGINA PRINCIPAL
		List<Vacante> listVacantes= vacanteService.findAllVacantes();
		model.addAttribute("categorias", categoriasService.findAllCategoria());//lista de categorias en el index
		model.addAttribute("vacantes", listVacantes);
		model.addAttribute("mensaje", "Seleccione una categoría");
		return "home/index"; //home es la carpeta donde estaran html del controlador HomeController
	}

	
}
