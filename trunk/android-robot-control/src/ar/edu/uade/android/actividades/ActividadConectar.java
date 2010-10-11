package ar.edu.uade.android.actividades;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import ar.edu.uade.android.R;
import ar.edu.uade.android.utils.Configuracion;
import ar.edu.uade.android.utils.Constantes;
import ar.edu.uade.android.utils.Validador;

public class ActividadConectar
    extends Activity
    implements OnClickListener
{
    private static final String TIPO_ROBOT = "tipo-robot";
    private static final String IP_NOMBRE_ROBOT = "ip-nombre-robot";
    private static final String PUERTO_PLAYER = "puerto-player";
    private static final String PUERTO_WEBCAM_PRINCIPAL = "puerto-webcam-principal";
    private static final String PUERTO_WEBCAM_BRAZO = "puerto-webcam-brazo";

    private SharedPreferences preferences;

    public void onCreate( Bundle savedInstanceState )
    {
        super.onCreate( savedInstanceState );

        preferences = getSharedPreferences( Constantes.NOMBRE_ARCHIVO_PREFERENCIAS, 0 );

        setContentView( R.layout.conectar );
        Button buttonConectar = (Button) this.findViewById( R.id.conexion_boton_conectar );

        buttonConectar.setOnClickListener( this );

        Button buttonGuardar = (Button) this.findViewById( R.id.conexion_boton_guardar );
        buttonGuardar.setOnClickListener( this );

        leerParametros();
    }

    @Override
    public void onClick( View v )
    {
        // FIX ME - tipo robot
        String tipoRobot = "bla";
        String ipNombreRobot = ( (EditText) findViewById( R.id.conexion_ip_robot ) ).getText().toString();
        String puertoPlayer = ( (EditText) findViewById( R.id.conexion_puerto_player ) ).getText().toString();
        String puertoWebcamPrincipal = ( (EditText) findViewById( R.id.conexion_puerto_webcam_principal ) ).getText()
            .toString();
        String puertoWebcamBrazo = ( (EditText) findViewById( R.id.conexion_puerto_webcam_brazo ) ).getText()
            .toString();

        switch ( v.getId() )
        {
            case R.id.conexion_boton_conectar:
            {

                if ( parametrosValidos( ipNombreRobot, puertoPlayer, puertoWebcamPrincipal, puertoWebcamBrazo ) )
                {
                    // Ahora que la ip y los puertos son validos los cargo a la configuracion.
                    Configuracion.setConfiguracion( Constantes.IP_PLAYER, ipNombreRobot );
                    Configuracion.setConfiguracion( Constantes.PORT_PLAYER, puertoPlayer );
                    Configuracion.setConfiguracion( Constantes.MAIN_WEBCAM_PORT, puertoWebcamPrincipal );
                    Configuracion.setConfiguracion( Constantes.ARM_WEBCAM_PORT, puertoWebcamBrazo );

                    Toast.makeText( this, R.string.toast_conectando, Toast.LENGTH_SHORT ).show();
                    startActivity( new Intent( ActividadPantallaWebcamPrincipal.class.getName() ) );
                }
                else
                {
                    Toast.makeText( this, R.string.toast_ip_o_puerto_invalidos, Toast.LENGTH_SHORT ).show();
                }
                break;
            }
            case R.id.conexion_boton_guardar:
            {
                guardarParametros( tipoRobot, ipNombreRobot, puertoPlayer, puertoWebcamPrincipal, puertoWebcamBrazo );
                break;
            }
            default:
                break;
        }
    }

    /**
     * Metodo para validar los parametros de conexion.
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
        // FIX ME
        // ((EditText)findViewById( R.id.conexion_tipo_robot ) ).setText( preferences.getString(
        // TIPO_ROBOT, "" ));
        ( (EditText) findViewById( R.id.conexion_ip_robot ) ).setText( preferences.getString( IP_NOMBRE_ROBOT, "" ) );
        ( (EditText) findViewById( R.id.conexion_puerto_player ) ).setText( preferences.getString( PUERTO_PLAYER, "" ) );
        ( (EditText) findViewById( R.id.conexion_puerto_webcam_principal ) ).setText( preferences
            .getString( PUERTO_WEBCAM_PRINCIPAL, "" ) );
        ( (EditText) findViewById( R.id.conexion_puerto_webcam_brazo ) ).setText( preferences
            .getString( PUERTO_WEBCAM_BRAZO, "" ) );

    }

    /**
     * Metodo para guardar los parametros de conexion de la aplicacion. Este metodo se ejecuta
     * cuando se presiona el boton 'Guardar' en la pantalla principal
     * @param tipoRobot
     * @param ipNombreRobot
     * @param puertoPlayer
     * @param puertoWebcamPrincipal
     * @param puertoWebcamBrazo
     */
    private void guardarParametros( String tipoRobot, String ipNombreRobot, String puertoPlayer,
                                    String puertoWebcamPrincipal, String puertoWebcamBrazo )
    {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString( TIPO_ROBOT, tipoRobot );
        editor.putString( IP_NOMBRE_ROBOT, ipNombreRobot );
        editor.putString( PUERTO_PLAYER, puertoPlayer );
        editor.putString( PUERTO_WEBCAM_PRINCIPAL, puertoWebcamPrincipal );
        editor.putString( PUERTO_WEBCAM_BRAZO, puertoWebcamBrazo );
        editor.commit();
    }
}