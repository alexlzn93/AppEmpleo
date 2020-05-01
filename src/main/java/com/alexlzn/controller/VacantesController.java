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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.alexlzn.interfaces.ICategoriaService;
import com.alexlzn.interfaces.IVacantesService;
import com.alexlzn.model.Categoria;
import com.alexlzn.model.Vacante;


@Controller
@RequestMapping("/vacantes")
public class VacantesController {
	
	@Autowired
	IVacantesService vacanteService;
	@Autowired
	ICategoriaService categoriasService;
	
	@GetMapping("/index")
	public String index(Model model, Vacante vacante) {
		//LISTA EN UNA TABLE TODAS LAS VACANTES DE LA LISTA
		model.addAttribute("list_vacantes", vacanteService.findAllVacantes());
		//model.addAttribute("categorias", categoriasService.findAllCategoria());
		return "vacantes/listVacantes";
	}
	@GetMapping("/create")
	public String nuevaVacante(Vacante vacante,Model model) { //th:object="${vacante}" DATA BINDING
		//FORMULARIO PARA CREAR UNA VACANTE NUEVA
		//desplegar en el option la lista de las categorias
		model.addAttribute("categorias", categoriasService.findAllCategoria()); 
		return "vacantes/formVacante";
	}
	@PostMapping("/save")
	public String saveVacante(Vacante vacante,BindingResult result,RedirectAttributes attributes) { //DATA BINDING
		//GUARDA E LA BBDD UNA NUEVA OFERTA
		//vacanteService.guardar(vacante); BBDD
		if(result.hasErrors()) {
			System.out.println("Ocurrio un error al introducir los datos del formulario");
			return "vacantes/formVacante"; //si hay errores en el formulario devuelvo la vista 
		}
		attributes.addFlashAttribute("mensaje", "Vacante guardada correctamente"); //atributo flash
		vacanteService.guardar(vacante);
		System.out.println(vacante);
		return "redirect:/vacantes/index"; //redirect al metodo index.
	}
	
	@GetMapping("/delete")
	public String delete(@RequestParam("id") int idVacante,Model model) {
		model.addAttribute("id", idVacante);
		vacanteService.delete(idVacante);
		return "redirect:/vacantes/index";
	}
	
	@GetMapping("/editar")
	public String editar(@RequestParam("id") int idVacante,Model model,BindingResult result,Vacante vacante) {
		if(result.hasErrors()) {
			System.out.println("Ocurrio un error al introducir los datos del formulario");
			return "vacantes/formVacante"; //si hay errores en el formulario devuelvo la vista 
		}
		model.addAttribute("id", idVacante);
		model.addAttribute("categorias", categoriasService.findAllCategoria()); 
		vacanteService.buscarPorId(idVacante);
		
		return "vacantes/formVacante";
	}

	@GetMapping("/verDetalle/{id}")
	public String verDetalle(Model model,@PathVariable("id") int idVacante) {
		model.addAttribute("id", idVacante);
		Vacante vacante=vacanteService.buscarPorId(idVacante); //me devuelve el objeto de una lista estatica
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
