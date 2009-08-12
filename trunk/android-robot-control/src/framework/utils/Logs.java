package framework.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

import framework.constants.Constants;
/**
  * DESCRIPCION DE LA CLASE 
  *	void : mensaje (nroMensaje , nroModulo , tipoMensaje, textoExtra)
  *	void : mensaje ( STRING    ,  STRING   , final int  , String)
  *	En un archivo de texto " errores.txt " estan almacenados los modulos y los mensajes
  *	estandarizados conocidos. Se pueden agregar al archivo respentando las columnas
  *	En archivo config.txt parametro LOG_PANTALLA: si es true muestra en pantalla los mensajes
  *	si es false no los muestra. pero siempre los graba en log.txt
  **/

public class Logs {

		public static void mensaje(String nroMensaje, String nroModulo, int tipoMensaje, String mensaje){
			boolean valorConfig = false;
			// coloca en valorConfig si imprime el log en pantalla o no.
			if (LeeConfig.buscarConfiguracion(Constants.LOG_PANTALLA).trim().compareTo("true")==0)
				valorConfig = true;
			else
				valorConfig = false;
			if (mensaje == null)
				imprimirMensaje(leerTxt(nroMensaje),leerTxt(nroModulo), tipoMensaje,valorConfig);
			else
				imprimirMensaje(mensaje,leerTxt(nroModulo), tipoMensaje,valorConfig);
		}
		
		private static void imprimirMensaje(String descripcion, String nroModulo, int tipoMensaje,boolean estado){
			// estado indica si imprime en pantalla o no los logs. true los imprime en pantalla y en archivo
			// false solo los imprime en archivo.
			switch(tipoMensaje){
			case Constants.MSG_ERR:
				if (estado)
				System.err.println("[ "+new Date()+" ]"+" [  En módulo: "+nroModulo+" Mensaje: "+descripcion+"  ]");
				escribirLog("[ "+new Date()+" ]"+" [  En módulo: "+nroModulo+" Mensaje: "+descripcion+"  ]"+" [ ERROR ]");
				break;
			case Constants.MSG_STD:
				if (estado)
				System.out.println("[ "+new Date()+" ]"+" [  En módulo: "+nroModulo+" Mensaje: "+descripcion+"  ]");
				escribirLog("[ "+new Date()+" ]"+" [  En módulo: "+nroModulo+" Mensaje: "+descripcion+"  ]");
				break;
			}
		}
		
		private static String leerTxt(String nroMensaje){
			File archivo = new File("errores.txt");
			FileReader archivoLector;
			boolean flag=false;
			String linea=new String();
			String descripcionError=null;
			try {
				archivoLector = new FileReader(archivo);
				BufferedReader bufferArchivo = new BufferedReader(archivoLector);
				while(bufferArchivo.ready()){
					linea = bufferArchivo.readLine();
					String nroError = linea.substring(0,5).trim();
				    descripcionError= linea.substring(6,linea.length());
				    if(nroError.compareTo(nroMensaje.trim())==0){
				    	flag= true;
				    	break;
				    }			  
				}			
				bufferArchivo.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} 
			if (flag == false)
				descripcionError = new String("#########");
			return descripcionError;
		}
		
		private static void escribirLog(String lineaAEscribir){
			//File archivo = new File ("log.txt");
			FileWriter archivoEscribe;
			try {
				archivoEscribe= new FileWriter("log.txt",true);
				BufferedWriter bufferArchivo = new BufferedWriter(archivoEscribe);
				bufferArchivo.append(lineaAEscribir);
				bufferArchivo.newLine();
				bufferArchivo.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		
}