package com.alexlzn.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/categorias") //localhost:8080/categorias/index-create
public class CategoriasController {

	@RequestMapping("/index") 
	public String index(Model model) {
		
		return "categorias/listCategorias";
	}
	@RequestMapping("/create")
	public String createCategoria(Model model) {
		
		return "categorias/formCategorias";
	}
	@PostMapping("/save")
	public String saveCategoria(@RequestParam("nombre") String nombre,@RequestParam("descripcion") String descripcion, 
			Model model) {
		System.out.println(nombre + " " +descripcion);
		return "categorias/listCategorias";
	}
}
