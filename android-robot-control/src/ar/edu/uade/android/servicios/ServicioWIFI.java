package ar.edu.uade.android.servicios;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import framework.modulos.ModuloWireless;

public class ServicioWIFI
    extends Service
{

    private ModuloWireless wireless;

    public ServicioWIFI()
    {
        wireless = ModuloWireless.getInstance();
    }

    @Override
    public IBinder onBind( Intent intent )
    {
        // TODO Auto-generated method stub
        return null;
    }

    public int[] getWifiData()
    {
        return wireless.getWifiData();
    }

    public int getNoise()
    {
        return wireless.getNoise();
    }

    public int getLevel()
    {
        return wireless.getLevel();
    }

    public int getLink()
    {
        return wireless.getLink();
    }
}