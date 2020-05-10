package com.alexlzn.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.alexlzn.interfaces.IUsuarioService;
import com.alexlzn.model.Perfil;
import com.alexlzn.model.Usuario;
@Controller
@RequestMapping("/usuarios")
public class UsuarioController {

	@Autowired
	IUsuarioService usuarioService;
	
	@GetMapping("/index")
	public String index(Model model, Usuario usuario) {
		//LISTA EN UNA TABLE TODAS LAS VACANTES DE LA LISTA
		model.addAttribute("list_usuarios", usuarioService.findAllUsuarios());
		return "usuarios/listUsuarios";
	}
	@GetMapping("/create")
	public String registroUsuario(Usuario usuario,Model model) { //th:object="${usuario}" DATA BINDING form con Beans
		//FORMULARIO PARA CREAR UN USUARIO NUEVO
		return "usuarios/formRegistro";
	}
	@PostMapping("/save")
	public String saveUsuario(Usuario usuario,BindingResult result,RedirectAttributes attributes,Model model) { //DATA BINDING
		
		usuario.setFecha_alta(new Date());//FECHA DEL SISTEMA
		usuario.setEstatus(1);//POR DEFECTO TODOS LOS USUARIOS QUE SE DEN DE ALTA ESTARAN DESBLOQUEADOS
		//CREAMOS EL PERFIL QUE LE ASIGNAREMOS A UN NUEVO USUARIO
		Perfil perfil= new Perfil();
		perfil.setId(3); //EL ID 3 ES EL USUARIO
		usuario.agregar(perfil);
		//GUARDAMOS EL USUARIO EN LA BASE DE DATOS
		usuarioService.guardar(usuario);
		System.out.println(usuario);
		attributes.addFlashAttribute("mensaje", "Usuario registrado correctamente"); //atributo flash en listVacantes
		return "redirect:/usuarios/index"; //redirect al metodo index.
		}
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable("id") int idUser,Model model, RedirectAttributes attribute) {
		System.out.println("Eliminado el usuario con ID :" + idUser);
		usuarioService.delete(idUser);
		attribute.addAttribute("mensaje", "El usuario fue eliminado");
		return "redirect:/usuarios/index";
	}
	
	
	
	
	//CORREGIR EL ERROR DE STRING A TIPO DATE
		@InitBinder
		public void errorStringDate(WebDataBinder wdb) {
			SimpleDateFormat dateformat= new SimpleDateFormat("dd-MM-YYYY");
			wdb.registerCustomEditor(Date.class, new CustomDateEditor(dateformat, false));
		}
		
	}

