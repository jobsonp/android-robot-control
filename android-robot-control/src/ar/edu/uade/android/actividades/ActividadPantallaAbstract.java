package ar.edu.uade.android.actividades;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Vibrator;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.MenuItem.OnMenuItemClickListener;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.ToggleButton;
import ar.edu.uade.android.R;
import ar.edu.uade.android.controladores.ControladorBrazo;
import ar.edu.uade.android.controladores.ControladorServos;
import ar.edu.uade.android.controladores.ControladorSpeech;
import ar.edu.uade.android.dialogo.DialogoSalir;
import ar.edu.uade.android.utils.Constantes;

import com.google.android.maps.MapActivity;

public abstract class ActividadPantallaAbstract
    extends MapActivity
{

    private SharedPreferences preferences;
    private ControladorBrazo controladorBrazo;
    private ControladorSpeech controladorSpeech;
    private ControladorServos controladorServos;

    @Override
    protected void onCreate( Bundle icicle )
    {
        super.onCreate( icicle );

        controladorBrazo = new ControladorBrazo();
        controladorSpeech = new ControladorSpeech();
        controladorServos = new ControladorServos();

        requestWindowFeature( Window.FEATURE_NO_TITLE );
        getWindow().setFlags( WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN );
        preferences = getSharedPreferences( Constantes.NOMBRE_ARCHIVO_PREFERENCIAS, 0 );
    }

    /**
     * Creacion del menu y sus listeners
     * 
     * @param menu.
     */
    public final boolean onCreateOptionsMenu( Menu menu )
    {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate( R.menu.menu, menu );

        menu.getItem( 0 ).setOnMenuItemClickListener( new OnMenuItemClickListener()
        {
            @Override
            public boolean onMenuItemClick( MenuItem item )
            {
                startActivity( new Intent( ActividadConfiguracion.class.getName() ) );
                return false;
            }
        } );
        menu.getItem( 1 ).setOnMenuItemClickListener( new OnMenuItemClickListener()
        {
            @Override
            public boolean onMenuItemClick( MenuItem item )
            {
                salir();
                return false;
            }
        } );

        return true;
    }

    /**
     * Metodo para desconectar el robot y salir a la pantalla principal.
     */
    public final void salir()
    {

        new DialogoSalir( this, this, getPreferences()
            .getBoolean(
                         getResources().getString( R.string.configuracion_interfaz_salir_preference_key ),
                         Boolean.parseBoolean( getResources()
                             .getString( R.string.configuracion_interfaz_salir_valor_defecto ) ) ) );
    }

    /**
     * Metodo disponible para todas las activities para mostrar mensajes del tipo Toast
     * 
     * @param mensaje a mostrar
     */
    public final void toast( String mensaje )
    {
        Context context = getApplicationContext();
        CharSequence text = mensaje;
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText( context, text, duration );
        toast.show();
    }

    /**
     * Metodo disponible para todas las activities para hacer vibrar el dispositivo.
     * 
     * @param milisegundos de la duracion de la vibracion.
     */
    public final void vibrationFeedback()
    {

        boolean vibracionActivada = getPreferences()
            .getBoolean(
                         getResources().getString( R.string.configuracion_vibracion_activar_preference_key ),
                         Boolean.parseBoolean( getResources()
                             .getString( R.string.configuracion_vibracion_activar_valor_defecto ) ) );

        if ( vibracionActivada )
        {
            String milisegundos = getPreferences()
                .getString( getResources().getString( R.string.configuracion_vibracion_duracion_preference_key ),
                            getResources().getString( R.string.configuracion_vibracion_duracion_valor_defecto ) );

            ( (Vibrator) getSystemService( VIBRATOR_SERVICE ) ).vibrate( Integer.parseInt( milisegundos ) );
        }

    }

    /**
     * Metodo llamado directamente desde el onClick declarado en el xml del layout del boton.
     * Obtiene una captura de la pantalla actual.
     * 
     * @param vista a ser capturada.
     * @throws IOException
     */
    public final void capturaDePantallaHandler( View vista )
    {
        vibrationFeedback();

        Bitmap bitmap = obtenerBitmap();

        String directorio = getPreferences()
            .getString( getResources().getString( R.string.configuracion_captura_pantalla_directorio_preference_key ),
                        getResources().getString( R.string.configuracion_captura_pantalla_directorio_valor_defecto ) );

        String formato = getPreferences()
            .getString( getResources().getString( R.string.configuracion_captura_pantalla_formato_preference_key ),
                        getResources().getString( R.string.configuracion_captura_pantalla_formato_valor_defecto ) );

        int compresion = getPreferences()
            .getInt(
                     getResources().getString( R.string.configuracion_captura_pantalla_compresion_preference_key ),
                     Integer.parseInt( getResources()
                         .getString( R.string.configuracion_captura_pantalla_compresion_valor_defecto ) ) );

        File archivoCapturaPantalla = new File( directorio + System.currentTimeMillis() + "." + formato );

        try
        {
            archivoCapturaPantalla.createNewFile();

            OutputStream fOut = new FileOutputStream( archivoCapturaPantalla );
            bitmap.compress( formato.equals( "png" ) ? Bitmap.CompressFormat.PNG : Bitmap.CompressFormat.JPEG,  compresion , fOut );
            fOut.flush();
            fOut.close();
            bitmap.recycle();

            MediaStore.Images.Media.insertImage( getContentResolver(), archivoCapturaPantalla.getAbsolutePath(),
                                                 archivoCapturaPantalla.getName(), archivoCapturaPantalla.getName() );
            toast( getResources().getString( R.string.toast_pantalla_capturada ) );
        }
        catch ( Exception e )
        {
            toast( getResources().getString( R.string.error_captura_pantalla ) );
            e.printStackTrace();
        }

    }

    /**
     * Metodo llamado directamente desde el onClick declarado en el xml del layout del boton. Envia
     * un mensaje al robot para que lo pronuncie.
     * 
     * @param vista
     */
    public final void enviarMensajeHandler( View vista )
    {
        vibrationFeedback();
        AlertDialog.Builder builder = new AlertDialog.Builder( this );
        builder.setTitle( getResources().getString( R.string.dialogo_enviar_mensaje_titulo ) );
        builder.setCancelable( false );

        final EditText input = new EditText( this );
        input.setPadding( 3, 3, 3, 3 );
        builder.setView( input );
        builder.setPositiveButton( getResources().getString( R.string.dialogo_enviar_mensaje_boton_enviar ),
                                   new DialogInterface.OnClickListener()
                                   {
                                       public void onClick( DialogInterface dialog, int id )
                                       {
                                           String value = input.getText().toString();
                                           if ( value != null )
                                           {
                                               try
                                               {
                                                   controladorSpeech.decirTexto( value );
                                                   toast( getResources().getString( R.string.toast_mensaje_enviado ) );
                                               }
                                               catch ( Exception e )
                                               {
                                                   toast( getResources().getString( R.string.error_enviar_mensaje ) );
                                                   e.printStackTrace();
                                               }
                                           }
                                       }
                                   } );

        builder.setNegativeButton( getResources().getString( R.string.dialogo_enviar_mensaje_boton_cancelar ),
                                   new DialogInterface.OnClickListener()
                                   {
                                       public void onClick( DialogInterface dialog, int id )
                                       {
                                           dialog.cancel();
                                       }
                                   } );

        AlertDialog d = builder.create();
        d.show();
    }

    /**
     * Metodo llamado directamente desde el onClick declarado en el xml del layout del boton. Abre o
     * cierra la pinza del brazo del robot.
     * 
     * @param vista
     */
    public final void abrirCerrarBrazoHandler( View vista )
    {
        vibrationFeedback();
        try
        {
            int handCurrentPosition = preferences.getInt( Constantes.STAGE_HAND_CURRENT_VALUE_PREFERENCES_KEY,
                                                          Constantes.STAGE_HAND_DEFAULT_POSITION );
            if ( handCurrentPosition == Constantes.STAGE_HAND_OPEN )
            {
                controladorBrazo.cerrarManoMaximo();
                ( (ToggleButton) findViewById( R.id.boton_funcion_brazo ) ).setChecked( false );
                toast( getResources().getString( R.string.toast_pinza_cerrada ) );
            }
            else
            {
                controladorBrazo.abrirManoMaximo();
                ( (ToggleButton) findViewById( R.id.boton_funcion_brazo ) ).setChecked( true );
                toast( getResources().getString( R.string.toast_pinza_abierta ) );
            }
        }
        catch ( Exception e )
        {
            toast( getResources().getString( R.string.error_abrir_cerrar_brazo ) );
            e.printStackTrace();
        }
    }

    /**
     * Metodo llamado directamente desde el onClick declarado en el xml del layout del boton. Cambia
     * la vista actual a la principal.
     * 
     * @param target
     */
    public final void cambiarAVistaPrincipalHandler( View target )
    {
        vibrationFeedback();
        this.finish();
        startActivity( new Intent( ActividadPantallaWebcamPrincipal.class.getName() ) );
    }

    /**
     * Metodo llamado directamente desde el onClick declarado en el xml del layout del boton. Cambia
     * la vista actual a la del brazo.
     * 
     * @param target
     */
    public final void cambiarAVistaBrazoHandler( View target )
    {
        vibrationFeedback();
        this.finish();
        startActivity( new Intent( ActividadPantallaWebcamBrazo.class.getName() ) );
    }

    /**
     * Metodo llamado directamente desde el onClick declarado en el xml del layout del boton. Cambia
     * la vista actual a la del mapa.
     * 
     * @param target
     */
    public final void cambiarAVistaMapaHandler( View target )
    {
        vibrationFeedback();
        this.finish();
        startActivity( new Intent( ActividadPantallaMapa.class.getName() ) );
    }

    /**
     * Metodo llamado directamente desde el onClick declarado en el xml del layout del boton Delega
     * el evento del boton a las subclases.
     * 
     * @param target
     */
    public final void flechaArribaHandler( View target )
    {
        vibrationFeedback();
        ejecutarFlechaArriba();
    }

    /**
     * Metodo llamado directamente desde el onClick declarado en el xml del layout del boton Delega
     * el evento del boton a las subclases.
     * 
     * @param target
     */
    public final void flechaAbajoHandler( View target )
    {
        vibrationFeedback();
        ejecutarFlechaAbajo();
    }

    /**
     * Metodo llamado directamente desde el onClick declarado en el xml del layout del boton Delega
     * el evento del boton a las subclases.
     * 
     * @param target
     */
    public final void flechaDerechaHandler( View target )
    {
        vibrationFeedback();
        ejecutarFlechaDerecha();
    }

    /**
     * Metodo llamado directamente desde el onClick declarado en el xml del layout del boton Delega
     * el evento del boton a las subclases.
     * 
     * @param target
     */
    public final void flechaIzquierdaHandler( View target )
    {
        vibrationFeedback();
        ejecutarFlechaIzquierda();
    }

    /**
     * Getter para las preferencias. Permite ser accecidas por las subclases
     * 
     * @return
     */
    public final SharedPreferences getPreferences()
    {
        return preferences;
    }

    /**
     * Permitir el acceso al ControladorBrazo por parte de las subclases
     * 
     * @return ControladorBrazo
     */
    public final ControladorBrazo getControladorBrazo()
    {
        return controladorBrazo;
    }

    /**
     * Permitir el acceso al ControladorSpeech por parte de las subclases
     * 
     * @return ControladorSpeech
     */
    public final ControladorSpeech getControladorSpeech()
    {
        return controladorSpeech;
    }

    /**
     * Permitir el acceso al ControladorServos por parte de las subclases
     * 
     * @return ControladorServos
     */
    public final ControladorServos getControladorServos()
    {
        return controladorServos;
    }

    /**
     * Delegar la implementacion de la funcionalidad de la flecha derecha
     */
    public abstract void ejecutarFlechaDerecha();

    /**
     * Delegar la implementacion de la funcionalidad de la flecha izquierda
     */
    public abstract void ejecutarFlechaIzquierda();

    /**
     * Delegar la implementacion de la funcionalidad de la flecha abajo
     */
    public abstract void ejecutarFlechaAbajo();

    /**
     * Delegar la implementacion de la funcionalidad de la flecha arriba
     */
    public abstract void ejecutarFlechaArriba();

    /**
     * Delegar la obtencion del bitmap a ser guardado en la captura de pantalla
     */
    public abstract Bitmap obtenerBitmap();

}
