package com.alexlzn.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

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
	public String goHome(Model model) {
		//PAGINA PRINCIPAL
		List<Vacante> listVacantes= vacanteService.vacantesDestacadas(); //SOLO SE MUESTRAN LAS OFERTAS DESTACADAS Y APROBADAS
		model.addAttribute("categorias", categoriasService.findAllCategoria());//lista de categorias en el index
		model.addAttribute("vacantes", listVacantes);
		
		return "home/index"; 
	}
	
	@PostMapping("/searchVacante")
	public String busquedaVacantes(@ModelAttribute("buscarVacante")Vacante vacante, Model model) {
		System.out.println("buscado vacante" + vacante);
		
		
		return "home/index";
	}

	
	@ModelAttribute
	public void recursosComunes(Model model) {
		Vacante vacanteSearch = new Vacante();
		model.addAttribute("vacantes", vacanteService.vacantesDestacadas()); 
		model.addAttribute("categorias", categoriasService.findAllCategoria());
		model.addAttribute("buscarVacante", vacanteSearch);
	}
	@InitBinder
	public void errorStringDate(WebDataBinder wdb) {
		SimpleDateFormat dateformat= new SimpleDateFormat("dd-MM-YYYY");
		wdb.registerCustomEditor(Date.class, new CustomDateEditor(dateformat, false));
	}
	
}
