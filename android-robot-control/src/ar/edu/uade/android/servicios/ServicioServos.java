package ar.edu.uade.android.servicios;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.os.IBinder;
import framework.modulos.ModuloRuedas;

public class ServicioServos
    extends Service
{

    private ModuloRuedas ruedas;

    @Override
    public ComponentName startService( Intent service )
    {
        super.startService( service );
        
        ruedas = ModuloRuedas.getInstance();
        
        return new ComponentName( "ar.edu.uade.android.servicios", "ServicioServos" );
    }
    
    @Override
    public IBinder onBind( Intent intent )
    {
        // TODO Auto-generated method stub
        return null;
    }

    public void Adelante()
    {
        ruedas.Adelante();
    }

    public void Atras()
    {
        ruedas.Atras();
    }

    public void RotarIzq()
    {
        ruedas.RotarIzq();
    }

    public void RotarDer()
    {
        ruedas.RotarDer();
    }

    // Metodos que probablemente no usemos.
    public void GirarIzq()
    {
        ruedas.GirarIzq();
    }

    public void GirarDer()
    {
        ruedas.GirarDer();
    }
    
    public void Stop()
    {
        ruedas.Stop();
    }
}
