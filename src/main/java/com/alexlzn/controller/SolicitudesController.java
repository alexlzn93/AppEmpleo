package com.alexlzn.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.alexlzn.interfaces.ICategoriaService;
import com.alexlzn.interfaces.ISolicitudService;
import com.alexlzn.interfaces.IVacantesService;
import com.alexlzn.model.Solicitud;
import com.alexlzn.model.Vacante;
import com.alexlzn.util.UploadFile;

@Controller
@RequestMapping("/solicitud")
public class SolicitudesController {

	@Autowired
	ISolicitudService solicitudService;
	@Autowired
	ICategoriaService categoriasService;
	@Autowired
	IVacantesService vacanteService;
	
	@GetMapping("/create")
	public String create(Solicitud solicitud,Model model,Vacante vacante) {
		model.addAttribute("list_vacantes", vacanteService.findAllVacantes());
		model.addAttribute("categorias",categoriasService.findAllCategoria() );
		return "solicitudes/formSolicitudes";
	}
	//save
	@PostMapping("/save")
	public String saveVacante(Solicitud solicitud,BindingResult result,RedirectAttributes attributes
			,@RequestParam("archivoCV") MultipartFile multipart,Model model) { //DATA BINDING
		
		//ERRORES
		if(result.hasErrors()) {
			System.out.println("Ocurrio un error al introducir los datos del formulario");
			 
			return "solicitudes/formSolicitudes"; //si hay errores en el formulario devuelvo la vista 
		}
		//SUBIDA DE ARCHIVOS
		if(!multipart.isEmpty()) {
			String ruta="C:/Users/34687/Desktop/Proyectos-GIT/AppEmpleo/src/main/resources/static/images";
			String nombreArhivo=UploadFile.saveFiles(multipart, ruta);
			if(nombreArhivo !=null) {
				solicitud.setArchivo(nombreArhivo);
			}
		}
		
		attributes.addFlashAttribute("mensaje", "Solicitud presentada correctamente"); //atributo flash
		solicitudService.guardar(solicitud);
		System.out.println(solicitud);
		return "redirect:/vacantes/index"; //redirect al metodo index.
	}
	
	
	
	@GetMapping("/inscribirte/{id}")
	public String verDetalle(Model model,@PathVariable("id") int idVacante,Solicitud solicitud) {
		model.addAttribute("id", idVacante);
		Vacante vacante=vacanteService.buscarPorId(idVacante); 
		model.addAttribute("vacante", vacante);
		System.out.println(idVacante);
		return "solicitudes/formSolicitudes";
	}
}
