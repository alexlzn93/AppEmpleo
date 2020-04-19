package com.alexlzn.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.alexlzn.util.ListaVacantes;

@Controller
@RequestMapping("/vacantes")
public class VacantesController {
	
	@GetMapping("/")
	public String listVacantes(Model model) {
		//LISTA EN UNA TABLE TODAS LAS VACANTES DE LA LISTA
		model.addAttribute("list_vacantes", ListaVacantes.getVacantes());
		return "vacantes/vacantes";
	}
	
	@GetMapping("/delete")
	public String delete(@RequestParam("id") int idVacante,Model model) {
		model.addAttribute("id", idVacante);
		return "mensajeEliminado";
	}

	@GetMapping("/verDetalle/{id}")
	public String verDetalle(Model model,@PathVariable("id") int idVacante) {
		model.addAttribute("id", idVacante);
		System.out.println(idVacante);
		return "vacantes/detalle";
	}
	
	
}
