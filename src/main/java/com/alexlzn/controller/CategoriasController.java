package com.alexlzn.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.alexlzn.interfaces.ICategoriaService;
import com.alexlzn.model.Categoria;

@Controller
@RequestMapping("/categorias") //localhost:8080/categorias/index-create
public class CategoriasController {
	@Autowired
	ICategoriaService categoriaService;
	
	@RequestMapping("/index") 
	public String index(Model model) {
		
		return "categorias/listCategorias";
	}
	@RequestMapping("/create")
	public String createCategoria(Model model,@ModelAttribute Categoria categoria) {
		//FORMULARIO NUEVA CATEGORIA
		return "categorias/formCategorias";
	}
	@PostMapping("/save")
	public String saveCategoria(@ModelAttribute Categoria categoria, Model model) {
		categoriaService.guardar(categoria);
		//System.out.println(nombre + " " +descripcion);
		return "categorias/listCategorias";
	}
}
