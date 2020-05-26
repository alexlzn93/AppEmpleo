package com.alexlzn.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.alexlzn.interfaces.ICategoriaService;
import com.alexlzn.interfaces.IUsuarioService;
import com.alexlzn.interfaces.IVacantesService;
import com.alexlzn.model.Perfil;
import com.alexlzn.model.Usuario;
import com.alexlzn.model.Vacante;



@Controller
public class HomeController {
	@Autowired
	IVacantesService vacanteService;
	@Autowired
	ICategoriaService categoriasService;
	@Autowired
	IUsuarioService usuarioService;
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@GetMapping("/")
	public String goHome(Model model) {
		//PAGINA PRINCIPAL
		/*List<Vacante> listVacantes= vacanteService.vacantesDestacadas(); //SOLO SE MUESTRAN LAS OFERTAS DESTACADAS Y APROBADAS
		model.addAttribute("categorias", categoriasService.findAllCategoria());//lista de categorias en el index
		model.addAttribute("vacantes", listVacantes);*/
		return "home/index"; 
	}
	@GetMapping("/index")
	public String mostrarIndex(Authentication authentication, HttpSession session) {		
		
		// Como el usuario ya ingreso, ya podemos agregar a la session el objeto usuario.
		String username = authentication.getName();	
		System.out.println(username);
		
		for(GrantedAuthority rol: authentication.getAuthorities()) {
			System.out.println("ROL: " + rol.getAuthority());
		}
		
		if (session.getAttribute("usuario") == null){
			Usuario usuario = usuarioService.findByUsername(username);
			usuario.setPassword(null);
			System.out.println("Usuario--> " + usuario);
			session.setAttribute("usuario", usuario);
		}
		
		return "redirect:/";
	}
	@GetMapping("/singup")
	public String registroUsuario(Usuario usuario,Model model) { //th:object="${usuario}" DATA BINDING form con Beans
		//FORMULARIO PARA CREAR UN USUARIO NUEVO
		return "usuarios/formRegistro";
	}
	@PostMapping("/singup")
	public String saveUsuario(Usuario usuario,BindingResult result,RedirectAttributes attributes,Model model) { //DATA BINDING

		String passwordNoCryptada= usuario.getPassword();
		String passwordCryptada = passwordEncoder.encode(passwordNoCryptada);
		usuario.setPassword(passwordCryptada);
		usuario.setFecha_alta(new Date());//FECHA DEL SISTEMA
		usuario.setEstatus(1);//POR DEFECTO TODOS LOS USUARIOS QUE SE DEN DE ALTA ESTARAN DESBLOQUEADOS
		
		System.out.println(usuario.toString());
		//CREAMOS EL PERFIL QUE LE ASIGNAREMOS A UN NUEVO USUARIO
		Perfil perfil= new Perfil();
		perfil.setId(3); //EL ID 3 ES EL USUARIO
		usuario.agregar(perfil);
		
		//GUARDAMOS EL USUARIO EN LA BASE DE DATOS
		usuarioService.guardar(usuario);
		System.out.println("--------------->"+usuario);
		attributes.addFlashAttribute("msg", "Gracias por registrate en JobsApp!");
		return "redirect:/login"; //redirect a login
	}
	
	@GetMapping("/login")
	public String mostrarLogin() {
		return "home/formLogin";
	}
	/**
	 * Metodo encargado de cerrar la sesion de usuario
	 * @param request para obtener ls sesion actual
	 * @return
	 */
	@GetMapping("/logout")
	public String logout(HttpServletRequest request) {
		SecurityContextLogoutHandler logoutHandler = new SecurityContextLogoutHandler();
		logoutHandler.logout(request, null, null);
		return "redirect:/";
	}
	/**
	 * 
	 * Metodo para encriptar contraseñas para guardar en la base de datos si hubiesen contraseñas sin encriptar
	 * @param texto
	 * @return
	 */
	@GetMapping("/bycrypt/{texto}")
	@ResponseBody //en vez de buscar un HTML se renderiza el texto al navegador
	public String encriptar(@PathVariable("texto") String texto) {
		return texto+ "Ecriptado en Bycrypt: " + passwordEncoder.encode(texto);
	}
	
	
	@ModelAttribute
	public void recursosComunes(Model model) {
		Vacante vacanteSearch = new Vacante();
		vacanteSearch.resetImages();//PONGO LA IMAGEN A NULL CON ESE METODO
		model.addAttribute("vacantes", vacanteService.vacantesDestacadas()); 
		model.addAttribute("categorias", categoriasService.findAllCategoria());
		model.addAttribute("buscarVacante", vacanteSearch);
		
	}
	@InitBinder
	public void errorStringDate(WebDataBinder wdb) {
		SimpleDateFormat dateformat= new SimpleDateFormat("dd-MM-YYYY");
		wdb.registerCustomEditor(Date.class, new CustomDateEditor(dateformat, false));
		//SETTEA LOS STRING A NULOS EN EL DATA BINDING 
		wdb.registerCustomEditor(String.class, new StringTrimmerEditor(true));
		
	}
	
}
