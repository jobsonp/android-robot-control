package framework.conector;

import java.util.Vector;

import javaclient2.GPSInterface;
import javaclient2.PlayerClient;
import javaclient2.Position2DInterface;
import javaclient2.ServoRTAIInterface;
import javaclient2.SpeechInterface;
import javaclient2.WiFiInterface;
import javaclient2.structures.PlayerConstants;
import android.util.Log;
import ar.edu.uade.android.utils.Configuracion;
import ar.edu.uade.android.utils.Constantes;

public class Conector {
    
	private int puerto;

    private String ip;

    private boolean online;

    private PlayerClient robot = null;

    private static Conector instance;

    // Para el brazo
    private int brazo_pasos = 127;

    private int[] brazo_min = { 0, 0, 0 };

    private int[] brazo_max = { 127, 127, 127 };

    private int[] brazo_id_servos = { 0, 1, 2 };

    public static Conector getInstance()
    {
        if ( instance == null )
        {
            instance = new Conector();
        }
        return instance;
    }

    public boolean isOnline()
    {
        return online;
    }

    private Conector()
    {
        ip = Configuracion.getConfigString( Constantes.IP_PLAYER );
        puerto = Configuracion.getConfigInt( Constantes.PORT_PLAYER );
        online = Configuracion.getConfigBoolean( Constantes.ONLINE_PLAYER );
        brazo_id_servos[Constantes.SERVO_HORIZONTAL_ARRAY] = Configuracion.getConfigInt( Constantes.BRAZO_HORIZ_ID );
        brazo_id_servos[Constantes.SERVO_VERTICAL_ARRAY] = Configuracion.getConfigInt( Constantes.BRAZO_VERT_ID );
        brazo_id_servos[Constantes.SERVO_MANO_ARRAY] = Configuracion.getConfigInt( Constantes.BRAZO_MANO_ID );
        brazo_min[Constantes.SERVO_HORIZONTAL_ARRAY] = Configuracion.getConfigInt( Constantes.BRAZO_HORIZ_MIN );
        brazo_max[Constantes.SERVO_HORIZONTAL_ARRAY] = Configuracion.getConfigInt( Constantes.BRAZO_HORIZ_MAX );
        brazo_min[Constantes.SERVO_VERTICAL_ARRAY] = Configuracion.getConfigInt( Constantes.BRAZO_VERT_MIN );
        brazo_max[Constantes.SERVO_VERTICAL_ARRAY] = Configuracion.getConfigInt( Constantes.BRAZO_VERT_MAX );
        brazo_min[Constantes.SERVO_MANO_ARRAY] = Configuracion.getConfigInt( Constantes.BRAZO_MANO_MIN );
        brazo_max[Constantes.SERVO_MANO_ARRAY] = Configuracion.getConfigInt( Constantes.BRAZO_MANO_MAX );
        brazo_pasos = Configuracion.getConfigInt( Constantes.BRAZO_STEP );

        Log.d( Conector.class.getName(), "OBTUVO IP: " + ip );
        Log.d( Conector.class.getName(), "OBTUVO PUERTO: " + puerto );
        Log.d( Conector.class.getName(), "OBTUVO ESTADO: " + online );
        Log.d( Conector.class.getName(), "OBTUVO ESTADO: HORIZ ID SERVO: " + brazo_id_servos[0] );
        Log.d( Conector.class.getName(), "OBTUVO ESTADO: VERT ID SERVO: " + brazo_id_servos[1] );
        Log.d( Conector.class.getName(), "OBTUVO ESTADO: MANO ID SERVO: " + brazo_id_servos[1] );
        Log.d( Conector.class.getName(), "OBTUVO ESTADO: PASOS SERVO: " + brazo_pasos );
        Log.d( Conector.class.getName(), "OBTUVO ESTADO: BRAZO HORIZ MIN: " + brazo_min[0] );
        Log.d( Conector.class.getName(), "OBTUVO ESTADO: BRAZO HORIZ MAX: " + brazo_max[0] );
        Log.d( Conector.class.getName(), "OBTUVO ESTADO: BRAZO VERT MIN: " + brazo_min[1] );
        Log.d( Conector.class.getName(), "OBTUVO ESTADO: BRAZO VERT MAX: " + brazo_max[1] );
        Log.d( Conector.class.getName(), "OBTUVO ESTADO: MANO MIN: " + brazo_min[2] );
        Log.d( Conector.class.getName(), "OBTUVO ESTADO: MANO MAX: " + brazo_max[2] );
        Log.i( Conector.class.getName(), "Conector inicializado." );
    }

	public Vector<Object> getInterfaceGPS()
    {
        GPSInterface gps = null;
        if ( online )
        {
            robot = new PlayerClient( ip, puerto );
            try
            {
                gps = robot.requestInterfaceGPS( 0, PlayerConstants.PLAYER_OPEN_MODE );

                Log.d( Conector.class.getName(), Constantes.MODULO_CONECTOR + Constantes.INTERFACE_GPS_OK );
            }
            catch ( Exception e )
            {
                Log.d( Conector.class.getName(), Constantes.MODULO_CONECTOR + Constantes.INTERFACE_GPS_FAILED );
            }
        }
        else
        {
            Log.e( Conector.class.getName(), Constantes.MODULO_CONECTOR + Constantes.SERVIDOR_OFFLINE );
        }
       
        Vector<Object> vv = new Vector<Object>();
        vv.addElement( robot );
        vv.addElement( gps );
        return vv;
    }

