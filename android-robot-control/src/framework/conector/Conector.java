package framework.conector;

import java.util.Vector;

import javaclient2.GPSInterface;
import javaclient2.PlayerClient;
import javaclient2.ServoRTAIInterface;
import javaclient2.WiFiInterface;
import javaclient2.structures.PlayerConstants;
import framework.constants.Constants;
import framework.utils.LeeConfig;
import framework.utils.Logs;

public class Conector
{
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
        ip = LeeConfig.buscarConfiguracion( Constants.IP_PLAYER );
        puerto = Integer.parseInt( LeeConfig.buscarConfiguracion( Constants.PORT_PLAYER ) );
        online = Boolean.parseBoolean( LeeConfig.buscarConfiguracion( Constants.ONLINE_PLAYER ) );
        brazo_id_servos[Constants.SERVO_HORIZONTAL_ARRAY] = Integer.parseInt( LeeConfig
            .buscarConfiguracion( Constants.BRAZO_HORIZ_ID ) );
        brazo_id_servos[Constants.SERVO_VERTICAL_ARRAY] = Integer.parseInt( LeeConfig
            .buscarConfiguracion( Constants.BRAZO_VERT_ID ) );
        brazo_id_servos[Constants.SERVO_MANO_ARRAY] = Integer.parseInt( LeeConfig
            .buscarConfiguracion( Constants.BRAZO_MANO_ID ) );
        brazo_min[Constants.SERVO_HORIZONTAL_ARRAY] = Integer.parseInt( LeeConfig
            .buscarConfiguracion( Constants.BRAZO_HORIZ_MIN ) );
        brazo_max[Constants.SERVO_HORIZONTAL_ARRAY] = Integer.parseInt( LeeConfig
            .buscarConfiguracion( Constants.BRAZO_HORIZ_MAX ) );
        brazo_min[Constants.SERVO_VERTICAL_ARRAY] = Integer.parseInt( LeeConfig
            .buscarConfiguracion( Constants.BRAZO_VERT_MIN ) );
        brazo_max[Constants.SERVO_VERTICAL_ARRAY] = Integer.parseInt( LeeConfig
            .buscarConfiguracion( Constants.BRAZO_VERT_MAX ) );
        brazo_min[Constants.SERVO_MANO_ARRAY] = Integer.parseInt( LeeConfig
            .buscarConfiguracion( Constants.BRAZO_MANO_MIN ) );
        brazo_max[Constants.SERVO_MANO_ARRAY] = Integer.parseInt( LeeConfig
            .buscarConfiguracion( Constants.BRAZO_MANO_MAX ) );
        brazo_pasos = Integer.parseInt( LeeConfig.buscarConfiguracion( Constants.BRAZO_STEP ) );

