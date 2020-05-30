package com.alexlzn.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
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
	
	
	
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable("id") Integer idUser,Model model, RedirectAttributes attribute) {
		System.out.println("Eliminado el usuario con ID :" + idUser);
		usuarioService.delete(idUser);
		attribute.addAttribute("mensaje", "El usuario fue eliminado");
		return "redirect:/usuarios/index";
	}
	@GetMapping("/bloquear/{id}")
	public String bloquearUsuario(@PathVariable("id") Integer idUser,Model model, RedirectAttributes attribute) {
		System.out.println("Usuario bloqueado :" + idUser);
		Usuario user= usuarioService.bloquearUsuario(idUser);
	
		System.out.println(user);
		usuarioService.guardar(user);
		
		attribute.addAttribute("mensaje", "El usuario fue bloqueado");
		return "redirect:/usuarios/index";
	}
	@GetMapping("/desbloquear/{id}")
	public String desbloquearUsuario(@PathVariable("id") Integer idUser,Model model, RedirectAttributes attribute) {
		System.out.println("Usuario desbloqueado :" + idUser);
		Usuario user= usuarioService.desbloquearUsuario(idUser);
		System.out.println(user);
		usuarioService.guardar(user);
		
		attribute.addAttribute("mensaje", "El usuario fue desbloqueado");
		return "redirect:/usuarios/index";
	}

	
	
	
	
	//CORREGIR EL ERROR DE STRING A TIPO DATE
		@InitBinder
		public void errorStringDate(WebDataBinder wdb) {
			SimpleDateFormat dateformat= new SimpleDateFormat("dd-MM-YYYY");
			wdb.registerCustomEditor(Date.class, new CustomDateEditor(dateformat, false));
		}
		
	}

