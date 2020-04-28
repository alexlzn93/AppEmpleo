package com.alexlzn.controller;


import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
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
	
	@GetMapping("/index")
	public String index(Model model) {
		//LISTA EN UNA TABLE TODAS LAS VACANTES DE LA LISTA
		model.addAttribute("list_vacantes", vacanteService.listVacantes());
		return "vacantes/listVacantes";
	}
	@GetMapping("/create")
	public String nuevaVacante( Vacante vacante) { //th:object="${vacante}" DATA BINDING
		//FORMULARIO PARA CREAR UNA VACANTE NUEVA
		
		return "vacantes/formVacante";
	}
	@PostMapping("/save")
	public String saveVacante(Vacante vacante,BindingResult result) { //DATA BINDING
		//GUARDA E LA BBDD UNA NUEVA OFERTA
		//vacanteService.guardar(vacante); BBDD
		if(result.hasErrors()) {
			System.out.println("Ocurrio un error al introducir los datos del formulario");
			return "vacantes/formVacante"; //si hay errores en el formulario devuelvo la vista 
		}
		vacanteService.guardarEnListaVacantes(vacante);//no base de datos
		System.out.println(vacante);
		return "vacantes/listVacantes";
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
	
	//CORREGIR EL ERROR DE STRING A TIPO DATE
	@InitBinder
	public void errorStringDate(WebDataBinder wdb) {
		SimpleDateFormat dateformat= new SimpleDateFormat("dd-MM-YYYY");
		wdb.registerCustomEditor(Date.class, new CustomDateEditor(dateformat, false));
	}
	
	
}
