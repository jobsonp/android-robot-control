package ar.edu.uade.android.servicios;

import framework.enums.ModuloType;
import framework.factories.ModuloGPSFactory;
import framework.interfaces.IModuloGPS;

public class ServicioGPS {
    
	private IModuloGPS gps;
	
    public ServicioGPS() {
    	ModuloGPSFactory factory = new ModuloGPSFactory();
        gps = (IModuloGPS)factory.createModulo(ModuloType.STAGE);
    }

	/**
	 * 
	 * @return
	 */
    public float getAltitude() {	
    	return gps.getAltitude();
    }
    
    /**
     * 
     * @return
     */
    public float getLatitude(){
    	return gps.getLatitude();
    }

    /**
     * 
     * @return
     */
    public float getLongitude() {
    	return gps.getLongitude();
    }
}
