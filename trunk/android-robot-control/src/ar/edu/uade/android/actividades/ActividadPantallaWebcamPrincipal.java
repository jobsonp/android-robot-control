package ar.edu.uade.android.actividades;

import android.graphics.Bitmap;
import android.os.Bundle;
import ar.edu.uade.android.R;
import ar.edu.uade.android.mjpeg.MjpegInputStream;
import ar.edu.uade.android.mjpeg.MjpegView;
import ar.edu.uade.android.utils.Configuracion;
import ar.edu.uade.android.utils.Constantes;

public class ActividadPantallaWebcamPrincipal
    extends ActividadPantallaAbstract
{

    private MjpegView mv;

    @Override
    protected void onCreate( Bundle savedInstanceState )
    {
        super.onCreate( savedInstanceState );

        setContentView( R.layout.webcam_principal );

        initializeStreamingView();
    }

    private void initializeStreamingView()
    {
        boolean mostrarCPS = getPreferences()
            .getBoolean(
                         getResources().getString( R.string.configuracion_interfaz_cps_preference_key ),
                         Boolean.parseBoolean( getResources()
                             .getString( R.string.configuracion_interfaz_cps_valor_defecto ) ) );

        mv = (MjpegView) this.findViewById( R.id.mjpeg_view_main );
        mv.setSource( MjpegInputStream.read( "http://" + Configuracion.getConfigString( Constantes.IP_PLAYER ) + ":"
            + Configuracion.getConfigString( Constantes.MAIN_WEBCAM_PORT ) ) );
        mv.setDisplayMode( MjpegView.SIZE_BEST_FIT );
        mv.showFps( mostrarCPS );
    }

    @Override
    protected boolean isRouteDisplayed()
    {
        return false;
    }

    @Override
    public void onPause()
    {
        super.onPause();
        mv.stopPlayback();
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        mv.stopPlayback();
    }

    @Override
    public void ejecutarFlechaAbajo()
    {
        try
        {
            getControladorServos().atras();
        }
        catch ( Exception e )
        {
            toast( getResources().getString( R.string.error_al_enviar_el_comando ) );
        }
    }

    @Override
    public void ejecutarFlechaArriba()
    {
        try
        {
            getControladorServos().adelante();
        }
        catch ( Exception e )
        {
            toast( getResources().getString( R.string.error_al_enviar_el_comando ) );
        }
    }

    @Override
    public void ejecutarFlechaDerecha()
    {
        try
        {
            getControladorServos().girarDer();
        }
        catch ( Exception e )
        {
            toast( getResources().getString( R.string.error_al_enviar_el_comando ) );
        }
    }

    @Override
    public void ejecutarFlechaIzquierda()
    {
        try
        {
            getControladorServos().girarIzq();
        }
        catch ( Exception e )
        {
            toast( getResources().getString( R.string.error_al_enviar_el_comando ) );
        }
    }

    @Override
    public Bitmap obtenerBitmap()
    {
        return mv.getLastFrame();
    }
}
