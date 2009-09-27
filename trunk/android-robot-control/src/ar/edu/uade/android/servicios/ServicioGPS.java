package ar.edu.uade.android.servicios;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import framework.modulos.ModuloGPS;

public class ServicioGPS extends Service {
    
	@SuppressWarnings("unused")
	private ModuloGPS gps;
	
	public static final String POSITION_UPDATED = "Position_Updated";
	
	private Timer timer = new Timer();

	private static final long UPDATE_INTERVAL = 10000;

    public ServicioGPS()
    {
        gps = ModuloGPS.getInstance();
    }

    public IBinder onBind(Intent intent) {
	  return null;
	}
	
	@Override 
	public void onCreate() {
	  super.onCreate();
	  _startService();
	  Log.d(getClass().getSimpleName(), "Servicio GPS creado");
	} 

	private void _startService() {
		timer.scheduleAtFixedRate(new TimerTask() {
							        public void run() {
							          getPosition();
							        }
		      					},
		      					0,
		      					UPDATE_INTERVAL);
		Log.d(getClass().getSimpleName(), "Timer de Servicio GPS started");

	}
	
	 @Override 
	 public void onDestroy() {
	  super.onDestroy();
	  Log.d(getClass().getSimpleName(), "Destroying Servicio GPS");
	}
	 
	/**
	 * 
	 */
	private void getPosition() {
		announcePositionUpdated(getLatitude(), getLongitude());
	}

	/**
	 * 
	 * @param latitude
	 * @param longitude
	 */
	private void announcePositionUpdated(float latitude, float longitude) {
		Intent intent = new Intent(POSITION_UPDATED);
		intent.putExtra("latitude", latitude);
		intent.putExtra("longitude", longitude);
		Log.d(this.getClass().getName(),"Nueva posicion anunciada");
		sendBroadcast(intent);
	}
	 
	/**
	 * 
	 * @return
	 */
    public float getAltitude()
    {	
    	return 10F;
        //return gps.getAltitude();
    }
    
    /**
     * 
     * @return
     */
    public float getLatitude()
    {
    	return 34.12345F;
       // return gps.getLatitude();
    }

    /**
     * 
     * @return
     */
    public float getLongitude()
    {
    	return 58.12345F;
       // return gps.getLongitude();
    }
}
