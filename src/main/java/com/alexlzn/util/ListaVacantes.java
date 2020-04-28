package com.alexlzn.util;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.alexlzn.interfaces.IVacantesService;
import com.alexlzn.model.Vacante;
import com.alexlzn.service.VacantesService;

public class ListaVacantes {
	private static List<Vacante> vacantes= new ArrayList();

	public static List<Vacante> getVacantes(){
		//DEVUELVE UNA LISTA DE VACANTES DISTINTAS
	
		Vacante v1= new Vacante(1, "JAVA", "Springboot,microservicios,RestFul,Java EE", new Date(), 14556,1,"empresa1.jpg","creada","gfgghfh");
		Vacante v2= new Vacante(2, "Ingeniero", "Quimico, para empresa importante", new Date(), 54826,0,"empresa2.jpg","creada","gfgghfh");
		Vacante v3= new Vacante(3, "Fontanero", "Con años de experiencia", new Date(), 15478,0,"empresa3.jpg","creada","gfgghfh");
		Vacante v4= new Vacante(4, "Cocinero", "en un importante restaurante de Madrid", new Date(), 12475,1,"empresa4.jpg","creada","gfgghfh");
		Vacante v5= new Vacante(5, "Arquitecto", " para nuevas viviendas en Alcobendas", new Date(), 87216,1,"empresa5.jpg","creada","gfgghfh");
	
		
		vacantes.add(v1);
		vacantes.add(v2);
		vacantes.add(v3);
		vacantes.add(v4);
		vacantes.add(v5);
		
		return vacantes;
		
	}
	public static void addVacante(Vacante vacante) {
		vacantes.add(vacante);
	}
}
