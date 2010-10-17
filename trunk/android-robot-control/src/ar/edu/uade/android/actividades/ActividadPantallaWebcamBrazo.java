package ar.edu.uade.android.actividades;

import android.content.SharedPreferences.Editor;
import android.graphics.Bitmap;
import android.os.Bundle;
import ar.edu.uade.android.R;
import ar.edu.uade.android.mjpeg.MjpegInputStream;
import ar.edu.uade.android.mjpeg.MjpegView;
import ar.edu.uade.android.utils.Configuracion;
import ar.edu.uade.android.utils.Constantes;

public class ActividadPantallaWebcamBrazo
    extends ActividadPantallaAbstract
{

    private MjpegView mv;

    @Override
    protected void onCreate( Bundle savedInstanceState )
    {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.webcam_brazo );
        resetArmAndHand();
        initializeStreamingView();
    }

    private void resetArmAndHand()
    {
        Editor edit = getPreferences().edit();
        edit.putInt( Constantes.STAGE_ARM_CURRENT_VERTICAL_ANGLE_PREFERENCES_KEY,
                     Constantes.STAGE_ARM_VERTICAL_DEFAULT_ANGLE );
        edit.putInt( Constantes.STAGE_ARM_CURRENT_HORIZONTAL_ANGLE_PREFERENCES_KEY,
                     Constantes.STAGE_ARM_HORIZONTAL_DEFAULT_ANGLE );
        edit.putInt( Constantes.STAGE_HAND_CURRENT_VALUE_PREFERENCES_KEY, Constantes.STAGE_HAND_DEFAULT_POSITION );
        edit.commit();

    }

    private void initializeStreamingView()
    {
        boolean mostrarCPS = getPreferences()
            .getBoolean(
                         getResources().getString( R.string.configuracion_interfaz_cps_preference_key ),
                         Boolean.parseBoolean( getResources()
                             .getString( R.string.configuracion_interfaz_cps_valor_defecto ) ) );

        mv = (MjpegView) this.findViewById( R.id.mjpeg_view_brazo );
        mv.setSource( MjpegInputStream.read( "http://" + Configuracion.getConfigString( Constantes.IP_PLAYER ) + ":"
            + Configuracion.getConfigString( Constantes.ARM_WEBCAM_PORT ) ) );
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
            getControladorBrazo().brazoBajar( Constantes.STAGE_ARM_VERTICAL_DEFAULT_MOVEMENT_ANGLE );
        }
        catch ( Exception e )
        {
            toast( getResources().getString( R.string.error_al_enviar_el_comando ) );
            e.printStackTrace();
        }
    }

    @Override
    public void ejecutarFlechaArriba()
    {
        try
        {
            getControladorBrazo().brazoSubir( Constantes.STAGE_ARM_VERTICAL_DEFAULT_MOVEMENT_ANGLE );
        }
        catch ( Exception e )
        {
            toast( getResources().getString( R.string.error_al_enviar_el_comando ) );
            e.printStackTrace();
        }
    }

    @Override
    public void ejecutarFlechaDerecha()
    {
        try
        {
            getControladorBrazo().brazoDerecha( Constantes.STAGE_ARM_HORIZONTAL_DEFAULT_MOVEMENT_ANGLE );
        }
        catch ( Exception e )
        {
            toast( getResources().getString( R.string.error_al_enviar_el_comando ) );
            e.printStackTrace();
        }
    }

    @Override
    public void ejecutarFlechaIzquierda()
    {
        try
        {
            getControladorBrazo().brazoIzquierda( Constantes.STAGE_ARM_HORIZONTAL_DEFAULT_MOVEMENT_ANGLE );
        }
        catch ( Exception e )
        {
            toast( getResources().getString( R.string.error_al_enviar_el_comando ) );
            e.printStackTrace();
        }
    }

    @Override
    public Bitmap obtenerBitmap()
    {
        return mv.getLastFrame();
    }
}