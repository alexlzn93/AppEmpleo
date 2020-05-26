package com.alexlzn.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.alexlzn.interfaces.ICategoriaService;
import com.alexlzn.interfaces.ISolicitudService;
import com.alexlzn.interfaces.IUsuarioService;
import com.alexlzn.interfaces.IVacantesService;
import com.alexlzn.model.Solicitud;
import com.alexlzn.model.Usuario;
import com.alexlzn.model.Vacante;
import com.alexlzn.util.UploadFile;



@Controller
@RequestMapping("/solicitud")
public class SolicitudesController {
	@Value("${jobsapp.ruta.cv}")
	private String rutaCV; //ruta donde estan las imagenes
	@Autowired
	ISolicitudService solicitudService;
	@Autowired
	ICategoriaService categoriasService;
	@Autowired
	IVacantesService vacanteService;
	@Autowired
	IUsuarioService usuarioService;
	/**
	 * Metodo para mostar la lista de solicitudes con paginacion, solo accesibe a los usuarios con el rol de Administrador, Supervisor
	 */
	@GetMapping("/index")
	public String indexSolicitudes(Model model,Pageable page) {
		Page<Solicitud> solicitudes= solicitudService.findAllSolicitudesPaginacion(page);
		model.addAttribute("solicitudes", solicitudes);
		return "solicitudes/listSolicitudes";
	}
	/**
	 * Metodo para mostrar el formSolicitudes, de la oferta elegida.Se trae el objeto Vacante
	 * @param idVacante el id de la vacante seleccionada
	 */
	@GetMapping("/inscribirte/{id}")
	public String incribirseOferta(Model model,@PathVariable("id") Integer idVacante,Solicitud solicitud) {
		Vacante vacante=vacanteService.buscarPorId(idVacante); 
		model.addAttribute("id", idVacante);
		model.addAttribute("vacante", vacante);
		System.out.println(idVacante);
		return "solicitudes/formSolicitudes";
	}
	/**
	 * Metodo para guardar la Solicitud en la base de datos
	 * @param authentication para recuperar el usuario de la sesion
	 * @param objeto Solicitud DataBinding 
	 * 
	 */
	@PostMapping("/save")
	public String guardar(Solicitud solicitud, BindingResult result, Model model, HttpSession session,
			@RequestParam("archivoCV") MultipartFile multiPart, RedirectAttributes attributes, Authentication authentication) {	
		
		// Recuperamos el username que inicio sesion
		String username = authentication.getName();
		
		if (result.hasErrors()){
			
			System.out.println("Existieron errores");
			return "solicitudes/formSolicitudes";
		}	
		//subida de archivos
		if (!multiPart.isEmpty()) {
			
			String nombreArchivo = UploadFile.saveFiles(multiPart, rutaCV);
			if (nombreArchivo!=null){ // El archivo (CV) si se subio				
				solicitud.setArchivo(nombreArchivo); // Asignamos el nombre de la imagen
			}	
		}

		// Buscamos el objeto Usuario en BD	
		Usuario usuario = usuarioService.findByUsername(username);		
		
		solicitud.setUsuario(usuario); // Referenciamos la solicitud con el usuario 
		solicitud.setFecha(new Date()); //fecha del dia en el que se solicite la oferta
		// Guadamos el objeto solicitud en la bd
		solicitudService.guardar(solicitud);
		attributes.addFlashAttribute("msg", "Gracias por enviar tu CV!");
			
		
		return "redirect:/";		
	}
	/**
	 * Metodo para eliminar una solicitud
	 * 
	 */
	@GetMapping("/delete/{id}")
	public String eliminarSolicitud(@PathVariable("id") Integer idSolicitud,RedirectAttributes attributes) {
		
		solicitudService.eliminar(idSolicitud);
		attributes.addAttribute("msg", "Solicitud eliminada correctamente");
		return "redirect:/solicitud/index";
		
	}
	/**
	 * Metodo para editar una solicitud, me traigo el objeto entero para volver al formSolicitud
	 * @param  idSolicitud 
	 */
	@GetMapping("/editar/{id}")
	public String editarSolicitud(@PathVariable("id") Integer idSolicitud,Model model) {
		System.out.println("Editar solicitud " + idSolicitud);
		Solicitud solicitud= solicitudService.buscarPorId(idSolicitud);
		Vacante vacante = vacanteService.buscarPorId(idSolicitud);
		model.addAttribute("solicitud", solicitud);
		model.addAttribute("vacante", vacante);
		return "solicitudes/formSolicitudes";
	}
	
	
	
	@InitBinder
	public void initBinder(WebDataBinder webDataBinder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		webDataBinder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}
}
