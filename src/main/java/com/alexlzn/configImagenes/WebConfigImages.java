package com.alexlzn.configImagenes;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfigImages implements WebMvcConfigurer {
	/*
	 * Clase para configurar donde estaran las imagenes que se suban para una vacante
	 * 
	 */
	@Value("${jobsapp.ruta.imagenes}") //propiedad personalizada donde esta la ruta de la imagenes
	private String rutaImagenes;
	
	
	public void addResourceHandlers(ResourceHandlerRegistry registro) {
		//registro.addResourceHandler("/logotipos/**").addResourceLocations("file:c:/jobsapp/imagenes/");
		registro.addResourceHandler("/logotipos/**").addResourceLocations("file:" + rutaImagenes);
	}
}
