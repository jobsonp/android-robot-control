package framework.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


public class LeeConfig {
	
	public static String buscarConfiguracion (String item){
		File archivo = new File("config.txt");
		FileReader archivoLector;
		String linea=new String();
		String valor=null;
		try {
			archivoLector = new FileReader(archivo);
			BufferedReader bufferArchivo = new BufferedReader(archivoLector);
			while(bufferArchivo.ready()){
				linea = bufferArchivo.readLine();
				if(linea.startsWith(item)){
					valor = linea.substring(item.length(), linea.length());
					break;
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return valor;
	}
}