    public Vector<Object> getInterfaceSERVO()
    {
        ServoRTAIInterface servo = null;
        if ( online )
        {
            robot = new PlayerClient( ip, puerto );
            try
            {
                servo = robot.requestInterfaceServoRTAI( 0, PlayerConstants.PLAYER_OPEN_MODE );
                Log.d( Conector.class.getName(), Constantes.MODULO_CONECTOR + Constantes.INTERFACE_SERVORTAI_OK );
            }
            catch ( Exception e )
            {
                Log.d( Conector.class.getName(), Constantes.MODULO_CONECTOR + Constantes.INTERFACE_SERVORTAI_FAILED );
            }
        }
        else
        {
            Log.e( Conector.class.getName(), Constantes.MODULO_CONECTOR + Constantes.SERVIDOR_OFFLINE );
        }
      
        Vector<Object> vv = new Vector<Object>();
        vv.addElement( robot );
        vv.addElement( servo );
        return vv;
    }


    public Vector<Object> getInfoBrazo()
    {
        Vector<Object> vv = new Vector<Object>();
        vv.addElement( new Integer( brazo_pasos ) ); // int
        vv.addElement( brazo_id_servos ); // int[]
        vv.addElement( brazo_min ); // int[]
        vv.addElement( brazo_max ); // int[]
        return vv;
    }

    public Vector<Object> getInterfaceMOTOR()
    {
        ServoRTAIInterface servo = null;
        if ( online )
        {
            robot = new PlayerClient( ip, puerto );
            try
            {
                servo = robot.requestInterfaceServoRTAI( 1, PlayerConstants.PLAYER_OPEN_MODE );
                Log.d( Conector.class.getName(), Constantes.MODULO_CONECTOR + Constantes.INTERFACE_MOTOR_OK );
            }
            catch ( Exception e )
            {
                Log.d( Conector.class.getName(), Constantes.MODULO_CONECTOR + Constantes.INTERFACE_MOTOR_OK );
            }
        }
        else
        {
            Log.e( Conector.class.getName(), Constantes.MODULO_CONECTOR + Constantes.SERVIDOR_OFFLINE );
        }
        
        Vector<Object> vv = new Vector<Object>();
        vv.addElement( robot );
        vv.addElement( servo );
        return vv;
    }

    public Vector<Object> getInterfaceWIRELESS()
    {
        WiFiInterface wifi = null;
        if ( online )
        {
            robot = new PlayerClient( ip, puerto );
            try
            {
                wifi = robot.requestInterfaceWiFi( 0, PlayerConstants.PLAYER_OPEN_MODE );
                Log.d( Conector.class.getName(), Constantes.MODULO_CONECTOR + Constantes.INTERFACE_WIRELESS_OK );
            }
            catch ( Exception e )
            {
                Log.d( Conector.class.getName(), Constantes.MODULO_CONECTOR + Constantes.INTERFACE_WIRELESS_FAILED );
            }
        }
        else
        {
            Log.e( Conector.class.getName(), Constantes.MODULO_CONECTOR + Constantes.SERVIDOR_OFFLINE );
        }
        
        Vector<Object> vv = new Vector<Object>();
        vv.addElement( robot );
        vv.addElement( wifi );
        return vv;
    }
    
    public Vector<Object> getInterfaceSPEECH()
    {
        SpeechInterface speech = null;
        if ( online )
        {
            robot = new PlayerClient( ip, puerto );
            try
            {
            	speech = robot.requestInterfaceSpeech( 0, PlayerConstants.PLAYER_OPEN_MODE );
                Log.d( Conector.class.getName(), Constantes.MODULO_CONECTOR + Constantes.INTERFACE_SPEECH_OK );
            }
            catch ( Exception e )
            {
                Log.d( Conector.class.getName(), Constantes.MODULO_CONECTOR + Constantes.INTERFACE_SPEECH_FAILED );
            }
        }
        else
        {
            Log.e( Conector.class.getName(), Constantes.MODULO_CONECTOR + Constantes.SERVIDOR_OFFLINE );
        }
        
        Vector<Object> vv = new Vector<Object>();
        vv.addElement( robot );
        vv.addElement( speech );
        return vv;
    }
    
    public Vector<Object> getInterfacePOSITION2D(){
		Position2DInterface servo = null;
		if (online) {
			robot = new PlayerClient (ip, puerto);
			try {
				servo = robot.requestInterfacePosition2D(0, PlayerConstants.PLAYER_OPEN_MODE);
				Log.d( Conector.class.getName(), Constantes.MODULO_CONECTOR + Constantes.INTERFACE_POSITION_2D_OK );
			} catch (Exception e)
			{
				Log.d( Conector.class.getName(), Constantes.MODULO_CONECTOR + Constantes.INTERFACE_POSITION_2D_FAILED );
			}
		} else
		{
			Log.e( Conector.class.getName(), Constantes.MODULO_CONECTOR + Constantes.SERVIDOR_OFFLINE );
		}
		
		Vector<Object> vv = new Vector<Object>();
		vv.addElement(robot);
		vv.addElement(servo);
		return vv;
	}
    
}