        Logs.mensaje( null, "M8", Constants.MSG_STD, "OBTUVO IP: " + ip );
        Logs.mensaje( null, "M8", Constants.MSG_STD, "OBTUVO PUERTO: " + puerto );
        Logs.mensaje( null, "M8", Constants.MSG_STD, "OBTUVO ESTADO: " + online );
        Logs.mensaje( null, "M2", Constants.MSG_STD, "OBTUVO ESTADO: HORIZ ID SERVO: " + brazo_id_servos[0] );
        Logs.mensaje( null, "M2", Constants.MSG_STD, "OBTUVO ESTADO: VERT ID SERVO: " + brazo_id_servos[1] );
        Logs.mensaje( null, "M2", Constants.MSG_STD, "OBTUVO ESTADO: MANO ID SERVO: " + brazo_id_servos[1] );
        Logs.mensaje( null, "M2", Constants.MSG_STD, "OBTUVO ESTADO: PASOS SERVO: " + brazo_pasos );
        Logs.mensaje( null, "M2", Constants.MSG_STD, "OBTUVO ESTADO: BRAZO HORIZ MIN: " + brazo_min[0] );
        Logs.mensaje( null, "M2", Constants.MSG_STD, "OBTUVO ESTADO: BRAZO HORIZ MAX: " + brazo_max[0] );
        Logs.mensaje( null, "M2", Constants.MSG_STD, "OBTUVO ESTADO: BRAZO VERT MIN: " + brazo_min[1] );
        Logs.mensaje( null, "M2", Constants.MSG_STD, "OBTUVO ESTADO: BRAZO VERT MAX: " + brazo_max[1] );
        Logs.mensaje( null, "M2", Constants.MSG_STD, "OBTUVO ESTADO: MANO MIN: " + brazo_min[2] );
        Logs.mensaje( null, "M2", Constants.MSG_STD, "OBTUVO ESTADO: MANO MAX: " + brazo_max[2] );
    }

    public Vector getInterfaceGPS()
    {
        GPSInterface gps = null;
        if ( online )
        {
            robot = new PlayerClient( ip, puerto );
            try
            {
                gps = robot.requestInterfaceGPS( 0, PlayerConstants.PLAYER_OPEN_MODE );
                Logs.mensaje( "L6", "M8", Constants.MSG_STD, null );
            }
            catch ( Exception e )
            {
                Logs.mensaje( "L7", "M8", Constants.MSG_ERR, null );
            }
        }
        else
        {
            Logs.mensaje( "L3", "M8", Constants.MSG_ERR, null );
        }
        Vector vv = new Vector();
        vv.addElement( robot );
        vv.addElement( gps );
        return vv;
    }

    public Vector getInterfaceSERVO()
    {
        ServoRTAIInterface servo = null;
        if ( online )
        {
            robot = new PlayerClient( ip, puerto );
            try
            {
                servo = robot.requestInterfaceServoRTAI( 0, PlayerConstants.PLAYER_OPEN_MODE );
                Logs.mensaje( "L12", "M8", Constants.MSG_STD, null );
            }
            catch ( Exception e )
            {
                Logs.mensaje( "L13", "M8", Constants.MSG_ERR, null );
            }
        }
        else
        {
            Logs.mensaje( "L3", "M8", Constants.MSG_ERR, null );
        }
        Vector vv = new Vector();
        vv.addElement( robot );
        vv.addElement( servo );
        return vv;
    }

    public Vector getInfoBrazo()
    {
        Vector vv = new Vector();
        vv.addElement( new Integer( brazo_pasos ) ); // int
        vv.addElement( brazo_id_servos ); // int[]
        vv.addElement( brazo_min ); // int[]
        vv.addElement( brazo_max ); // int[]
        return vv;
    }

    public Vector getInterfaceMOTOR()
    {
        ServoRTAIInterface servo = null;
        if ( online )
        {
            robot = new PlayerClient( ip, puerto );
            try
            {
                servo = robot.requestInterfaceServoRTAI( 1, PlayerConstants.PLAYER_OPEN_MODE );
                Logs.mensaje( "L14", "M8", Constants.MSG_STD, null );
            }
            catch ( Exception e )
            {
                Logs.mensaje( "L15", "M8", Constants.MSG_ERR, null );
            }
        }
        else
        {
            Logs.mensaje( "L3", "M8", Constants.MSG_ERR, null );
        }
        Vector vv = new Vector();
        vv.addElement( robot );
        vv.addElement( servo );
        return vv;
    }

    public Vector getInterfaceWIRELESS()
    {
        WiFiInterface wifi = null;
        if ( online )
        {
            robot = new PlayerClient( ip, puerto );
            try
            {
                wifi = robot.requestInterfaceWiFi( 0, PlayerConstants.PLAYER_OPEN_MODE );
                Logs.mensaje( "L10", "M8", Constants.MSG_STD, null );
            }
            catch ( Exception e )
            {
                Logs.mensaje( "L11", "M8", Constants.MSG_ERR, null );
            }
        }
        else
        {
            Logs.mensaje( "L3", "M8", Constants.MSG_ERR, null );
        }
        Vector vv = new Vector();
        vv.addElement( robot );
        vv.addElement( wifi );
        return vv;
    }

    public static void main( String[] args )
    {

    }
}
