package com.alexlzn.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alexlzn.interfaces.IVacantesService;
import com.alexlzn.model.Vacante;

@RestController
@RequestMapping("/rest")
public class Rest_Controller {
	@Autowired
	IVacantesService vacantesService;
	
	@GetMapping("/listvacantes")
	public List<Vacante> findAllVacante(){
		return vacantesService.findAllVacantes();
	}
}
