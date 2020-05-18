package com.alexlzn.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Pageable;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.alexlzn.interfaces.ICategoriaService;
import com.alexlzn.interfaces.IVacantesService;
import com.alexlzn.model.Categoria;
import com.alexlzn.model.Vacante;
import com.alexlzn.util.UploadFile;


@Controller
@RequestMapping("/vacantes")
public class VacantesController {
	@Value("${jobsapp.ruta.imagenes}")
	private String rutaImagenes; //ruta donde estan las imagenes
	@Autowired
	IVacantesService vacanteService;
	@Autowired
	ICategoriaService categoriasService;
	
	/*
	 * INDEX CON PAGINACION
	 * 
	 */
	@GetMapping("/index")
	public String index(Model model, Vacante vacante, Pageable page) {
		//LISTA EN UNA TABLE TODAS LAS VACANTES DE LA LISTA
		model.addAttribute("list_vacantes", vacanteService.findAllVacantesPaginacion(page));
		return "vacantes/listVacantes";
	}
	
	@GetMapping("/create")
	public String formVacante(Vacante vacante,Model model) { //th:object="${vacante}" DATA BINDING form con Beans
		//FORMULARIO PARA CREAR UNA VACANTE NUEVA
		//desplegar en el option la lista de las categorias
		//model.addAttribute("categorias", categoriasService.findAllCategoria()); 
		return "vacantes/formVacante";
	}
	@PostMapping("/save")
	public String saveVacante(Vacante vacante,BindingResult result,RedirectAttributes attributes
			,@RequestParam("archivoImagen") MultipartFile multipart,Model model) { //DATA BINDING
		
		//ERRORES
		if(result.hasErrors()) {
			System.out.println("Ocurrio un error al introducir los datos del formulario");
			return "vacantes/formVacante"; //si hay errores en el formulario devuelvo la vista 
		}
		//SUBIDA DE IMAGENES
		if(!multipart.isEmpty()) {
			String ruta="c:/jobsapp/imagenes/";
			String nombreImagen=UploadFile.saveFiles(multipart, ruta);
			if(nombreImagen !=null) {
				vacante.setImages(nombreImagen);
			}
		}
		
		attributes.addFlashAttribute("mensaje", "Oferta guardada correctamente"); //atributo flash en listVacantes
		vacanteService.guardar(vacante);
		System.out.println(vacante);
		return "redirect:/vacantes/index"; //redirect al metodo index.
	}
	
	@GetMapping("/delete")
	public String delete(@RequestParam("id") int idVacante,Model model, RedirectAttributes attribute) {
		System.out.println("Eliminada la vacante :" + idVacante);
		vacanteService.delete(idVacante);
		attribute.addAttribute("mensaje", "La oferta fue eliminada");
		return "redirect:/vacantes/index";
	}
	
	@GetMapping("/editar")
	public String editar(@RequestParam("id") int idVacante,Model model) {
		
			System.out.println("Editando vacante " + idVacante);
			Vacante vacante= vacanteService.buscarPorId(idVacante);
			//model.addAttribute("categorias", categoriasService.findAllCategoria()); 
			model.addAttribute("vacante", vacante);
			return "vacantes/formVacante";
	}

	@GetMapping("/verDetalle/{id}")
	public String verDetalle(Model model,@PathVariable("id") int idVacante) {
		model.addAttribute("id", idVacante);
		Vacante vacante=vacanteService.buscarPorId(idVacante); 
		model.addAttribute("vacante", vacante);
		System.out.println(idVacante);
		return "vacantes/detalle";
	}
	/*
	 * METODO PARA BUSCAR VACANTES POR DESCRIPCION Y POR CATEGORIAS
	 * 
	 */
	@GetMapping("/buscarVacante")
	public String busquedaVacantes(@ModelAttribute("buscarVacante")Vacante vacante, Model model) {
		System.out.println("Buscando vacante" + vacante);
		//where descripcion like '%?%' 
		ExampleMatcher matcher= ExampleMatcher.matching()
				.withMatcher("descripcion", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase());
		Example<Vacante> example= Example.of(vacante, matcher);
		List<Vacante> lista= vacanteService.findByExample(example);
		
		model.addAttribute("vacantes", lista);
		return "home/index";
	}
	
	//CORREGIR EL ERROR DE STRING A TIPO DATE
	@InitBinder
	public void errorStringDate(WebDataBinder wdb) {
		SimpleDateFormat dateformat= new SimpleDateFormat("dd-MM-YYYY");
		wdb.registerCustomEditor(Date.class, new CustomDateEditor(dateformat, false));
		//SETTEA LOS STRING A NULOS EN EL DATA BINDING (images=null;)
		wdb.registerCustomEditor(String.class, new StringTrimmerEditor(true));
	}
	//DATOS QUE SON COMUNES O SE UTILIZAN EN VARIOS METODOS
	@ModelAttribute
	public void recursosComunes(Model model) {
		model.addAttribute("categorias", categoriasService.findAllCategoria());
		Vacante vacanteSearch = new Vacante();
		vacanteSearch.resetImages();//PONGO LA IMAGEN A NULL CON ESE METODO
		model.addAttribute("vacantes", vacanteService.vacantesDestacadas());
		model.addAttribute("buscarVacante", vacanteSearch);
	}
	
}
