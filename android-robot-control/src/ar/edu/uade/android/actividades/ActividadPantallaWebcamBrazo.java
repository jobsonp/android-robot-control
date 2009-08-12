package ar.edu.uade.android.actividades;

import android.os.Bundle;
import ar.edu.uade.android.R;


public class ActividadPantallaWebcamBrazo
    extends ActividadPantallaAbstract
{
    @Override
    protected void onCreate( Bundle savedInstanceState )
    {
        // TODO Auto-generated method stub
        super.onCreate( savedInstanceState );
        
        setContentView( R.layout.webcam_brazo );
    }

    @Override
    protected boolean isRouteDisplayed()
    {
        return false;
    }
}