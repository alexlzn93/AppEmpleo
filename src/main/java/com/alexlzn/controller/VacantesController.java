package com.alexlzn.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.alexlzn.interfaces.IVacantesService;
import com.alexlzn.model.Vacante;
import com.alexlzn.util.ListaVacantes;

@Controller
@RequestMapping("/vacantes")
public class VacantesController {
	
	@Autowired
	IVacantesService vacanteService;
	
	@GetMapping("/")
	public String listVacantes(Model model) {
		//LISTA EN UNA TABLE TODAS LAS VACANTES DE LA LISTA
		model.addAttribute("list_vacantes", vacanteService.listVacantes());
		return "vacantes/listVacantes";
	}
	@GetMapping("/create")
	public String nuevaVacante(Model model,@ModelAttribute Vacante vacante) {
		//FORMULARIO PARA CREAR UNA VACANTE NUEVA
		vacanteService.guardar(vacante);
		return "vacantes/formVacante";
	}
	@PostMapping("/save")
	public String saveVacante(Model model,@ModelAttribute Vacante vacante) {
		//GUARDA E LA BBDD UNA NUEVA OFERTA
		vacanteService.guardar(vacante);
		return "home/index";
	}
	
	@GetMapping("/delete")
	public String delete(@RequestParam("id") int idVacante,Model model) {
		model.addAttribute("id", idVacante);
		vacanteService.delete(idVacante);
		return "mensajeEliminado";
	}
	
	@GetMapping("/editar")
	public String editar(@RequestParam("id") int idVacante,Model model) {
		model.addAttribute("id", idVacante);
		vacanteService.buscarPorId(idVacante);
		return "redirect:/vacantes/formVacante";
	}

	@GetMapping("/verDetalle/{id}")
	public String verDetalle(Model model,@PathVariable("id") int idVacante) {
		model.addAttribute("id", idVacante);
		Vacante vacante=vacanteService.findPorId(idVacante); //me devuelve el objeto de una lista estatica
		model.addAttribute("vacante", vacante);
		System.out.println(idVacante);
		return "vacantes/detalle";
	}
	
	
}
