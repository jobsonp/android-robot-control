package framework.modulos.player;

import java.util.Vector;

import javaclient2.PlayerClient;
import javaclient2.WiFiInterface;
import framework.conector.Conector;
import framework.interfaces.IModuloWireless;

public class ModuloPlayerWireless implements IModuloWireless {
	
	private static ModuloPlayerWireless instance = null;

	private ModuloPlayerWireless() {
		
	}
	
	public static ModuloPlayerWireless getInstance ()
	{
		if (instance==null)
			instance = new ModuloPlayerWireless();
		return instance;
	}
	
	@SuppressWarnings("unchecked")
	private int[] getSenal ()
	{
		int[] datos = {0,0,0};
		Conector conector = Conector.getInstance();
		Vector vv = conector.getInterfaceWIRELESS();
		((PlayerClient) vv.elementAt(0)).start();
		((PlayerClient) vv.elementAt(0)).readAll();

        if (((WiFiInterface) vv.elementAt(1)).isDataReady ()){
                   
                   datos[0] = ((WiFiInterface) vv.elementAt(1)).getData().getLinks()[0].getQual();
                   datos[1] = ((WiFiInterface) vv.elementAt(1)).getData().getLinks()[0].getNoise();
                   datos[2] = ((WiFiInterface) vv.elementAt(1)).getData().getLinks()[0].getLevel();
        }
        //	CERRAR CONEXION
        
		((PlayerClient) vv.elementAt(0)).stop();
		return datos;
	}
	
	public int[] getWifiData(){
		return getSenal();
	}
	
	public int getNoise() {
		int[] data = getSenal();
		return data[1];
	}

	public int getLevel() {
		int[] data = getSenal();
		return data[2];
	}

	public int getLink() {
		int[] data = getSenal();
		return data[0];
	}
}
