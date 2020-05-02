package com.alexlzn.util;

import java.io.File;
import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

public class UploadFile {

	public static String saveFiles(MultipartFile multipart, String ruta) {
		//OBTENEMOS EL NOMBRE ORIGINAL DEL ARCHIVO
		String nombreOriginal=multipart.getOriginalFilename();
		try {
			//FORMAMOS EL NOMBRE DEL ARCHIVO PARA GUARDARLO EN EL DISCO DURO
			File imageFile= new File(ruta + nombreOriginal);	
			System.out.println("Archivo: " +  imageFile.getAbsolutePath());
			//GUARDAMOS FISICAMENTE EL  ARCHIVO EN EL DISCO DURO
			multipart.transferTo(imageFile);
			return nombreOriginal;
		} catch (IOException e) {
			System.out.println("error" + e.getMessage());
			return null;
		}
		
		
		
	}
}
