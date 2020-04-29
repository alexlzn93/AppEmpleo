package com.alexlzn.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.alexlzn.interfaces.ICategoriaService;
import com.alexlzn.model.Categoria;

@Controller
@RequestMapping("/categorias") //localhost:8080/categorias/index-create
public class CategoriasController {
	@Autowired
	ICategoriaService categoriaService;
	
	@RequestMapping("/index") 
	public String index(Model model) {
		categoriaService.findAllCategoria();
		model.addAttribute("list_categorias", categoriaService.findAllCategoria());
		return "categorias/listCategorias";
	}
	@RequestMapping("/create")
	public String createCategoria(Model model,Categoria categoria) {
		//FORMULARIO NUEVA CATEGORIA
		return "categorias/formCategorias";
	}
	@PostMapping("/save")
	public String saveCategoria( Categoria categoria,BindingResult result, RedirectAttributes attributes) {
		if(result.hasErrors()) {
			System.out.println("Ocurrio un error al introducir los datos del formulario");
			return "vacantes/formVacante"; //si hay errores en el formulario devuelvo la vista 
		}
		attributes.addFlashAttribute("mensaje", "Categoria guardada correctamente"); //atributo flash
		categoriaService.guardar(categoria);
	
		return "redirect:/categorias/index";
	}
	
	@GetMapping("/delete")
	public String delete(@RequestParam("id") int idcategoria,Model model) {
		model.addAttribute("id", idcategoria);
		categoriaService.delete(idcategoria);
		return "mensajeEliminado";
	}
	
	@GetMapping("/editar")
	public String editar(@RequestParam("id") int idcategoria,Model model) {
		model.addAttribute("id", idcategoria);
		categoriaService.buscarPorId(idcategoria);
		return "redirect:/categorias/create";
	}
}
