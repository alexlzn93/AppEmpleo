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
	@GetMapping("/vacantes")
	public String listVacantes(Model model) {
		//LISTA EN UNA TABLE TODAS LAS VACANTES DE LA LISTA
		model.addAttribute("list_vacantes", getVacantes());
		return "home/vacantes";
	}
	

	
	private List<Vacante> getVacantes(){
		//DEVUELVE UNA LISTA DE VACANTES DISTINTAS
		List<Vacante> vacantes= new ArrayList();
		Vacante v1= new Vacante(1, "JAVA", "Springboot, Jira..", new Date(), 14556,1,"empresa1.jpg");
		Vacante v2= new Vacante(2, "Ingeniero", "Quimico, para empresa importante", new Date(), 54826,0,"empresa2.jpg");
		Vacante v3= new Vacante(3, "Fontanero", "Con años de experiencia", new Date(), 15478,0,"empresa3.jpg");
		Vacante v4= new Vacante(4, "Cocinero", "en un importante restaurante de Madrid", new Date(), 12475,1,"empresa4.jpg");
		Vacante v5= new Vacante(5, "Arquitecto", " para nuevas viviendas en Alcobendas", new Date(), 87216,1,"empresa5.jpg");
		
		vacantes.add(v1);
		vacantes.add(v2);
		vacantes.add(v3);
		vacantes.add(v4);
		vacantes.add(v5);
		
		return vacantes;
		
	}
}
