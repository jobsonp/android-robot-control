package ar.edu.uade.android.actividades;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import ar.edu.uade.android.R;
import ar.edu.uade.android.utils.Configuracion;
import ar.edu.uade.android.utils.Constantes;
import ar.edu.uade.android.utils.Validador;

public class ActividadConectar
    extends Activity
{
    private static final String TIPO_ROBOT = "tipo-robot";
    private static final String IP_NOMBRE_ROBOT = "ip-nombre-robot";
    private static final String PUERTO_PLAYER = "puerto-player";
    private static final String PUERTO_WEBCAM_PRINCIPAL = "puerto-webcam-principal";
    private static final String PUERTO_WEBCAM_BRAZO = "puerto-webcam-brazo";

    private SharedPreferences preferences;
    private ArrayAdapter<CharSequence> adapter;

    public void onCreate( Bundle savedInstanceState )
    {
        super.onCreate( savedInstanceState );

        preferences = getSharedPreferences( Constantes.NOMBRE_ARCHIVO_PREFERENCIAS, 0 );

        setContentView( R.layout.conectar );

        Spinner tipoRobot = (Spinner) findViewById( R.id.conexion_tipo_robot );
        adapter = new ArrayAdapter<CharSequence>( this, android.R.layout.simple_spinner_item,
                                                  new ArrayList<CharSequence>( 2 ) );
        adapter.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item );
        tipoRobot.setAdapter( adapter );

        leerParametros();
    }

    /**
     * Metodo llamado directamente desde el onClick declarado en el xml del layout del boton.
     * Utiliza los parametros de la pantalla para conectarse al robot.
     * 
     * @param v
     */
    public void conectar( View v )
    {
        String tipoRobot = ( (Spinner) findViewById( R.id.conexion_tipo_robot ) ).getSelectedItem().toString();
        String ipNombreRobot = ( (EditText) findViewById( R.id.conexion_ip_robot ) ).getText().toString();
        String puertoPlayer = ( (EditText) findViewById( R.id.conexion_puerto_player ) ).getText().toString();
        String puertoWebcamPrincipal = ( (EditText) findViewById( R.id.conexion_puerto_webcam_principal ) ).getText()
            .toString();
        String puertoWebcamBrazo = ( (EditText) findViewById( R.id.conexion_puerto_webcam_brazo ) ).getText()
            .toString();

        if ( parametrosValidos( ipNombreRobot, puertoPlayer, puertoWebcamPrincipal, puertoWebcamBrazo ) )
        {
            // Ahora que la ip y los puertos son validos los cargo a la configuracion.
            Configuracion.setConfiguracion( Constantes.TIPO_ROBOT, tipoRobot );
            Configuracion.setConfiguracion( Constantes.IP_PLAYER, ipNombreRobot );
            Configuracion.setConfiguracion( Constantes.PORT_PLAYER, puertoPlayer );
            Configuracion.setConfiguracion( Constantes.MAIN_WEBCAM_PORT, puertoWebcamPrincipal );
            Configuracion.setConfiguracion( Constantes.ARM_WEBCAM_PORT, puertoWebcamBrazo );

            Toast.makeText( this, R.string.toast_conectando, Toast.LENGTH_SHORT ).show();

            ejecutarActividadInicio();
        }
        else
        {
            Toast.makeText( this, R.string.toast_ip_o_puerto_invalidos, Toast.LENGTH_SHORT ).show();
        }
    }

    /**
     * Leer de las preferencias salvadas que pantalla se posee configurada para ser la inicial e
     * inciarla
     */
    private void ejecutarActividadInicio()
    {
        String pantallaIncial = preferences.getString( getResources()
            .getString( R.string.configuracion_interfaz_pantalla_inicial_preference_key ), getResources()
            .getString( R.string.configuracion_interfaz_pantalla_inicial_valor_defecto ) );

        if ( pantallaIncial.equals( "brazo" ) )
        {
            startActivity( new Intent( ActividadPantallaWebcamBrazo.class.getName() ) );
        }
        else if ( pantallaIncial.equals( "mapa" ) )
        {
            startActivity( new Intent( ActividadPantallaMapa.class.getName() ) );
        }
        else
        {
            startActivity( new Intent( ActividadPantallaWebcamPrincipal.class.getName() ) );
        }
    }

    /**
     * Metodo llamado directamente desde el onClick declarado en el xml del layout del boton. Guarda
     * los parametros en las preferencias del sistema.
     * 
     * @param target
     */
    public void guardarParametros( View target )
    {
        String tipoRobot = ( (Spinner) findViewById( R.id.conexion_tipo_robot ) ).getSelectedItem().toString();
        String ipNombreRobot = ( (EditText) findViewById( R.id.conexion_ip_robot ) ).getText().toString();
        String puertoPlayer = ( (EditText) findViewById( R.id.conexion_puerto_player ) ).getText().toString();
        String puertoWebcamPrincipal = ( (EditText) findViewById( R.id.conexion_puerto_webcam_principal ) ).getText()
            .toString();
        String puertoWebcamBrazo = ( (EditText) findViewById( R.id.conexion_puerto_webcam_brazo ) ).getText()
            .toString();

        SharedPreferences.Editor editor = preferences.edit();
        editor.putString( TIPO_ROBOT, tipoRobot );
        editor.putString( IP_NOMBRE_ROBOT, ipNombreRobot );
        editor.putString( PUERTO_PLAYER, puertoPlayer );
        editor.putString( PUERTO_WEBCAM_PRINCIPAL, puertoWebcamPrincipal );
        editor.putString( PUERTO_WEBCAM_BRAZO, puertoWebcamBrazo );
        editor.commit();
        
        Toast.makeText( this, R.string.toast_parametros_guardados, Toast.LENGTH_SHORT ).show();
    }

    /**
     * Metodo para validar los parametros de conexion.
     * 
     * @param ipNombreRobot
     * @param puertoPlayer
     * @param puertoWebcamPrincipal
     * @param puertoWebcamBrazo
     * @return
     */
    private boolean parametrosValidos( String ipNombreRobot, String puertoPlayer, String puertoWebcamPrincipal,
                                       String puertoWebcamBrazo )
    {
        return Validador.validarIp( ipNombreRobot ) && Validador.validarPuerto( puertoPlayer )
            && Validador.validarPuerto( puertoWebcamPrincipal ) && Validador.validarPuerto( puertoWebcamBrazo );
    }

    /**
     * Metodo para leer los parametros de las preferencias de la aplicacion y agregar estos valores
     * a los campos de texto de la pantalla de conexion
     */
    private void leerParametros()
    {
        leerTipoRobot();

        ( (EditText) findViewById( R.id.conexion_ip_robot ) ).setText( preferences.getString( IP_NOMBRE_ROBOT, "" ) );
        ( (EditText) findViewById( R.id.conexion_puerto_player ) ).setText( preferences.getString( PUERTO_PLAYER, "" ) );
        ( (EditText) findViewById( R.id.conexion_puerto_webcam_principal ) ).setText( preferences
            .getString( PUERTO_WEBCAM_PRINCIPAL, "" ) );
        ( (EditText) findViewById( R.id.conexion_puerto_webcam_brazo ) ).setText( preferences
            .getString( PUERTO_WEBCAM_BRAZO, "" ) );

    }

    /**
     * Metodo para leer el tipo de robot guardado en las preferencias y popular el spinner.
     */
    private void leerTipoRobot()
    {
        String emuladoStage = getResources().getString( R.string.conexion_texto_tipo_robot_stage );
        String robotRtai = getResources().getString( R.string.conexion_texto_tipo_robot_rtai );
        String preference = preferences.getString( TIPO_ROBOT, "" );

        if ( preference.equals( "" ) || preference.equals( emuladoStage ) )
        {
            adapter.add( emuladoStage );
            adapter.add( robotRtai );
        }
        else
        {
            adapter.add( robotRtai );
            adapter.add( emuladoStage );
        }
    }
}