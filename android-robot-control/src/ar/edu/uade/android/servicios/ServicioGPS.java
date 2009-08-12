package ar.edu.uade.android.servicios;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import framework.modulos.ModuloGPS;

public class ServicioGPS
    extends Service
{
    private ModuloGPS gps;

    public ServicioGPS()
    {
        gps = ModuloGPS.getInstance();
    }

    @Override
    public IBinder onBind( Intent intent )
    {
        // TODO Auto-generated method stub
        return null;
    }

    public float getAltitude()
    {
        return gps.getAltitude();
    }

    public float getLatitude()
    {
        return gps.getLatitude();
    }

    public float getLongitude()
    {
        return gps.getLongitude();
    }
}
