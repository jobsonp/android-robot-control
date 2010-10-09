package ar.edu.uade.android.utils;

import java.io.Serializable;

public class GpsPosition implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private float latitud;
	private float longitud;
	private float altitude;
	
	@Override
	public String toString() {
		return "GpsPosition [lat=" + latitud + ", long=" + longitud + ", altitude=" + altitude +"]";
	}

	public GpsPosition() {
		
	}
	
	public GpsPosition(float latitud,float longitud,float altitude) {
		this.latitud = latitud;
		this.longitud = longitud;
		this.altitude = altitude;
	}
	
	public float getLatitud() {
		return latitud;
	}
	public void setLatitud(float latitud) {
		this.latitud = latitud;
	}
	
	public float getLongitud() {
		return longitud;
	}
	public void setLongitud(float longitud) {
		this.longitud = longitud;
	}

	public float getAltitude() {
		return altitude;
	}

	public void setAltitude(float altitude) {
		this.altitude = altitude;
	}
	

}
